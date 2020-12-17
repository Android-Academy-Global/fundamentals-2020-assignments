package com.android.fundamentals.workshop01

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.android.fundamentals.R
import com.android.fundamentals.domain.login.LoginInteractor
import kotlinx.coroutines.Dispatchers

class Workshop1Fragment : Fragment(R.layout.fragment_workshop_1_workshop_2), Workshop1View {

    // Creating presenter instance
    private val presenter = Workshop1Presenter(
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

        // Attaching view to the presenter
        presenter.attachView(this)
    }

    override fun onDestroyView() {
        // Detaching view from the presenter
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
        //TODO 01: Depending on "loading" value, set "loader" visibility = View.VISIBLE/GONE.
        // And opposite, set "loginBtn" disabled/enabled.
    }

    override fun showUserNameError() {
        //TODO 02: Set error for "userNameInput" from strings resources (ws01_ws02_user_name_error).
    }

    override fun showPasswordError() {
        //TODO 03: Set error for "passwordInput" from strings resources (ws01_ws02_password_error).
    }

    override fun showSuccess() {
        //TODO 04: Set "loginBtn" visibility = View.INVISIBLE.
        // And opposite, set "loginSuccess" visibility = View.VISIBLE.
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

        //TODO 05: Call presenter's "login()" method.
    }

    companion object {
        fun newInstance(): Workshop1Fragment = Workshop1Fragment()
    }
}