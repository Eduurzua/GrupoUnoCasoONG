package com.example.ongsomosmas.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Register (
    @SerializedName("name")
    val name : String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
) : Serializable
