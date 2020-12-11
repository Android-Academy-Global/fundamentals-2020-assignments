package com.android.fundamentals.workshop01

import com.android.fundamentals.domain.login.LoginInteractor
import kotlinx.coroutines.*

class Workshop1Presenter(
    private val interactor: LoginInteractor,
    private val mainDispatcher: CoroutineDispatcher
) {

    private val presenterScope: CoroutineScope =
        CoroutineScope(SupervisorJob() + mainDispatcher)

    private var view: Workshop1View? = null

    fun attachView(view: Workshop1View) {
        this.view = view
    }

    fun detachView() {
        presenterScope.cancel()
        this.view = null
    }

    fun login(userName: String, password: String) {
        presenterScope.launch {
            //TODO 01: Set loading to true

            val loginResult = interactor.login(userName = userName, password = password)
            //TODO 02: Handle loginResult and show success or error depend on it
            /*when (loginResult) {
            }*/

            //TODO 03: Set loading to false
        }
    }
}