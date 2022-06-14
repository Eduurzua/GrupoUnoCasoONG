package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.Register
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

class PostService {
    @POST("/register")
    fun registerUser(@Body Register: Register) : ResponseBody<Register>

}