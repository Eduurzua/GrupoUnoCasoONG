package com.example.ongsomosmas.Dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class Members(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("facebookUrl")
    val facebookUrl: String,
    @SerializedName("linkedinUrl")
    val linkedinUrl: String,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("updated_at")
    val updatedAt: Date,
    @SerializedName("deleted_at")
    val deletedAt: Date
)