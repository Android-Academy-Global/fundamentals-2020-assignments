package com.android.fundamentals.workshop02

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundamentals.domain.login.LoginInteractor
import kotlinx.coroutines.launch

class Workshop2ViewModel(
    private val interactor: LoginInteractor
) : ViewModel() {

    //TODO 06: Create private MutableLiveData for state storing. Set initial state State.Init

    //TODO 07: Create public LiveData (only getter) for provide state outside. Init with your private MutableLiveData

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            //TODO 08: Set loading state to private liveData

            val loginResult = interactor.login(userName = userName, password = password)

            //TODO 09: Handle loginResult and create new state depend on it (success or error)

            //TODO 10: Set new state to private liveData
        }
    }

    sealed class State {
        class Init : State()
        class Loading : State()
        class UserNameError : State()
        class PasswordError : State()
        class Success : State()
    }
}