package com.example.ongsomosmas.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(\\.)(.{1,})"
    val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"

    val showToast: MutableLiveData<Boolean> = MutableLiveData()

    fun validateEmail(email: String) : Boolean{
        val result = EMAIL_REGEX.toRegex().matches(email)
        return if(email.isEmpty()){
            false
        }else result
    }

    fun validatePassword(password: String) : Boolean{
        val result = PASSWORD_REGEX.toRegex().matches(password)
        return if(password.isEmpty()){
            false
        }else result
    }

    //*buscar mejor validador de boleanos*//
    fun validateAll(email:String, password: String) : Boolean {
        val result = validateEmail(email).equals(validatePassword(password))

        return result

    }

}