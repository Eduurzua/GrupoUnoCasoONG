package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Dto.*
import com.example.ongsomosmas.Model.PostMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRemoteDataSource {

    fun registerUser(register: Register, listener: ResponseListener<UserRegister>) {
        val service = RetrofitService.instance
            .create(ApiService::class.java)
            .registerUser(register)

        service.enqueue(object : Callback<RepositoryResponse<UserRegister>> {

            override fun onResponse(
                call: Call<RepositoryResponse<UserRegister>>,
                response: Response<RepositoryResponse<UserRegister>>
            ) {
                val callResponse = response.body()
                println("callResponse Onresponse : " + callResponse)
                println("success : " + callResponse?.success)
                println("data : " + callResponse?.data)
                println("message : " + callResponse?.message)
                println("response.isSuccessful  : " + response.isSuccessful)
                println("token   :"  + callResponse?.data?.token)
                if (callResponse != null) {
                    if (response.isSuccessful && callResponse.success) {
                        println("Entro if successful  :")
                        listener.onResponse(callResponse)

                    } else {
                        println("Entro else de error  :")
                        listener.onError(
                            RepositoryError(
                                message = "El servidor rechazó la solicitud",
                                errors = response.code(),
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<RepositoryResponse<UserRegister>>, t: Throwable) {
                println("callResponse Onfailure : " + t)
                listener.onError(
                    RepositoryError(
                        "Unexpected Error",
                        errors = -1,
                    )
                )
            }

        })
    }

    fun loginUser(login: Login, listener: ResponseListener<UserRegister>) {
        val service = RetrofitService.instance
            .create(ApiService::class.java)
            .loginUser(login)

        service.enqueue(object : Callback<RepositoryResponse<UserRegister>> {
            override fun onResponse(
                call: Call<RepositoryResponse<UserRegister>>,
                response: Response<RepositoryResponse<UserRegister>>
            ) {
                val callResponse = response.body()
                println("callResponse Onresponse : " + callResponse)
                println("success : " + callResponse?.success)
                println("data : " + callResponse?.data)
                println("message : " + callResponse?.message)
                println("response.isSuccessful  : " + response.isSuccessful)
                println("token   :"  + callResponse?.data?.token)
                if (callResponse != null) {
                    if (response.isSuccessful && callResponse.success) {
                        println("Entro if successful  :")
                        listener.onResponse(callResponse)
                    } else {
                        println("Entro else de error  :")
                        listener.onError(
                            RepositoryError(
                                message = "El servidor rechazó la solicitud",
                                errors = response.code(),
                            )
                        )
                    }

                }
            }

            override fun onFailure(call: Call<RepositoryResponse<UserRegister>>, t: Throwable) {
                println("callResponse Onfailure : " + t)

                listener.onError(
                    RepositoryError(
                        "Unexpected Error",
                        errors = -1,
                    )
                )
            }

        })
    }

    fun getNews(limit: Int, listener: ResponseListener<List<News>>) {
        val service = RetrofitService.instance
            .create(ApiService::class.java)
            .getNews(limit)

        service.enqueue(object : Callback<RepositoryResponse<List<News>>> {

            override fun onResponse(call: Call<RepositoryResponse<List<News>>>, response: Response<RepositoryResponse<List<News>>>) {
                val callResponse = response.body()
                if (response.isSuccessful && callResponse != null) {
                    listener.onResponse(
                        callResponse
                    )
                } else {
                    listener.onError(
                        RepositoryError(
                            message = "El servidor rechazó la solicitud",
                            errors = response.code(),

                        )
                    )
                }
            }

            override fun onFailure(call: Call<RepositoryResponse<List<News>>>, t: Throwable) {
                println(" onFailure a news")
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Error inesperado",
                        errors = -1,

                    )
                )
            }

        })
    }

    fun getMembers(limit: Int, listener: ResponseListener<List<Members>>) {
        val service = RetrofitService.instance
            .create(ApiService::class.java)
            .getMembers(limit)

        service.enqueue(object : Callback<RepositoryResponse<List<Members>>> {

            override fun onResponse(call: Call<RepositoryResponse<List<Members>>>, response: Response<RepositoryResponse<List<Members>>>) {
                val callResponse = response.body()
                if (response.isSuccessful && callResponse != null) {
                    listener.onResponse(
                        callResponse
                    )
                } else {
                    listener.onError(
                        RepositoryError(
                            message = "El servidor rechazó la solicitud",
                            errors = response.code(),

                            )
                    )
                }
            }

            override fun onFailure(call: Call<RepositoryResponse<List<Members>>>, t: Throwable) {
                println(" onFailure a news")
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Error inesperado",
                        errors = -1,

                        )
                )
            }

        })
    }

    fun postMessageContact(post: PostMessage, listener: ResponseListener<PostMessage>) {
        val service = RetrofitService.instance
            .create(ApiService::class.java)
            .postMessage(post)

        service.enqueue(object : Callback<RepositoryResponse<PostMessage>> {
            override fun onResponse(
                call: Call<RepositoryResponse<PostMessage>>,
                response: Response<RepositoryResponse<PostMessage>>
            ) {
                val callResponse = response.body()
                if (callResponse != null) {
                    if (response.isSuccessful && callResponse.success) {
                        println("Entro if successful  :")
                        listener.onResponse(callResponse)
                    } else {
                        println("Entro else de error  :")
                        listener.onError(
                            RepositoryError(
                                message = "El servidor rechazó la solicitud",
                                errors = response.code(),
                            )
                        )
                    }

                }
            }

            override fun onFailure(call: Call<RepositoryResponse<PostMessage>>, t: Throwable) {
                println("callResponse Onfailure : " + t)

                listener.onError(
                    RepositoryError(
                        "Unexpected Error",
                        errors = -1,
                    )
                )
            }
        })
    }
}

