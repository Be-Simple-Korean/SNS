package com.example.data.usecase

import com.example.data.model.LoginParam
import com.example.data.retrofit.UserService
import com.example.domain.usecase.login.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val userService: UserService
) : LoginUseCase {
    override suspend fun invoke(
        id: String,
        pwd: String
    ): Result<String> = runCatching {
        val param = LoginParam(
            loginId = id,
            password = pwd
        )
        userService.login(
            param.toRequestBody()
        ).data
    }
}