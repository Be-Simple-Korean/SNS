package com.example.domain.usecase.login

interface LoginUseCase {
    suspend operator fun invoke(
        id: String,
        pwd: String
    ): Result<String>
}