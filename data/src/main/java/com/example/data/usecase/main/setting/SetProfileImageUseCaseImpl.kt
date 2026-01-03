package com.example.data.usecase.main.setting

import com.example.data.di.FC_HOST
import com.example.domain.model.Image
import com.example.domain.usecase.file.GetImageUseCase
import com.example.domain.usecase.file.UploadImageUseCase
import com.example.domain.usecase.main.setting.SetMyUserUseCase
import com.example.domain.usecase.main.setting.SetProfileImageUseCase
import javax.inject.Inject

class SetProfileImageUseCaseImpl @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val setMyUserUseCase: SetMyUserUseCase,
) : SetProfileImageUseCase {
    override suspend fun invoke(contentUri: String): Result<Unit> = runCatching {
        val image: Image =
            getImageUseCase(contentUri = contentUri) ?: throw NullPointerException("이미지를 찾을 수 없습니다")
        val imagePath = uploadImageUseCase(image).getOrThrow()
        setMyUserUseCase(
            profileImageUrl = imagePath
        ).getOrThrow()
    }
}