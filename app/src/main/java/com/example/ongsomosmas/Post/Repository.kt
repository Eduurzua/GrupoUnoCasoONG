package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.Model.Register
import com.example.ongsomosmas.Model.UserRegister

class Repository(

    private val remoteDataSource: ApiRemoteDataSource) {

    fun registerUser(register : Register, listener: ResponseListener<UserRegister>) {
        this.remoteDataSource.registerUser(register,listener)
    }

    fun loginUser(login: Login, listener: ResponseListener<UserRegister>) {
        this.remoteDataSource.loginUser(login,listener)
    }
}