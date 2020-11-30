package com.android.academy.fundamentals.workshop03.solution

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class WS03CoroutinesFragment : Fragment(R.layout.fragment_coroutines_scope_cancel) {
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val i = 0
    }
}