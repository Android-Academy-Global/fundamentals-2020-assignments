package com.android.fundamentals.workshop03.solution

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundamentals.domain.location.Location
import com.android.fundamentals.domain.location.LocationGenerator
import kotlinx.coroutines.launch

class Workshop3SolutionViewModel(
    private val generator: LocationGenerator
) : ViewModel() {

    private val _mutableLocationsList = MutableLiveData<List<Location>>(emptyList())
    private val _mutableLoadingState = MutableLiveData<Boolean>(false)

    val locationsList: LiveData<List<Location>> get() = _mutableLocationsList
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState

    fun addNew() {
        viewModelScope.launch {
            _mutableLoadingState.setValue(true)

            val newLocation = generator.generateNewLocation()

            val updatedLocationsList = _mutableLocationsList.value?.plus(newLocation).orEmpty()
            _mutableLocationsList.setValue(updatedLocationsList)

            _mutableLoadingState.setValue(false)
        }
    }
}