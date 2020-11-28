package com.android.fundamentals.workshop01

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.data.models.Actor

// TODO 08: Extends this class from "RecyclerView.Adapter<>".
//  Parametrize the generic with EmptyViewHolder.
//  Add a constructor invocation to the RecyclerView.Adapter.
//  Place a cursor on the WS01ActorsAdapter name, press "Alt+Enter", implement all three methods.
class WS01ActorsAdapter {

    private var actors = mutableListOf<Actor>()

    fun bindActors(newActors: List<Actor>) {
        actors = newActors.toMutableList()

        // TODO 09: uncomment.
        // notifyDataSetChanged()
    }

    // TODO 10: With "override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder".
    //  There you have to return an instance of your ViewHolder:
    //  - Create and return EmptyViewHolder instance;
    //  - You need an item view as a parameter. The view of single item inside a recycler.
    //  You have to inflate it with "LayoutInflater.from(parent.context).inflate(...)";
    //  Parent view (recycler) is non null and has a context.
    //  Inflate "../res/layout/item_actors_empty.xml" into parent, don't attach to root.

    // TODO 11: With "override fun onBindViewHolder(holder: EmptyViewHolder, position: Int)".
    //  You haven't got public methods inside your EmptyViewHolder yet. So there is nothing to bind.
    //  Let's just show a Toast that we reach this method.
    //  Holder has an item view. Item view has a context.

    // TODO 12: With "override fun getItemCount(): Int".
    //  return 1 (just for this workshop)
}

// TODO 07: Create a EmptyViewHolder class extends RecyclerView.ViewHolder.
//  Studio will ask you to fix ViewHolder:
//  - Add a default constructor to EmptyViewHolder class and pass a "itemView: View" as parameter;
//  - Add a constructor invocation to the RecyclerView.ViewHolder and pass the "itemView".