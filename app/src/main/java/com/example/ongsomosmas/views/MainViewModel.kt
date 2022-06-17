package com.example.ongsomosmas.views

import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ongsomosmas.Model.*
import com.example.ongsomosmas.Post.Repository
import com.example.ongsomosmas.Post.RepositoryError
import com.example.ongsomosmas.Post.RepositoryResponse
import com.example.ongsomosmas.Post.ResponseListener
import com.example.ongsomosmas.databinding.ActivityLoginBinding
import com.example.ongsomosmas.databinding.ActivitySignUpBinding
import java.util.regex.Pattern


class MainViewModel(
    private val repository: Repository,

): ViewModel() {

    val success = MutableLiveData(false)
    val message = MutableLiveData<String>(null)
    val user = MutableLiveData<User>(null)
    val error = MutableLiveData<Errors?>(null)
    val enableButton = MutableLiveData<Boolean>(false)
    val enableRegister= MutableLiveData<Boolean>(false)


    fun registerUser(newRegister: Register) {
        repository.registerUser(newRegister,object: ResponseListener<UserRegister> {

            override fun onResponse(response: RepositoryResponse<UserRegister>) {
                val postResponse = response.data
                println("Register : " + postResponse)
                success.value = response.success
                message.value = response.message
                user.value = response.data.user
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
            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.errors})"
                error.value = repositoryError.errors
                println("Mensaje Error : " + message)
            }

        })
    }

    private fun validateEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            false
        } else PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun samePassword(password: String, passwordRepeat: String): Boolean {
        return password == passwordRepeat
    }

    private fun nameLength(name: String): Boolean {
        return name.length > 6
    }

    private fun validatePassword(password: String): Boolean {
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +          //al menos 1 dígito
                    "(?=.*[A-Z])" +          //al menos una Mayúscula
                    ".{8,}" +                //minimo 8 de largo
                    "$"
        )
        return if (password.isEmpty()) {
            false
        } else passwordRegex.matcher(password).matches()
    }

    fun validate(email: String, password: String){
        val respuesta = validateEmail(email) && validatePassword(password)
        enableButton.value = !respuesta
    }

    fun validateRegister(email: String, password: String, passwordRepeat: String, name: String){
        val response = validateEmail(email) && validatePassword(password) && samePassword(password,passwordRepeat) && nameLength(name)
        enableRegister.value = !response
    }


}