package com.android.fundamentals.workshop03

import androidx.fragment.app.viewModels
import com.android.fundamentals.workshop02_03.AbstractWorkshop2Workshop3Fragment
import com.android.fundamentals.workshop02_03.Workshop2Workshop3ViewModel

class Ws03Fragment : AbstractWorkshop2Workshop3Fragment() {
    
    override val viewModel: Workshop2Workshop3ViewModel by viewModels {
        Ws03ViewModelFactory(applicationContext = requireContext().applicationContext)
    }
    
    companion object {
        fun newInstance(): Ws03Fragment = Ws03Fragment()
    }
}