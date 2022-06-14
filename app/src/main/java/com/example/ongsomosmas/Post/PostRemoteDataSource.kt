package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.Model.Register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRemoteDataSource {

    fun registerUser(register: Register, listener: ResponseListener<Register>) {
        val service = RetrofitService.instance
            .create(PostService::class.java)
            .registerUser(register)

        service.enqueue(object : Callback<Register> {

            override fun onResponse(call: Call<Register>, response: Response<Register>) {
                val createdPost = response.body()
                if (response.isSuccessful && createdPost != null) {
                    listener.onResponse(
                        RepositoryResponse(
                            data = createdPost,
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

            override fun onFailure(call: Call<Register>, t: Throwable) {
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
    fun loginUser(login: Login, listener: ResponseListener<Login>) {
        val service = RetrofitService.instance
            .create(PostService::class.java)
            .loginUser(login)

        service.enqueue(object : Callback<Login> {

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                val createdPost = response.body()
                if (response.isSuccessful && createdPost != null) {
                    listener.onResponse(
                        RepositoryResponse(
                            data = createdPost,
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

            override fun onFailure(call: Call<Login>, t: Throwable) {
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