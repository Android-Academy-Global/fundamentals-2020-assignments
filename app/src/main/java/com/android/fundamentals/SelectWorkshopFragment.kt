package com.android.fundamentals

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class SelectWorkshopFragment : Fragment(R.layout.fragment_select_workshop) {

    private val parentRouter: Router? get() = (activity as? Router)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.fragment_select_workshop_open_1).setOnClickListener {
            parentRouter?.openWorkshop1()
        }

        view.findViewById<View>(R.id.fragment_select_workshop_open_2).setOnClickListener {
            parentRouter?.openWorkshop2()
        }

        view.findViewById<View>(R.id.fragment_select_workshop_open_3).setOnClickListener {
            parentRouter?.openWorkshop3()
        }
    }

    companion object {
        fun newInstance(): SelectWorkshopFragment = SelectWorkshopFragment()
    }
}