package com.example.ongsomosmas.views

import android.app.Application
import android.content.Context
import androidx.core.util.PatternsCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ongsomosmas.Model.Login
import com.example.ongsomosmas.Model.Register
import com.example.ongsomosmas.Model.User
import com.example.ongsomosmas.Model.UserRegister
import com.example.ongsomosmas.Post.Repository
import com.example.ongsomosmas.Post.RepositoryError
import com.example.ongsomosmas.Post.RepositoryResponse
import com.example.ongsomosmas.Post.ResponseListener
import com.example.ongsomosmas.R
import java.util.regex.Pattern


class MainViewModel(private val repository: Repository, context: Context) : ViewModel(
)  {

    val success = MutableLiveData(false)
    val message = MutableLiveData<String>(null)
    val user = MutableLiveData<User>(null)
    val error = MutableLiveData<String?>(null)
    val enableButton = MutableLiveData<Boolean>(false)
    val enableRegister = MutableLiveData<Boolean>(false)
    val samePassword = MutableLiveData<Boolean>(true)
    val token = MutableLiveData<String?>(null)
    val sharedPreferences = context.getSharedPreferences(context.getString(R.string.tokenFile), Context.MODE_PRIVATE)


     init {
         token.value = sharedPreferences.getString(R.string.tokenValue.toString(), "")
     }

    fun registerUser(newRegister: Register) {
        repository.registerUser(newRegister, object : ResponseListener<UserRegister> {

            override fun onResponse(response: RepositoryResponse<UserRegister>) {
                success.value = response.success
                message.value = response.message
                user.value = response.data.user
                token.value = response.data.token
                println("success.value   : " + success.value)
                println("message.value   : " + message.value)
                println("user.value   : " + user.value)
                println("token.value   : " + token.value)

            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.errors})"
                error.value = message
                println("Mensaje Error : " + message)
                println("valor Error : " + error.value)
            }

        })
    }

    fun loginUser(newLogin: Login) {
        repository.loginUser(newLogin, object : ResponseListener<UserRegister> {

            override fun onResponse(response: RepositoryResponse<UserRegister>) {
                println("Login")
                val editor = sharedPreferences.edit()
                success.value = response.success
                message.value = response.message
                user.value = response.data.user
                token.value = response.data.token
                editor.putString(R.string.tokenValue.toString(),token.value.toString())
                editor.apply()
                println("success.value   : " + success.value)
                println("message.value   : " + message.value)
                println("user.value   : " + user.value)
                println("token.value   : " + token.value)
            }

            override fun onError(repositoryError: RepositoryError) {
                println("Login Error")
                val message = "${repositoryError.message} (code: ${repositoryError.errors})"
                error.value = message
                println("Mensaje Error : " + message)
                println("valor Error : " + error.value)
            }

        })
    }

    private fun validateEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            false
        } else PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
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

    fun validate(email: String, password: String) {
        val respuesta = validateEmail(email) && validatePassword(password)
        enableButton.value = respuesta
    }

    fun validateContact(nombreyApellido: String, email: String, mensaje: String ) {
        val respuestaContact = validateEmail(email) && validateIsNull(nombreyApellido) && validateIsNull(mensaje)
        enableButton.value = respuestaContact
    }

    private fun validateIsNull(content: String): Boolean {
        return content.isNotEmpty()
    }

    fun validateRegister(email: String, password: String, passwordRepeat: String, name: String) {
        val response = validateEmail(email) && validatePassword(password) && samePassword(
            password,
            passwordRepeat
        ) && nameLength(name)
        enableRegister.value = response
    }

    private fun samePassword(password: String, passwordRepeat: String): Boolean {
        return (password == passwordRepeat)
    }

    fun samePasswordRepeat(password: String, passwordRepeat: String) {
        samePassword.value = (password.isNullOrEmpty() && passwordRepeat.isNullOrEmpty())
                || (password == passwordRepeat)
    }


}