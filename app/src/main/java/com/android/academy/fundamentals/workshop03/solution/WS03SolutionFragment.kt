package com.android.academy.fundamentals.workshop03.solution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.R
import com.android.academy.fundamentals.workshop03.ColorsAdapter
import com.android.academy.fundamentals.workshop03.ColorsHelper


class WS03SolutionFragment : Fragment() {

    private lateinit var mList : RecyclerView
    private lateinit var mAdapter : ColorsAdapter
    private lateinit var mOperationsRadioGroup : RadioGroup
    private lateinit var mPredictiveAnimationsCB : CheckBox

    private val colorsHelper = ColorsHelper()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wso3_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mList = view.findViewById(R.id.recycler_view)
        mPredictiveAnimationsCB = view.findViewById(R.id.predictive_animations)
        mOperationsRadioGroup = view.findViewById(R.id.operations)
        mAdapter = ColorsAdapter(context!!, colorsHelper.generateColors(40))
        mAdapter.mItemAction = View.OnClickListener {
            when (mOperationsRadioGroup.checkedRadioButtonId) {
                R.id.add_color -> addItem(it)
                R.id.change_color -> changeItem(it)
                R.id.delete_color -> deleteItem(it)
            }
        }
        mList = view.findViewById(R.id.recycler_view)
        mList.adapter = mAdapter
        mList.layoutManager = object : LinearLayoutManager(context) {
            override fun supportsPredictiveItemAnimations(): Boolean {
                return mPredictiveAnimationsCB.isChecked
            }
        }
        view.findViewById<CheckBox>(R.id.custom_animator).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mList.itemAnimator = SolutionColorItemAnimator()
            } else mList.itemAnimator = DefaultItemAnimator()
        }
    }

    private fun addItem(view: View) {
        val pos = mList.getChildAdapterPosition(view)
        if (pos != RecyclerView.NO_POSITION) {
            val color: Int = colorsHelper.generateColor()
            mAdapter.addItem(pos, color)
            if (pos == 0) {
                mList.scrollToPosition(0)
            }
        }
    }

    private fun changeItem(view: View) {
        val pos = mList.getChildAdapterPosition(view)
        if (pos != RecyclerView.NO_POSITION) {
            val color: Int = colorsHelper.generateColor()
            mAdapter.changeItem(pos, color)
        }
    }

    private fun deleteItem(view: View) {
        val pos = mList.getChildAdapterPosition(view)
        if (pos != RecyclerView.NO_POSITION) {
            mAdapter.removeItem(pos)
        }
    }
}