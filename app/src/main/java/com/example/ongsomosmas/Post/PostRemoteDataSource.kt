package com.example.ongsomosmas.Post

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRemoteDataSource {

    fun getPost(id: Int, listener: ResponseListener<Post>) {
        val service = RetrofitService.instance
            .create(PostService::class.java)
            .getPost(id)

        service.enqueue(object : Callback<Post> {

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val post = response.body()
                if (response.isSuccessful && post != null) {
                    listener.onResponse(
                        RepositoryResponse(
                            data = post,
                            source = Source.REMOTE
                        )
                    )
                } else {
                    listener.onError(
                        RepositoryError(
                            message = "El servidor rechazó la solicitud",
                            code = response.code(),
                            source = Source.REMOTE
                        )
                    )
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Error inesperado",
                        code = -1,
                        source = Source.REMOTE
                    )
                )
            }

        })
    }

    fun getPosts(str: String, listener: ResponseListener<Post>) {
        val service = RetrofitService.instance
            .create(PostService::class.java)
            .getPosts(str)

        service.enqueue(object : Callback<Post> {

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val post = response.body()
                if (response.isSuccessful && post != null) {
                    listener.onResponse(
                        RepositoryResponse(
                            data = post,
                            source = Source.REMOTE
                        )
                    )
                } else {
                    listener.onError(
                        RepositoryError(
                            message = "El servidor rechazó la solicitud",
                            code = response.code(),
                            source = Source.REMOTE
                        )
                    )
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Error inesperado",
                        code = -1,
                        source = Source.REMOTE
                    )
                )
            }

        })
    }
}