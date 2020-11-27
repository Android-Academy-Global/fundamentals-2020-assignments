package com.android.fundamentals.workshop04

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

class WS04ActorsAdapter : RecyclerView.Adapter<DataViewHolder>() {

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_placeholder)
        .circleCrop()

    private var actors = mutableListOf<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actors_data, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.onBind(imageOption, actors[position])
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>) {
        actors = newActors.toMutableList()
    }
}

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val avatar: ImageView? = itemView.findViewById(R.id.iv_actor_avatar)
    val name: TextView? = itemView.findViewById(R.id.tv_actor_name)
    val oscarState: TextView? = itemView.findViewById(R.id.tv_actor_oscar_state)

    fun onBind(options: RequestOptions, actor: Actor) {
        Glide.with(context)
            .load(actor.avatar)
            .apply(options)
            .into(avatar)

        name?.text = actor.name

        oscarState?.text = context.getString(
            R.string.fragment_actors_avatar_oscar_state_text,
            actor.hasOscar.toString()
        )
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context
