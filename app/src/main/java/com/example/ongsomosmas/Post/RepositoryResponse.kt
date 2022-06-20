package com.example.ongsomosmas.Post

class RepositoryResponse<T>(
    val success: Boolean,
    val data: T,
    val message: String,
    val token: String
)