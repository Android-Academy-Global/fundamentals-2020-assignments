package com.android.fundamentals.workshop01_02.solution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.domain.ActorsDataSource

class WS01ActorsFragment : Fragment() {

    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_actors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.rv_actors)
        recycler?.adapter = WS01ActorsAdapter()
    }

    override fun onStart() {
        super.onStart()

        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? WS01ActorsAdapter)?.apply {
            bindActors(ActorsDataSource().getActors())
            notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance() = WS01ActorsFragment()
    }
}