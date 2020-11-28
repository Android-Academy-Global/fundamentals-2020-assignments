package com.android.fundamentals.workshop01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.fundamentals.R
import com.android.fundamentals.domain.ActorsDataSource

class WS01ActorsFragment : Fragment() {

    // TODO 01: create a nullable var of the "androidx.recyclerview.widget.RecyclerView" class.
//    private var someRecycler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO 02: Open a file: "../res/layout/fragment_actors.xml".
        //  You can see a recycler view: "rv_actors". This is your recycler.
        return inflater.inflate(R.layout.fragment_actors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // TODO 03: First of all, find recycler view inside the fragment "view" and store to a variable.
        // someRecycler = view.

        // TODO 04: Instantiate the WS01ActorsAdapter and assign it to a recycler's adapter.
        // someRecycler?.adapter = WS01ActorsAdapter()
    }

    override fun onStart() {
        super.onStart()

        updateData()
    }

    private fun updateData() {
        // TODO 05: Uncomment and fix "someRecycler" name if needed.
//        (someRecycler?.adapter as? WS01ActorsAdapter)?.apply {
//            bindActors(ActorsDataSource().getActors())
//        }

        // TODO 06: Open the WS01ActorsAdapter.kt file.
    }

    companion object {
        fun newInstance() = WS01ActorsFragment()
    }
}