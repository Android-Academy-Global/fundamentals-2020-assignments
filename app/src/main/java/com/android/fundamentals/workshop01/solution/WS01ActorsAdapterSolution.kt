package com.android.fundamentals.workshop01.solution

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.data.models.Actor

class WS01ActorsAdapterSolution : RecyclerView.Adapter<EmptyViewHolder>() {

    private var actors = listOf<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {
        return EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_actors_empty, parent, false))
    }

    override fun onBindViewHolder(holder: EmptyViewHolder, position: Int) {
        Toast.makeText(holder.itemView.context, "Nothing to bind in default holder", Toast.LENGTH_LONG).show()
    }

    override fun getItemCount(): Int = 1

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }
}

class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)