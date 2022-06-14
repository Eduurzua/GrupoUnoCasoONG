package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.Model.Register

class PostRepository(

    private val remoteDataSource: PostRemoteDataSource) {

    fun registerUser(register : Register, listener: ResponseListener<Register>) {
        this.remoteDataSource.registerUser(register,listener)
    }

    fun loginUser(login: Login, listener: ResponseListener<Login>) {
        this.remoteDataSource.loginUser(login,listener)
    }
}