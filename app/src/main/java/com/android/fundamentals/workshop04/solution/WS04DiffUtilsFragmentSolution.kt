package com.android.fundamentals.workshop04.solution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.data.models.Actor
import com.android.fundamentals.domain.ActorsDataSource
import com.android.fundamentals.workshop04.WS04ActorsAdapter
import java.util.*
import kotlin.random.Random

class WS04DiffUtilsFragmentSolution : Fragment() {

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
        val originalData: List<Actor> = ActorsDataSource().getActors()
        val shuffledList: List<Actor> = getShuffledActors()
        adapter.bindActors(shuffledList)
        val diffCallback = ActorDiffUtilCallbackSolution(originalData, shuffledList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(adapter)
    }

    private fun getShuffledActors(): List<Actor> {
        val actors: List<Actor> = ActorsDataSource().getActors()
        for (i in 0..5) {
            val from: Int = Random.nextInt(0, actors.size - 1)
            val to: Int = Random.nextInt(0, actors.size - 1)
            if (from == to) {
                continue
            }
            Collections.swap(actors, from, to)
        }
        return actors
    }

    companion object {
        fun newInstance() = WS04DiffUtilsFragmentSolution()
    }
}