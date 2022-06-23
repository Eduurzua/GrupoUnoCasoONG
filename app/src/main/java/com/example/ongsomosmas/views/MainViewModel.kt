package com.example.ongsomosmas.views

import android.content.Context
import android.content.SharedPreferences
import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ongsomosmas.Dto.*
import com.example.ongsomosmas.Dto.PostMessage
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
    val news = MutableLiveData<List<News>>(null)
    val members = MutableLiveData<List<Members>>(null)
    val new = MutableLiveData<News>(null)
    val postMessage = MutableLiveData<PostMessage?>(null)
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
            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.errors})"
                error.value = message
            }

        })
    }

    fun loginUser(newLogin: Login) {
        repository.loginUser(newLogin, object : ResponseListener<UserRegister> {

            override fun onResponse(response: RepositoryResponse<UserRegister>) {
                val editor = sharedPreferences.edit()
                success.value = response.success
                message.value = response.message
                user.value = response.data.user
                token.value = response.data.token
                editor.putString(R.string.tokenValue.toString(),token.value.toString())
                editor.putString(R.string.tokenUser.toString(),user.value?.name.toString())
                editor.apply()
            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.errors})"
                error.value = message

            }

        })
    }

    fun postMessage(newPostMessage: PostMessage) {
        repository.postMessageContact(newPostMessage, object : ResponseListener<PostMessage> {

            override fun onResponse(response: RepositoryResponse<PostMessage>) {
                success.value = response.success
                message.value = response.message
                postMessage.value = response.data
            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.errors})"
                error.value = message

            }

        })
    }

    fun getNews(limit: Int) {

        error.value = null

        repository.getNews(limit, object : ResponseListener<List<News>> {

            override fun onResponse(response: RepositoryResponse<List<News>>) {
                val postResponse = response.data
                error.value = null
                news.value = postResponse
            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.errors})"
                error.value = message
            }

        })
    }

    fun getMembers(limit: Int) {

        error.value = null

        repository.getMembers(limit, object : ResponseListener<List<Members>> {

            override fun onResponse(response: RepositoryResponse<List<Members>>) {
                val postResponse = response.data
                error.value = null
                members.value = postResponse
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

    fun loadUser() : Boolean {
        val preferences =  sharedPreferences.getString(R.string.tokenValue.toString(),"")
        return !preferences.equals("")
        }

    fun findUser(): String? {
        return sharedPreferences.getString(R.string.tokenUser.toString(), "")
    }

    fun selectNew(position : Int){
        new.value = news.value?.get(position)
    }

}