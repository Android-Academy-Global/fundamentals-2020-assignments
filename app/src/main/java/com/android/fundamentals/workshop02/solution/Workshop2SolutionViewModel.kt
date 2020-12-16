package com.android.fundamentals.workshop02.solution

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundamentals.domain.login.LoginInteractor
import com.android.fundamentals.domain.login.LoginResult
import kotlinx.coroutines.launch

class Workshop2SolutionViewModel(
    private val interactor: LoginInteractor
) : ViewModel() {

    private val _mutableState = MutableLiveData<State>(State.Default())

    val state: LiveData<State> get() = _mutableState

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            _mutableState.setValue(State.Loading())

            val loginResult = interactor.login(userName = userName, password = password)

            val newViewState = when (loginResult) {
                is LoginResult.Error.UserName -> State.UserNameError()
                is LoginResult.Error.Password -> State.PasswordError()
                is LoginResult.Success -> State.Success()
            }
            _mutableState.setValue(newViewState)
        }
    }
    
    sealed class State {
        class Default : State()
        class Loading : State()
        class UserNameError : State()
        class PasswordError : State()
        class Success : State()
    }
}