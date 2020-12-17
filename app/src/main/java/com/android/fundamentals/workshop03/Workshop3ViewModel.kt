package com.android.fundamentals.workshop03

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundamentals.domain.location.LocationGenerator
import kotlinx.coroutines.launch

class Workshop3ViewModel(
    private val generator: LocationGenerator
) : ViewModel() {
    
    //TODO 03: Create private property MutableLiveData<boolean>
    // and public getter LiveData<boolean> for loading state (false).

    //TODO 04: Create private property MutableLiveData<List<Location>>
    // and public getter LiveData for locations (emptyList()).

    fun addNew() {
        viewModelScope.launch {
            //TODO 05: Copy logic for "addNew()" from fragment.
            // Update value of loading state's private livedata in the beginning and at the end of the coroutine.
            // Update value of locations's private livedata inside:
            // - get the previous value from mutable live data;
            // - add (.plus()) a new location to the collection;
            // - set updated collection back to the private livedata's value.
        }
    }
}