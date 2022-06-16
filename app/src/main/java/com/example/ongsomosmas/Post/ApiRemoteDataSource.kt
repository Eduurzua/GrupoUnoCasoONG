package com.example.ongsomosmas.Post

import com.example.ongsomosmas.Model.Login
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
                if (response.isSuccessful && null != callResponse) {
                    listener.onResponse(
                        RepositoryResponse(
                            callResponse.success,
                            callResponse.data,
                            callResponse.message,
                            null
                        )
                    )
                } else {
                    listener.onError(
                        RepositoryError(
                            callResponse?.message ?: run { "Unexpected Error" },
                            callResponse?.errors
                        )
                    )
                }
            }

            override fun onFailure(call: Call<RepositoryResponse<UserRegister>>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        "Unexpected Error",
                        null
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
                println("callResponse : " + callResponse)
                println("success : " + callResponse?.success)
                println("data : " + callResponse?.data)
                println("message : " + callResponse?.message)
                println("response.isSuccessful  : " + response.isSuccessful)
                if (callResponse != null) {
                    if (response.isSuccessful && callResponse.success) {
                        println("Entro if successful  :")
                        listener.onResponse(
                            RepositoryResponse(
                                callResponse.success,
                                callResponse.data,
                                callResponse.message,
                                null
                            )
                        )


                    } else {
                        println("Entro else de error  :")
                        listener.onError(
                            RepositoryError(
                                callResponse?.message ?: run { "Unexpected Error" },
                                callResponse?.errors
                            )
                        )
                    }

                }
            }

            override fun onFailure(call: Call<RepositoryResponse<UserRegister>>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        "Unexpected Error",
                        null
                    )
                )
            }

        })
    }
}