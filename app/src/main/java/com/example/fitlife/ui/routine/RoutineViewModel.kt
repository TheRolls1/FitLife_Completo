package com.example.fitlife.ui.routine

import androidx.lifecycle.*
import com.example.fitlife.data.model.RoutineDto
import com.example.fitlife.data.repository.RoutineRepository
import kotlinx.coroutines.launch

class RoutineViewModel(
    private val repository: RoutineRepository
) : ViewModel() {

    private val _routines = MutableLiveData<List<RoutineDto>>()
    val routines: LiveData<List<RoutineDto>> = _routines

    fun loadRoutines() {
        viewModelScope.launch {
            _routines.value = repository.getAll()
        }
    }

    fun createRoutine(name: String, description: String, duration: Int) {
        viewModelScope.launch {
            repository.create(
                RoutineDto(
                    name = name,
                    description = description,
                    durationMinutes = duration
                )
            )
            loadRoutines()
        }
    }

    fun deleteRoutine(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            loadRoutines()
        }
    }
}

