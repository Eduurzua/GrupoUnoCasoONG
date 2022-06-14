package com.example.ongsomosmas.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.Model.Register
import com.example.ongsomosmas.Post.PostRepository
import com.example.ongsomosmas.Post.RepositoryError
import com.example.ongsomosmas.Post.RepositoryResponse
import com.example.ongsomosmas.Post.ResponseListener

class MainViewModel(
    private val repository: PostRepository
): ViewModel() {

    val register = MutableLiveData<Register?>(null)
    val login = MutableLiveData<Login?>(null)
    val loading = MutableLiveData<Boolean>(false)
    val error = MutableLiveData<String?>(null)

    fun registerUser(newRegister: Register) {
        repository.registerUser(newRegister,object: ResponseListener<Register> {

            override fun onResponse(response: RepositoryResponse<Register>) {
                val postResponse = response.data

                loading.value = false
                error.value = null
                register.value = postResponse
            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.code})"

                loading.value = false
                error.value = message
                register.value = null
            }

        })
    }

    fun loginUser(newLogin: Login) {
        repository.loginUser(newLogin,object: ResponseListener<Login> {

            override fun onResponse(response: RepositoryResponse<Login>) {
                val postResponse = response.data

                loading.value = false
                error.value = null
                login.value = postResponse
            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.code})"

                loading.value = false
                error.value = message
                login.value = null
            }

        })
    }

}