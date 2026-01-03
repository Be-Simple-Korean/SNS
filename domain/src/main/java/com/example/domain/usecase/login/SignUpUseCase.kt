package com.example.domain.usecase.login

interface SignUpUseCase {
    suspend operator fun invoke(
        id: String,
        name: String,
        pwd: String
    ): Result<Boolean>
}