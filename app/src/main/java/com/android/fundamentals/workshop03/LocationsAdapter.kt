package com.android.fundamentals.workshop03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.domain.location.Location

class LocationsAdapter : ListAdapter<Location, LocationViewHolder>(LocationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder =
        LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
        )

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val coordinatesTextView: TextView = itemView.findViewById(R.id.item_location_coordinates)
    private val titleTextView: TextView = itemView.findViewById(R.id.item_location_title)

    fun bind(location: Location) {
        coordinatesTextView.text = "${location.latitude}, ${location.longitude}"
        titleTextView.text = location.title
    }
}

private class LocationDiffCallback : DiffUtil.ItemCallback<Location>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean =
        (oldItem == newItem)

    override fun getChangePayload(oldItem: Location, newItem: Location): Any = payload
}