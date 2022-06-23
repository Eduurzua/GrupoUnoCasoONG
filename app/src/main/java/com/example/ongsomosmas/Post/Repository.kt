package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Dto.*

class Repository(

    private val remoteDataSource: ApiRemoteDataSource) {

    fun registerUser(register : Register, listener: ResponseListener<UserRegister>) {
        this.remoteDataSource.registerUser(register,listener)
    }

    fun loginUser(login: Login, listener: ResponseListener<UserRegister>) {
        this.remoteDataSource.loginUser(login,listener)
    }

    fun getNews(limit: Int, listener: ResponseListener<List<News>>) {
        this.remoteDataSource.getNews(limit,listener)
    }

    fun getMembers(limit: Int, listener: ResponseListener<List<Members>>) {
        this.remoteDataSource.getMembers(limit,listener)
    }
}