package com.example.data.usecase.file

import com.example.data.di.FC_HOST
import com.example.data.retrofit.FileService
import com.example.data.retrofit.UriRequestBody
import com.example.domain.model.Image
import com.example.domain.usecase.file.GetInputStreamUseCase
import com.example.domain.usecase.file.UploadImageUseCase
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadImageUseCaseImpl @Inject constructor(
    private val fileService: FileService,
    private val getInputStreamUseCase: GetInputStreamUseCase
) : UploadImageUseCase {
    override suspend fun invoke(image: Image): Result<String> = runCatching {
        val fileName = MultipartBody.Part.createFormData(
            "fileName",
            image.name
        )
        val requestBody = UriRequestBody(
            contentUri = image.uri,
            getInputStreamUseCase = getInputStreamUseCase,
            contentType = image.mimeType.toMediaType(),
            contentLength = image.size
        )
        val file = MultipartBody.Part.createFormData(
            "file",
            image.name,
            requestBody
        )
        "$FC_HOST/" +fileService.uploadFile(
            fileName,
            file
        ).data.filePath
    }
}