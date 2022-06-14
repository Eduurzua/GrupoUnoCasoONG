package com.example.ongsomosmas.Post

interface ResponseListener<T> {

    fun onResponse(response: RepositoryResponse<T>)

    fun onError(repositoryError: RepositoryError)

}