package com.android.fundamentals.workshop03.antibonus

import androidx.lifecycle.*
import com.android.fundamentals.domain.location.Location
import kotlinx.coroutines.launch

class Ws03BonusViewModel(
    private val repository: Ws03BonusLocationsRepository
) : ViewModel() {

    private val _mutableLocationsList = MutableLiveData<List<Location>>(emptyList())
    private val _mutableLoadingState = MutableLiveData<Boolean>(false)

    val locationsList: LiveData<List<Location>> get() = _mutableLocationsList
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState
    val locationsCount get() = repository.getLocationsCount()

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
                _mutableLoadingState.value = true
    
                _mutableLocationsList.value = block()

                _mutableLoadingState.value = false
            }
        }
    }
}