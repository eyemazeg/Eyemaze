package com.eyemazegraphics.utils

object Validators {
    fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPhoneValid(phone: CharSequence): Boolean {
        return android.util.Patterns.PHONE.matcher(phone).matches()
    }
}
