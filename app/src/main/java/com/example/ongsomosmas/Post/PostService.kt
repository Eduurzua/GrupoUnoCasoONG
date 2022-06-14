package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.Model.Register
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {

    @POST("/register")
    fun registerUser(@Body register: Register): Call<Register>

    @POST("/login")
    fun loginUser(@Body login: Login): Call<Login>
}