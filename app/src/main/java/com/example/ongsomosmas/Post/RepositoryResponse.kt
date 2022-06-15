package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.Errors

class RepositoryResponse<T>(
    val success: Boolean,
    val data: T,
    val message: String,
    val errors: Errors?
)