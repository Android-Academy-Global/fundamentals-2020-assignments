package com.android.fundamentals.task_three.done

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.fundamentals.R

class SomeFragment : Fragment() {

    private var someFragmentClickListener: SomeFragmentContract? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_some, container, false)

        val tvValue = view.findViewById<TextView>(R.id.tv_value)
            ?.apply {
                text = arguments?.getString("android")
            }

        view?.findViewById<Button>(R.id.btn_next)
            ?.setOnClickListener {
                someFragmentClickListener?.onNextClicked()
            }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SomeFragmentContract) {
            someFragmentClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        someFragmentClickListener = null
    }

    companion object {
        fun newInstance(academy: String): SomeFragment {
            val args = Bundle()
            args.putString("android", academy)
            val fragment = SomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

}

interface SomeFragmentContract {
    fun onNextClicked()
}