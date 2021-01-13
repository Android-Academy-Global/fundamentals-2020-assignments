package com.android.fundamentals.workshop02

import androidx.fragment.app.viewModels
import com.android.fundamentals.workshop02_03.AbstractWorkshop2Workshop3Fragment
import com.android.fundamentals.workshop02_03.Workshop2Workshop3ViewModel

class Workshop2Fragment : AbstractWorkshop2Workshop3Fragment() {

    override val viewModel: Workshop2Workshop3ViewModel by viewModels {
        Workshop2ViewModelFactory(
            applicationContext = requireContext().applicationContext
        )
    }

    companion object {
        fun newInstance(): Workshop2Fragment = Workshop2Fragment()
    }
}