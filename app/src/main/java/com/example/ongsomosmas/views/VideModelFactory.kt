package com.example.ongsomosmas.views

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ongsomosmas.Post.ApiRemoteDataSource
import com.example.ongsomosmas.Post.Repository

class VideModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val remoteDataSource = ApiRemoteDataSource()
        val repository = Repository(remoteDataSource)

        return MainViewModel(repository) as T
    }
}