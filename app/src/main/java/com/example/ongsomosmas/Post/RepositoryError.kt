package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.Errors

data class RepositoryError(
    val message: String,
    val errors: Errors?
)