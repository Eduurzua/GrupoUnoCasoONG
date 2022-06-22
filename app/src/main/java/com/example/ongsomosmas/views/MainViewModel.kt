package com.example.ongsomosmas.views

import android.content.Context
import android.content.SharedPreferences
import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ongsomosmas.Dto.*
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
    val news = MutableLiveData<List<News?>>(null)
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.tokenFile), Context.MODE_PRIVATE)


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
                editor.putString(R.string.tokenUser.toString(),user.value?.name.toString())
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

    fun getNews(limit: Int) {

        error.value = null

        repository.getNews(limit, object : ResponseListener<List<News>> {

            override fun onResponse(response: RepositoryResponse<List<News>>) {
                val postResponse = response.data
                println("postResponse   : " +postResponse)
                error.value = null
                news.value = postResponse
                println("news.value   : " +news.value)

            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.errors})"
                error.value = message
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

    fun loadUser() : Boolean {
        val preferences =  sharedPreferences.getString(R.string.tokenValue.toString(),"")
        return !preferences.equals("")
        }
}