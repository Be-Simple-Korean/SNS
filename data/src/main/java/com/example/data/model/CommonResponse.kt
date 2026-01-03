package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CommonResponse<T>(
    val result: String,
    val errorCode: String,
    val data: T,
    val errorMessage: String
)
