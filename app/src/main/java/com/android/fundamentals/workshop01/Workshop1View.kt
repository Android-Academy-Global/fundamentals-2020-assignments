package com.android.fundamentals.workshop01

interface Workshop1View {

    fun setLoading(loading: Boolean)

    fun showUserNameError()

    fun showPasswordError()

    fun showSuccess()
}