package com.android.fundamentals.workshop02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.android.fundamentals.R

class WS02SecondFragment : Fragment() {

    private var tvValue: TextView? = null
    private var fragmentId: Int = 0
    private var colorId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fragmentId = it.getInt(PARAM_ID)
            colorId = it.getInt(PARAM_COLOR)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_second_ws_02, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        tvValue = view.findViewById<TextView>(R.id.tv_value)
            .apply {
                text = "$fragmentId"
                background = ContextCompat.getDrawable(context, colorId)
            }

    }

    companion object {
        private const val PARAM_ID = "fragment_id"
        private const val PARAM_COLOR = "fragment_color"

        fun newInstance(
            id: Int,
            color: Int
        ): WS02SecondFragment {
            val fragment = WS02SecondFragment()
            val args = Bundle()
            args.putInt(PARAM_ID, id)
            args.putInt(PARAM_COLOR, color)
            fragment.arguments = args
            return fragment
        }
    }


}