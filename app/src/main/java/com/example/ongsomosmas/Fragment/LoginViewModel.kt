package com.example.ongsomosmas.Fragment

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(\\.)(.{1,})"
    val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"

    fun validateEmail(email: String) : Boolean{
        val result = EMAIL_REGEX.toRegex().matches(email)
        return if(email.isEmpty()){
            false
        }else result
    }

    fun validatePassword(password: String) : Boolean{
        val result : Boolean
        return if (password.length < 8){
            false
        } else if (password.isEmpty()) {
            false
        } else {
            result = PASSWORD_REGEX.toRegex().matches(password)
            result
        }
    }
}