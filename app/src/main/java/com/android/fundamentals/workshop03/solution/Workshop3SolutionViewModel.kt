package com.android.fundamentals.workshop03.solution

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.fundamentals.domain.LoginInteractor

class Workshop3SolutionViewModel(
    private val interactor: LoginInteractor
) : ViewModel() {

    private val _mutableState = MutableLiveData<State>(State.Init())

    val state: LiveData<State> get() = _mutableState

    override fun onCleared() =
        interactor.cancelLogin()

    fun login(userName: String, password: String) {
        _mutableState.postValue(State.Loading())

        interactor.login(
            userName = userName,
            password = password,
            listener = object : LoginInteractor.LoginListener {

                override fun onUserNameError() =
                    _mutableState.postValue(State.UserNameError())

                override fun onPasswordError() =
                    _mutableState.postValue(State.PasswordError())

                override fun onSuccess() =
                    _mutableState.postValue(State.Success())
            }
        )
    }

    sealed class State {
        class Init : State()
        class Loading : State()
        class UserNameError : State()
        class PasswordError : State()
        class Success : State()
    }
}