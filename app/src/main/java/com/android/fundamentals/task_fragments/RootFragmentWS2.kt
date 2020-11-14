package com.android.fundamentals.task_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.android.fundamentals.R


//TODO(W2:1) Create fragment class, extend Fragment
class RootFragmentWS2() : Fragment() {


    private var btnIncrement: Button? = null
    private var btnChangeBackground: Button? = null
    //TODO(W2:5) Create a variable ClickListener

    //TODO(W2:2) Override onCreateView() method
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_root_ws_2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnIncrement = view.findViewById<Button>(R.id.btn_increment).apply {
            //TODO(W2:7) Set button OnClickListener
        }
        btnChangeBackground = view.findViewById<Button>(R.id.btn_change_background).apply {
            //TODO(W2:8) Set button OnClickListener
        }

    }

    //TODO(W2:6) Create fun setListener() initialize clickListener in Fragment

    //TODO(W2:4) Create interface ClickListener
}