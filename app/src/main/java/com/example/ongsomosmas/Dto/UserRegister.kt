package com.example.ongsomosmas.Dto

import com.google.gson.annotations.SerializedName

data class UserRegister(
    @SerializedName("user")
    val user: User,
    @SerializedName("token")
    val token: String
)