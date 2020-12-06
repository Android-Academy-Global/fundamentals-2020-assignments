package com.android.fundamentals.workshop03.solution

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R

class Workshop3SolutionFragment : Fragment(R.layout.fragment_workshop_3) {

    private val viewModel: Workshop3SolutionViewModel by viewModels { Workshop3SolutionViewModelFactory() }

    private val locationsAdapter = LocationsAdapter()

    private var recycler: RecyclerView? = null
    private var addBtn: View? = null
    private var loader: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setUpLocationsAdapter()
        setUpListeners()

        viewModel.locationsList.observe(this.viewLifecycleOwner, locationsAdapter::submitList)
        viewModel.loadingState.observe(this.viewLifecycleOwner, this::setLoading)
    }

    override fun onDestroyView() {
        recycler?.adapter = null
        recycler = null
        addBtn = null
        loader = null

        super.onDestroyView()
    }

    private fun setLoading(loading: Boolean) {
        addBtn?.isInvisible = loading
        loader?.isVisible = loading
    }

    private fun initViews(view: View) {
        recycler = view.findViewById(R.id.fragment_workshop_3_recycler)
        addBtn = view.findViewById(R.id.fragment_workshop_3_add_new)
        loader = view.findViewById(R.id.fragment_workshop_3_loader)
    }

    private fun setUpLocationsAdapter() {
        recycler?.layoutManager = LinearLayoutManager(requireContext())
        recycler?.adapter = locationsAdapter
    }

    private fun setUpListeners() {
        addBtn?.setOnClickListener {
            viewModel.addNew()
        }
    }

    companion object {
        fun newInstance(): Workshop3SolutionFragment = Workshop3SolutionFragment()
    }
}