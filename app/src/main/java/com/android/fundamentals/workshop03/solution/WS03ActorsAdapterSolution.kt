package com.android.fundamentals.workshop03.solution

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.data.models.Actor
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class WS03ActorsAdapter(
    private val clickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<ActorsViewHolder>() {

    private var actors = listOf<Actor>()

    override fun getItemViewType(position: Int): Int {
        return when (actors.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_ACTORS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyViewHolder(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_actors_empty, parent, false)
            )
            else -> DataViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_actors_data, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                holder.onBind(actors[position])
                holder.itemView.setOnClickListener {
                    clickListener.onClick(actors[position])
                }
            }
            is EmptyViewHolder -> { /* nothing to bind */ }
        }
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }
}

abstract class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class EmptyViewHolder(itemView: View) : ActorsViewHolder(itemView)
private class DataViewHolder(itemView: View) : ActorsViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_avatar)
    private val name: TextView = itemView.findViewById(R.id.tv_actor_name)
    private val oscarState: TextView = itemView.findViewById(R.id.tv_actor_oscar_state)

    fun onBind(actor: Actor) {
        Glide.with(context)
            .load(actor.avatar)
            .apply(imageOption)
            .into(avatar)

        name.text = actor.name

        oscarState.text = context.getString(
            R.string.fragment_actors_avatar_oscar_state_text,
            actor.hasOscar.toString()
        )
    }

    companion object {
        private val imageOption = RequestOptions()
                .placeholder(R.drawable.ic_avatar_placeholder)
                .fallback(R.drawable.ic_avatar_placeholder)
                .circleCrop()
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

private const val VIEW_TYPE_EMPTY = 0
private const val VIEW_TYPE_ACTORS = 1

interface OnRecyclerItemClicked {
    fun onClick(actor: Actor)
}