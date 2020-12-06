package com.android.foundamentals.workshop01.solution

import com.android.fundamentals.workshop01.solution.Workshop1SolutionView

internal class Workshop1SolutionViewMock : Workshop1SolutionView {

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