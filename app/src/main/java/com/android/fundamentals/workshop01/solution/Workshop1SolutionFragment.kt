package com.android.fundamentals.workshop01.solution

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.android.fundamentals.R
import com.android.fundamentals.domain.login.LoginInteractor
import com.android.fundamentals.workshop01.Workshop1View
import kotlinx.coroutines.Dispatchers

class Workshop1SolutionFragment : Fragment(R.layout.fragment_workshop_1_workshop_2), Workshop1View {

    private val presenter = Workshop1SolutionPresenter(
        interactor = LoginInteractor(dispatcher = Dispatchers.Default),
        mainDispatcher = Dispatchers.Main.immediate
    )

    private var userNameInput: EditText? = null
    private var passwordInput: EditText? = null
    private var loginBtn: View? = null
    private var loader: View? = null
    private var loginSuccess: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setUpListeners()

        presenter.attachView(this)
    }

    override fun onDestroyView() {
        presenter.detachView()

        userNameInput = null
        passwordInput = null
        loginBtn = null
        loader = null
        loginSuccess = null

        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.onDestroy()

        super.onDestroy()
    }

    override fun setLoading(loading: Boolean) {
        loginBtn?.isEnabled = !loading
        loader?.isVisible = loading
    }

    override fun showUserNameError() {
        userNameInput?.error = getString(R.string.ws01_ws02_user_name_error)
    }

    override fun showPasswordError() {
        passwordInput?.error = getString(R.string.ws01_ws02_password_error)
    }

    override fun showSuccess() {
        loginBtn?.isVisible = false
        loginSuccess?.isVisible = true
    }

    private fun initViews(view: View) {
        userNameInput = view.findViewById(R.id.fragment_workshop_1_workshop_2_user_name_input)
        passwordInput = view.findViewById(R.id.fragment_workshop_1_workshop_2_password_input)
        loginBtn = view.findViewById(R.id.fragment_workshop_1_workshop_2_login_btn)
        loader = view.findViewById(R.id.fragment_workshop_1_workshop_2_loader)
        loginSuccess = view.findViewById(R.id.fragment_workshop_1_workshop_2_login_success)
    }

    private fun setUpListeners() {
        loginBtn?.setOnClickListener {
            tryToLogin()
        }
    }

    private fun tryToLogin() {
        val inputUserName = userNameInput?.text?.toString().orEmpty()
        val inputPassword = passwordInput?.text?.toString().orEmpty()

        presenter.login(userName = inputUserName, password = inputPassword)
    }

    companion object {
        fun newInstance(): Workshop1SolutionFragment = Workshop1SolutionFragment()
    }
}