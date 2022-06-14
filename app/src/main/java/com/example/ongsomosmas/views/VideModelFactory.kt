package com.example.ongsomosmas.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ongsomosmas.Post.PostRemoteDataSource
import com.example.ongsomosmas.Post.PostRepository

class VideModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val remoteDataSource = PostRemoteDataSource()
        val repository = PostRepository(remoteDataSource)

        return MainViewModel(repository) as T
    }
}