package com.example.ongsomosmas.Post

import android.telecom.CallScreeningService
import com.example.ongsomosmas.Dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/api/register")
    fun registerUser(@Body register: Register): Call<RepositoryResponse<UserRegister>>

    @POST("/api/login")
    fun loginUser(@Body login: Login): Call<RepositoryResponse<UserRegister>>

    @GET("/api/news")
    fun getNews(@Query("limit") limit : Int): Call<RepositoryResponse<List<News>>>

    @GET("/api/members")
    fun getMembers(@Query("limit") limit : Int): Call<RepositoryResponse<List<Members>>>
}