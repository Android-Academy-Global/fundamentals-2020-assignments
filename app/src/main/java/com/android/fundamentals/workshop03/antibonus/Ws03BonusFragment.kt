package com.android.fundamentals.workshop03.antibonus

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.LocationsAdapter
import com.android.fundamentals.R
import com.android.fundamentals.domain.location.Location
import com.android.fundamentals.workshop03.Ws03ViewModelFactory

class Ws03BonusFragment : Fragment(R.layout.fragment_workshop_3) {
    
    private var titleView: TextView? = null
    
    private val viewModel: Ws03BonusViewModel by viewModels {
        Ws03ViewModelFactory(applicationContext = requireContext().applicationContext)
    }
    
    private val locationsAdapter by lazy { LocationsAdapter(viewModel::delete) }
    
    private var recycler: RecyclerView? = null
    private var addBtn: View? = null
    private var loader: View? = null
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setUpLocationsAdapter()
        setUpListeners()
        setupViewModel()
    }
    
    override fun onDestroyView() {
        clearViews()
        
        super.onDestroyView()
    }
    
    private fun initViews(view: View) {
        titleView = view.findViewById(R.id.fragment_workshop_3_title)
        titleView?.isVisible = false
        
        recycler = view.findViewById(R.id.fragment_workshop_3_recycler)
        addBtn = view.findViewById(R.id.fragment_workshop_3_add_new)
        loader = view.findViewById(R.id.fragment_workshop_3_loader)
    }
    
    private fun clearViews() {
        titleView = null
        recycler?.adapter = null
        recycler = null
        addBtn = null
        loader = null
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
    
    private fun setupViewModel() {
        viewModel.locationsCount.observe(this.viewLifecycleOwner, this::updateTitleCounter)
        viewModel.locationsList.observe(this.viewLifecycleOwner, this::updateAdapter)
        viewModel.loadingState.observe(this.viewLifecycleOwner, this::setLoading)
    }
    
    private fun updateTitleCounter(value: Int) {
        titleView?.text = getString(R.string.ws03_locations_counter_text, value)
        
        if (titleView?.isVisible == false) {
            titleView?.isVisible = true
        }
    }
    
    private fun updateAdapter(locations: List<Location>) {
        locationsAdapter.submitList(locations)
    }
    
    private fun setLoading(loading: Boolean) {
        addBtn?.isInvisible = loading
        loader?.isVisible = loading
    }

    companion object {
        fun newInstance(): Ws03BonusFragment = Ws03BonusFragment()
    }
}