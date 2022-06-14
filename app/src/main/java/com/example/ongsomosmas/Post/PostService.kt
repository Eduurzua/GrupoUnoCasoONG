package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

class PostService {
    @GET("/login")
    fun loginUser(@Query("participants") int : Int): Call<Post>


    @POST("/register")
    fun registerUser(@Body user: User): Call<User>
}