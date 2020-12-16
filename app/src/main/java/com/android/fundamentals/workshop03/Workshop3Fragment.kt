package com.android.fundamentals.workshop03

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.domain.location.Location
import com.android.fundamentals.domain.location.LocationGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class Workshop3Fragment : Fragment(R.layout.fragment_workshop_3) {

    // TODO 07: Remove generator, locations and mainScope.
    private val generator = LocationGenerator(Dispatchers.Default)
    private val locations = mutableListOf<Location>()
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val viewModel: Workshop3ViewModel by viewModels { Workshop3ViewModelFactory() }

    private val locationsAdapter = LocationsAdapter()

    private var recycler: RecyclerView? = null
    private var addBtn: View? = null
    private var loader: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setUpLocationsAdapter()
        setUpListeners()
    
        // TODO 06: Subscribe on public LiveDatas from viewModel:
        //  first with "List<Location>", second with "Boolean" loading state.
        //  Use observe() method of LiveData.
        //  Pass "this.viewLifecycleOwner" as LifecycleOwner
        //  and { ... } lambda as lifecycle.Observer in parameters.
        //  Update "setLoading(...)" and "updateAdapter(...)" methods from lambdas.
    }

    override fun onDestroyView() {
        recycler?.adapter = null
        recycler = null
        addBtn = null
        loader = null
        mainScope.cancel()
        super.onDestroyView()
    }

    private fun setLoading(loading: Boolean) {
        //TODO 01: Make "loader" visible/gone = loading
        // and opposite "addBtn" visible/gone = !loading.
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
            // TODO 02: Change this "addNew()" with "viewModel addNew()" method.
            addNew()
        }
    }

    // TODO 08: Remove this method.
    private fun addNew() {
        mainScope.launch {
            setLoading(loading = true)

            val newLocation = generator.generateNewLocation()
            locations.add(newLocation)
            updateAdapter(locations)

            setLoading(loading = false)
        }
    }
    
    private fun updateAdapter(locations: List<Location>) {
        locationsAdapter.submitList(locations)
    }

    companion object {
        fun newInstance(): Workshop3Fragment = Workshop3Fragment()
    }
}