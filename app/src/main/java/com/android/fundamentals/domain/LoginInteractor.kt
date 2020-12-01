package com.android.fundamentals.domain

import android.os.Handler
import android.os.Looper

class LoginInteractor {

    private val handler = Handler(Looper.getMainLooper())

    fun login(userName: String, password: String, listener: LoginListener) =
        handler.postDelayed({
            when {
                userName.isEmpty() -> listener.onUserNameError()
                password.isEmpty() -> listener.onPasswordError()
                else -> listener.onSuccess()
            }
        }, 5_000)

    fun cancelLogin() = handler.removeCallbacksAndMessages(null)

    interface LoginListener {
        fun onUserNameError()
        fun onPasswordError()
        fun onSuccess()
    }
}