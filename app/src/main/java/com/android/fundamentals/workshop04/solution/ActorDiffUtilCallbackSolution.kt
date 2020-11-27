package com.android.fundamentals.workshop04.solution

import androidx.recyclerview.widget.DiffUtil
import com.android.fundamentals.data.models.Actor

class ActorDiffUtilCallbackSolution(
    private val oldList: List<Actor>,
    private val newList: List<Actor>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size
}