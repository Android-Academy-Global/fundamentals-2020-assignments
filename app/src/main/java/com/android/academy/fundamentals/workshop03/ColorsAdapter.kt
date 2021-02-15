package com.android.academy.fundamentals.workshop03

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.R


class ColorsAdapter(context: Context, var colors: ArrayList<Int>) : RecyclerView.Adapter<ColorsAdapter.ColorViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    var mItemAction: View.OnClickListener? = null

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ColorViewHolder = ColorViewHolder(layoutInflater.inflate(R.layout.item_color, parent, false))

    override fun onBindViewHolder(holder: ColorsAdapter.ColorViewHolder, position: Int) {
        val color = colors[position]
        holder.container.setBackgroundColor(color)
        holder.textView.text = "#" + Integer.toHexString(color)
    }

    override fun getItemCount(): Int = colors.size

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    fun addItem(pos: Int, color: Int) {
        colors.add(color)
        notifyItemInserted(pos)
    }

    fun changeItem(pos: Int, color: Int) {
        colors[pos] = color
        notifyItemChanged(pos)
    }

    fun removeItem(pos: Int) {
        colors.remove(pos)
        notifyItemRemoved(pos)
    }

    inner class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: TextView = itemView.findViewById(R.id.color)
        var container = itemView as LinearLayout

        init {
            itemView.setOnClickListener(mItemAction)
        }
    }
}