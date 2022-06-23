package com.example.ongsomosmas.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PostMessage (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("deleted_at")
    val deleted_at : String?,
    @SerializedName("created_at")
    val created_at : String?,
    @SerializedName("updated_at")
    val updated_at : String?,
    val token: String
) : Serializable