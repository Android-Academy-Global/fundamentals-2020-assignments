package com.android.fundamentals.workshop03

import androidx.fragment.app.viewModels
import com.android.fundamentals.workshop02_03.AbstractWorkshop2Workshop3Fragment
import com.android.fundamentals.workshop02_03.Workshop2Workshop3ViewModel

class Workshop3Fragment : AbstractWorkshop2Workshop3Fragment() {

    override val viewModel: Workshop2Workshop3ViewModel by viewModels {
        Workshop3ViewModelFactory(applicationContext = requireContext().applicationContext)
    }

    companion object {
        fun newInstance(): Workshop3Fragment = Workshop3Fragment()
    }
}