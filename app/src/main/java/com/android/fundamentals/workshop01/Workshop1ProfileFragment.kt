package com.android.fundamentals.workshop01

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.fundamentals.R
import com.android.fundamentals.Router

class Workshop1ProfileFragment : Fragment(R.layout.fragment_workshop_1_profile) {

    private val parentRouter: Router? get() = (activity as? Router)

    val viewModel: Workshop1ViewModel by viewModels {
        Workshop1ViewModelFactory(
            applicationContext = requireContext().applicationContext
        )
    }

    private var logoutBtn: View? = null
    private var loader: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setUpListeners()

        viewModel.logoutState.observe(viewLifecycleOwner, { loginResult ->
            loginResult ?: return@observe

            when (loginResult) {
                is LogoutResult.Loading -> setLoading(true)
                is LogoutResult.Success -> {
                    setLoading(false)
                    showSuccess()
                }
                is LogoutResult.Error -> setLoading(false)
            }
        })
    }

    override fun onDestroyView() {

        logoutBtn = null
        loader = null

        super.onDestroyView()
    }

    private fun setLoading(loading: Boolean) {
        logoutBtn?.isEnabled = !loading
        loader?.isVisible = loading
    }

    private fun showSuccess() {
        logoutBtn?.isVisible = false
        parentRouter?.openWorkshop1()
    }

    private fun initViews(view: View) {
        logoutBtn = view.findViewById(R.id.fragment_workshop_1_logout_btn)
        loader = view.findViewById(R.id.fragment_workshop_1_loader)
    }

    private fun setUpListeners() {
        logoutBtn?.setOnClickListener {
            tryToLogout()
        }
    }

    private fun tryToLogout() {
        viewModel.logout()
    }

    companion object {
        fun newInstance(): Workshop1ProfileFragment = Workshop1ProfileFragment()
    }
}