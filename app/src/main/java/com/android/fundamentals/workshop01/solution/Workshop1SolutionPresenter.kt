package com.android.fundamentals.workshop01.solution

import com.android.fundamentals.domain.LoginInteractor

class Workshop1SolutionPresenter(
    private val interactor: LoginInteractor
) {

    private var view: Workshop1SolutionView? = null

    fun attachView(view: Workshop1SolutionView) {
        this.view = view
    }

    fun detachView() {
        interactor.cancelLogin()
        this.view = null
    }

    fun login(userName: String, password: String) {
        view?.setLoading(loading = true)

        interactor.login(
            userName = userName,
            password = password,
            listener = object : LoginInteractor.LoginListener {
                override fun onUserNameError() {
                    view?.showUserNameError()
                    view?.setLoading(loading = false)
                }

                override fun onPasswordError() {
                    view?.showPasswordError()
                    view?.setLoading(loading = false)
                }

                override fun onSuccess() {
                    view?.showSuccess()
                    view?.setLoading(loading = false)
                }
            }
        )
    }
}