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

    fun onDestroy() {
        presenterScope.cancel()
    }

    fun login(userName: String, password: String) {
        presenterScope.launch {
            //TODO 06: Set loading to true (using view method)

            val loginResult = interactor.login(userName = userName, password = password)
            //TODO 07: Handle loginResult and show success or error depend on it (using view methods)
            /*when (loginResult) {
            }*/

            //TODO 08: Set loading to false (using view method)
        }
    }
}