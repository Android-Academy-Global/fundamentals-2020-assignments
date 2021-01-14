package com.android.fundamentals.workshop01.solution

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.fundamentals.R
import com.android.fundamentals.Router
import com.android.fundamentals.workshop01.Workshop1ViewModelFactory

class Workshop1LoginSolutionFragment : Fragment(R.layout.fragment_workshop_1_login) {

    private val parentRouter: Router? get() = (activity as? Router)

    val viewModel: Workshop1SolutionViewModel by viewModels {
        Workshop1ViewModelFactory(
            applicationContext = requireContext().applicationContext
        )
    }

    private var userNameInput: EditText? = null
    private var passwordInput: EditText? = null
    private var loginBtn: View? = null
    private var loader: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setUpListeners()

        viewModel.loginState.observe(viewLifecycleOwner, { loginResult ->
            loginResult ?: return@observe

            when (loginResult) {
                is LoginResult.Loading -> setLoading(true)
                is LoginResult.Error.UserName -> {
                    setLoading(false)
                    showUserNameError()
                }
                is LoginResult.Error.Password -> {
                    setLoading(false)
                    showPasswordError()
                }
                is LoginResult.Success -> {
                    setLoading(false)
                    showSuccess()
                }
            }
        })
    }

    override fun onDestroyView() {

        userNameInput = null
        passwordInput = null
        loginBtn = null
        loader = null

        super.onDestroyView()
    }

    private fun setLoading(loading: Boolean) {
        loginBtn?.isEnabled = !loading
        loader?.isVisible = loading
    }

    private fun showUserNameError() {
        userNameInput?.error = getString(R.string.ws01_user_name_error)
    }

    private fun showPasswordError() {
        passwordInput?.error = getString(R.string.ws01_password_error)
    }

    private fun showSuccess() {
        loginBtn?.isVisible = false
        parentRouter?.openWorkshop1()
    }

    private fun initViews(view: View) {
        userNameInput = view.findViewById(R.id.fragment_workshop_1_user_name_input)
        passwordInput = view.findViewById(R.id.fragment_workshop_1_password_input)
        loginBtn = view.findViewById(R.id.fragment_workshop_1_login_btn)
        loader = view.findViewById(R.id.fragment_workshop_1_loader)
    }

    private fun setUpListeners() {
        loginBtn?.setOnClickListener {
            tryToLogin()
        }
    }

    private fun tryToLogin() {
        val inputUserName = userNameInput?.text?.toString().orEmpty()
        val inputPassword = passwordInput?.text?.toString().orEmpty()

        viewModel.login(userName = inputUserName, password = inputPassword)
    }

    companion object {
        fun newInstance(): Workshop1LoginSolutionFragment = Workshop1LoginSolutionFragment()
    }
}