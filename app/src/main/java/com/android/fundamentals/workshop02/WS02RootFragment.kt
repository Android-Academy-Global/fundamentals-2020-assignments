package com.android.fundamentals.workshop02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.android.fundamentals.R

class WS02RootFragment : Fragment() {

    private var btnAddNewRedFragment: Button? = null
    private var btnAddNewBlueFragment: Button? = null
    private var btnRemoveLastFragment: Button? = null
    private var btnReplaceFragment: Button? = null
    private var listener: TransactionsFragmentClicks? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_root_ws_02, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddNewRedFragment = view.findViewById<Button>(R.id.btn_add_red).apply {
            setOnClickListener { listener?.addRedFragment() }
        }
        btnAddNewBlueFragment = view.findViewById<Button>(R.id.btn_add_blue).apply {
            setOnClickListener { listener?.addBlueFragment() }
        }
        btnRemoveLastFragment = view.findViewById<Button>(R.id.btn_remove_last).apply {
            setOnClickListener { listener?.removeLast() }
        }
        btnReplaceFragment = view.findViewById<Button>(R.id.btn_replace_green).apply {
            setOnClickListener { listener?.replaceFragment() }
        }

    }

    fun setClickListener(l: TransactionsFragmentClicks?) {
        listener = l
    }


    interface TransactionsFragmentClicks {
        fun addRedFragment()
        fun addBlueFragment()
        fun removeLast()
        fun replaceFragment()
    }
}