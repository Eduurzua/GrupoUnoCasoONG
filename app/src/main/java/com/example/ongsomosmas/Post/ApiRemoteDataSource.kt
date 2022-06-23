package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.Model.PostMessage
import com.example.ongsomosmas.Model.Register
import com.example.ongsomosmas.Model.UserRegister
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
                        listener.onResponse(
                            RepositoryResponse(
                                callResponse.success,
                                callResponse.data,
                                callResponse.message,
                                callResponse.data.token
                            )
                        )
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
                        listener.onResponse(
                            RepositoryResponse(
                                callResponse.success,
                                callResponse.data,
                                callResponse.message,
                                callResponse.data.token
                            )
                        )


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

    fun postMessageContact(post: PostMessage, listener: ResponseListener<PostMessage>) {
        val service = RetrofitService.instance
            .create(ApiService::class.java)
            .postMessage(post)

        service.enqueue(object : Callback<RepositoryResponse<PostMessage>>) {
            override fun onResponse(
                call: Call<RepositoryResponse<PostMessage>>,
                response: Response<RepositoryResponse<PostMessage>>
            ) {
                val callResponse = response.body()
                if (callResponse != null){
                    if (response.isSuccessful && callResponse.success){
                        listener.onResponse(
                            RepositoryResponse(
                                callResponse.success,
                                callResponse.data,
                                callResponse.message,
                                callResponse.data.token
                            )
                        )
                    }
                }
            }
        }
    }
}