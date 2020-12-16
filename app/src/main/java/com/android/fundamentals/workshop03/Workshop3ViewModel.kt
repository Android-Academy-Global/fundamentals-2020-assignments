package com.android.fundamentals.workshop03

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundamentals.domain.location.LocationGenerator
import kotlinx.coroutines.launch

class Workshop3ViewModel(
    private val generator: LocationGenerator
) : ViewModel() {

    //TODO 03: Create private MutableLiveData and public LiveData for loading state (boolean).

    //TODO 04: Create private MutableLiveData and public LiveData for locations (List<Location>).

    fun addNew() {
        viewModelScope.launch {
            //TODO 05: Implement logic for addNew from fragment with update state through liveData.
        }
    }
}