package com.example.data.usecase

import android.util.Log
import com.example.data.model.LoginParam
import com.example.data.model.SignUpParam
import com.example.data.retrofit.UserService
import com.example.domain.usecase.login.SignUpUseCase
import javax.inject.Inject

class SingUpUseCaseImpl @Inject constructor(
    private val userService: UserService
) : SignUpUseCase {
    override suspend fun invoke(
        id: String,
        name: String,
        pwd: String
    ): Result<Boolean> = runCatching {
        Log.e("JH Req","id=$id , name = $name, pwd=$pwd")
        val param = SignUpParam(
            loginId = id,
            password = pwd,
            name = name
        )
        userService.signUp(param.toRequestBody())
            .result == "SUCCESS"

    }
}