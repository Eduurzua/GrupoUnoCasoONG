package com.example.ongsomosmas.Post

class PostRepository(

    private val remoteDataSource: PostRemoteDataSource
) {

    fun getPost(id: Int, listener: ResponseListener<Post>) {
        this.remoteDataSource.getPost(id,listener)
    }


    fun createUser(str: String, listener: ResponseListener<Post>) {
        this.remoteDataSource.getPosts(listener)
    }
}