package com.android.fundamentals.domain.login

sealed class LoginResult {

    class Success : LoginResult()

    sealed class Error : LoginResult() {

        class UserName : Error()

        class Password : Error()
    }
}