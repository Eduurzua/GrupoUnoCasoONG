package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.Model.PostMessage
import com.example.ongsomosmas.Model.Register
import com.example.ongsomosmas.Model.UserRegister
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api/register")
    fun registerUser(@Body register: Register): Call<RepositoryResponse<UserRegister>>

    @POST("/api/login")
    fun loginUser(@Body login: Login): Call<RepositoryResponse<UserRegister>>

    @POST("/api/contact")
    fun postMessage(@Body post: PostMessage): Call<Response<PostMessage>>
}