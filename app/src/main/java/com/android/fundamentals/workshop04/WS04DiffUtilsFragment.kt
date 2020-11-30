package com.android.fundamentals.workshop04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.data.models.Actor
import com.android.fundamentals.domain.ActorsDataSource

class WS04DiffUtilsFragment : Fragment() {

    private lateinit var adapter: WS04ActorsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workshop_04, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler: RecyclerView = view.findViewById(R.id.rv_actors)
        adapter = WS04ActorsAdapter()
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        view.findViewById<View>(R.id.shuffle_button).setOnClickListener {
            shuffleActors()
        }
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        adapter.bindActors(ActorsDataSource().getActors())
        adapter.notifyDataSetChanged()
    }

    private fun shuffleActors() {
        val shuffledList: List<Actor> = ActorsDataSource().getActors().shuffled()
        adapter.bindActors(shuffledList)
        // TODO: Replace notifyDataSetChanged for updating the recyclerView to DiffUtil.Callback.
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = WS04DiffUtilsFragment()
    }
}