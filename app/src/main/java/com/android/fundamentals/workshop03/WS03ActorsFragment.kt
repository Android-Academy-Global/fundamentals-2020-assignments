package com.android.fundamentals.workshop03


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.data.models.Actor
import com.android.fundamentals.domain.ActorsDataSource
import com.google.android.material.snackbar.Snackbar


class WS03ActorsFragment : Fragment() {

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
        //TODO 5: pass click listener to adapter
        recycler?.adapter = WS03ActorsAdapter()
    }

    override fun onStart() {
        super.onStart()

        updateData()
    }

    override fun onDetach() {
        recycler = null

        super.onDetach()
    }

    private fun updateData() {
        (recycler?.adapter as? WS03ActorsAdapter)?.apply {
            bindActors(ActorsDataSource().getActors())
            notifyDataSetChanged()
        }
    }

    private fun doOnClick(actor: Actor) {
        recycler?.let { rv ->
            Snackbar.make(
                        rv,
                        rv.context.getString(R.string.fragment_actors_chosen_text, actor.name),
                        Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    /*TODO 4: create implementation of click listener
                you can call function
                doOnClick(actor: Actor)
     */
//    private val clickListener

    companion object {
        fun newInstance() = WS03ActorsFragment()
    }
}