package com.android.foundamentals.workshop01.solution

import com.android.fundamentals.workshop01.Workshop1View

internal class Workshop1SolutionViewMock : Workshop1View {

    var isLoading: Boolean = false
        private set

    var userNameErrorShownTimes: Int = 0
        private set

    var passwordErrorShownTimes: Int = 0
        private set

    var successShownTimes: Int = 0
        private set

    override fun setLoading(loading: Boolean) {
        isLoading = loading
    }

    override fun showUserNameError() {
        userNameErrorShownTimes++
    }

    override fun showPasswordError() {
        passwordErrorShownTimes++
    }

    override fun showSuccess() {
        successShownTimes++
    }
}