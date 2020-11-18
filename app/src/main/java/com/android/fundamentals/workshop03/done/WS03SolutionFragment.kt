package com.android.fundamentals.workshop03.done

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.android.fundamentals.R

class WS03SolutionFragment : Fragment() {

    private var btnIncrement: Button? = null
    private var btnChangeBackground: Button? = null
    private var listener: ClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_root_ws_03, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnIncrement = view.findViewById<Button>(R.id.btn_increment).apply {
            setOnClickListener { listener?.increaseValue() }
        }
        btnChangeBackground = view.findViewById<Button>(R.id.btn_change_background).apply {
            setOnClickListener { listener?.changeBackground() }
        }

    }

    fun setListener(l: ClickListener) {
        listener = l
    }

    interface ClickListener {
        fun increaseValue()
        fun changeBackground()
    }
}