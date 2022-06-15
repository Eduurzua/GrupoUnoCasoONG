package com.example.ongsomosmas.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ongsomosmas.Model.*
import com.example.ongsomosmas.Post.Repository
import com.example.ongsomosmas.Post.RepositoryError
import com.example.ongsomosmas.Post.RepositoryResponse
import com.example.ongsomosmas.Post.ResponseListener
import com.example.ongsomosmas.databinding.ActivityLoginBinding
import com.example.ongsomosmas.databinding.ActivitySignUpBinding

class MainViewModel(
    private val repository: Repository
): ViewModel() {

    private lateinit var bindingSignUp : ActivitySignUpBinding
    private lateinit var bindingLogin : ActivityLoginBinding
    val success = MutableLiveData<Boolean>(false)
    val message = MutableLiveData<String>(null)
    val user = MutableLiveData<User>(null)
    val token = MutableLiveData<String>(null)
    val error = MutableLiveData<Errors?>(null)

    fun registerUser(newRegister: Register) {
        repository.registerUser(newRegister,object: ResponseListener<UserRegister> {

            override fun onResponse(response: RepositoryResponse<UserRegister>) {
                val postResponse = response.data
                println("Register : " + postResponse)
                success.value = response.success
                message.value = response.message
                user.value = response.data.user
                token.value = response.data.token
            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.errors})"
                error.value = repositoryError.errors
                println("Mensaje Error : " + message)
            }

        })
    }

    fun loginUser(newLogin: Login) {
        repository.loginUser(newLogin,object: ResponseListener<UserRegister> {

            override fun onResponse(response: RepositoryResponse<UserRegister>) {
                val postResponse = response.data
                println("Login : " + postResponse)
                success.value = response.success
                message.value = response.message
                user.value = response.data.user
                token.value = response.data.token
            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.errors})"
                error.value = repositoryError.errors
                println("Mensaje Error : " + message)
            }

        })
    }

    fun clearTextSignUp(){
        bindingSignUp.tiPassword.editText?.text?.clear()
        bindingSignUp.tiEmail.editText?.text?.clear()
        bindingSignUp.tiPassword.editText?.text?.clear()
        bindingSignUp.tiRepeatPassword.editText?.text?.clear()
    }

    fun clearTextLogin(){
        bindingLogin.tiPassword.editText?.text?.clear()
        bindingLogin.tiEmail.editText?.text?.clear()
    }

}