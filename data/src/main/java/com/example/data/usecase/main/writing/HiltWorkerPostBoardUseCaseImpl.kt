package com.example.data.usecase.main.writing

import android.app.Notification
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.data.model.BoardParam
import com.example.data.model.BoardParcel
import com.example.data.model.ContentParam
import com.example.data.retrofit.BoardService
import com.example.data.service.PostingService
import com.example.domain.model.ACTION_POSTED
import com.example.domain.model.Image
import com.example.domain.usecase.file.UploadImageUseCase
import com.example.domain.usecase.main.writing.PostBoardUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.serialization.json.Json
import javax.inject.Inject

class HiltWorkerPostBoardUseCaseImpl @Inject constructor(
    private val context: Context
) : PostBoardUseCase {
    override fun invoke(
        title: String,
        content: String,
        images: List<Image>
    ) {
        val boardParcel = BoardParcel(
            title = title,
            content = content,
            images = images
        )

        val boardParcelJson = Json.encodeToString(boardParcel)

        val workRequest = OneTimeWorkRequestBuilder<BoardWorker>()
            .setInputData(
                workDataOf(
                    BoardParcel::class.java.simpleName to boardParcelJson
                )
            )
            .build()

        WorkManager.getInstance(context)
            .enqueue(workRequest)
    }
}

@HiltWorker
class BoardWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val boardService: BoardService,
    private val uploadImageUseCase: UploadImageUseCase
) : CoroutineWorker(
    context, params
) {
    override suspend fun doWork(): Result {
        val parcel =
            inputData.getString(BoardParcel::class.java.simpleName) ?: return Result.failure()
        val boardParcel = Json.decodeFromString<BoardParcel>(parcel)
        postBoard(boardParcel)
        return Result.success()
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(
            PostingService.FOREGROUND_NOTIFICATION_ID,
            createNotification()
        )
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(context, PostingService.CHANNEL_ID)
            .build()
    }

    private suspend fun postBoard(boardParcel: BoardParcel) {
        //업로드
        val uploadedImages =
            boardParcel.images.mapNotNull { image -> uploadImageUseCase(image).getOrNull() }

        val contentParam = ContentParam(
            text = boardParcel.content,
            images = uploadedImages
        )
        val boardParam = BoardParam(boardParcel.title, contentParam.toJson())
        val requestBody = boardParam.toRequestBody()
        boardService.postBoard(requestBody)
        context.sendBroadcast(
            Intent(
                ACTION_POSTED
            ).apply {
                setPackage(context.packageName)
            }
        )
    }
}