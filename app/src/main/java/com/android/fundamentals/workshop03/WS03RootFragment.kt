package com.android.fundamentals.workshop03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.android.fundamentals.R

class WS03RootFragment : Fragment() {

    private var btnAddNewRedFragment: Button? = null
    private var btnAddNewBlueFragment: Button? = null
    private var btnRemoveLastFragment: Button? = null
    private var btnReplaceFragment: Button? = null
    private var checkBox: CheckBox? = null
    private var listener: TransactionsFragmentClicks? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_root_ws_03, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkBox = view.findViewById<CheckBox>(R.id.cb_add_to_back_stack).apply {
            listener?.addToBackStack(isChecked)
            setOnCheckedChangeListener { _, isChecked -> listener?.addToBackStack(isChecked) }
        }

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
        fun addToBackStack(value: Boolean)
        fun addRedFragment()
        fun addBlueFragment()
        fun removeLast()
        fun replaceFragment()
    }
}