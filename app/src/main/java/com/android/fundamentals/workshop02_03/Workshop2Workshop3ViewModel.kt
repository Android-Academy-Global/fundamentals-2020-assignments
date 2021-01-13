package com.android.fundamentals.workshop02_03

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundamentals.domain.location.Location
import com.android.fundamentals.domain.location.LocationRepository
import kotlinx.coroutines.launch

class Workshop2Workshop3ViewModel(
    private val repository: LocationRepository
) : ViewModel() {

    private val _mutableLocationsList = MutableLiveData<List<Location>>(emptyList())
    private val _mutableLoadingState = MutableLiveData<Boolean>(false)

    val locationsList: LiveData<List<Location>> get() = _mutableLocationsList
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState

    init {
        startLoadingAndUpdateLocations(repository::getAllLocations)
    }

    fun addNew() = startLoadingAndUpdateLocations(repository::addNewAndGetUpdated)

    fun delete(location: Location) =
        startLoadingAndUpdateLocations { repository.deleteByIdAndGetUpdated(id = location.id) }

    private fun startLoadingAndUpdateLocations(block: suspend () -> List<Location>) {
        val isLoadingNow = (loadingState.value ?: false)
        if (!isLoadingNow) {
            viewModelScope.launch {
                _mutableLoadingState.setValue(true)

                val updatedLocations = block()

                _mutableLocationsList.setValue(updatedLocations)

                _mutableLoadingState.setValue(false)
            }
        }
    }
}