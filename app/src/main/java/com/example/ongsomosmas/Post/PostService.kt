package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.Model.Register
import com.example.ongsomosmas.Model.UserRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {

    @POST("/api/register")
    fun registerUser(@Body register: Register): Call<RepositoryResponse<UserRegister>>

    @POST("/api/login")
    fun loginUser(@Body login: Login): Call<RepositoryResponse<UserRegister>>
}