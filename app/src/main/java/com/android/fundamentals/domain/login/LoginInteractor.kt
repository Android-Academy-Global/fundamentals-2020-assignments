package com.android.fundamentals.domain.login

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoginInteractor(private val dispatcher: CoroutineDispatcher) {

    suspend fun login(userName: String, password: String): LoginResult =
        withContext(dispatcher) {
            delay(DELAY_MILLIS)
            when {
                userName.isEmpty() -> LoginResult.Error.UserName()
                password.isEmpty() -> LoginResult.Error.Password()
                else -> LoginResult.Success()
            }
        }

    companion object {
        const val DELAY_MILLIS: Long = 3_000
    }
}