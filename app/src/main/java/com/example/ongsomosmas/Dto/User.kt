package com.example.ongsomosmas.Dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name : String,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val email_verified_at: String?,
    @SerializedName("password")
    val password: String,
    @SerializedName("role_id")
    val role_id: Int?,
    @SerializedName("remember_token")
    val remember_token : String?,
    @SerializedName("created_at")
    val created_at : String?,
    @SerializedName("updated_at")
    val updated_at : String?,
    @SerializedName("deleted_at")
    val deleted_at : String?,
    @SerializedName("group_id")
    val group_id : Int?,
    @SerializedName("latitude")
    val latitude : Int?,
    @SerializedName("longitude")
    val longitude : Int?,
    @SerializedName("address")
    val address : String?,
    @SerializedName("profile_image")
    val profile_image : String?
) : Serializable
