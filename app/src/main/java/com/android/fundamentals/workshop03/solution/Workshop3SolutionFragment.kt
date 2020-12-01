package com.android.fundamentals.workshop03.solution

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.fundamentals.R

class Workshop3SolutionFragment : Fragment(R.layout.fragment_workshop_1) {

    private val viewModel: Workshop3SolutionViewModel by viewModels { Workshop3SolutionViewModelFactory() }

    private var userNameInput: EditText? = null
    private var passwordInput: EditText? = null
    private var loginBtn: View? = null
    private var loader: View? = null
    private var loginSuccess: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setUpListeners()

        viewModel.state.observe(this.viewLifecycleOwner, this::setState)
    }

    override fun onDestroyView() {
        userNameInput = null
        passwordInput = null
        loginBtn = null
        loader = null
        loginSuccess = null

        super.onDestroyView()
    }

    private fun setState(state: Workshop3SolutionViewModel.State) =
        when (state) {
            is Workshop3SolutionViewModel.State.Init -> {
                setLoading(loading = false)
            }
            is Workshop3SolutionViewModel.State.Loading -> {
                setLoading(loading = true)
            }
            is Workshop3SolutionViewModel.State.UserNameError -> {
                setLoading(loading = false)
                showUserNameError()
            }
            is Workshop3SolutionViewModel.State.PasswordError -> {
                setLoading(loading = false)
                showPasswordError()
            }
            is Workshop3SolutionViewModel.State.Success -> {
                setLoading(loading = false)
                showSuccess()
            }
        }

    private fun setLoading(loading: Boolean) {
        loginBtn?.isEnabled = !loading
        loader?.isVisible = loading
    }

    private fun showUserNameError() {
        userNameInput?.error = getString(R.string.user_name_error)
    }

    private fun showPasswordError() {
        passwordInput?.error = getString(R.string.password_error)
    }

    private fun showSuccess() {
        loginBtn?.isVisible = false
        loginSuccess?.isVisible = true
    }

    private fun initViews(view: View) {
        userNameInput = view.findViewById(R.id.fragment_workshop_1_user_name_input)
        passwordInput = view.findViewById(R.id.fragment_workshop_1_password_input)
        loginBtn = view.findViewById(R.id.fragment_workshop_1_login_btn)
        loader = view.findViewById(R.id.fragment_workshop_1_loader)
        loginSuccess = view.findViewById(R.id.fragment_workshop_1_login_success)
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
        fun newInstance(): Workshop3SolutionFragment = Workshop3SolutionFragment()
    }
}