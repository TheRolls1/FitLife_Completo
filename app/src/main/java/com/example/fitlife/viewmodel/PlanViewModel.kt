package com.example.fitlife.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlife.data.dto.PlanDto
import com.example.fitlife.repository.PlanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlanViewModel(private val repo: PlanRepository) : ViewModel() {
    private val _plans = MutableStateFlow<List<PlanDto>>(emptyList())
    val plans: StateFlow<List<PlanDto>> = _plans

    fun loadPlans(type: String? = null, level: String? = null) {
        viewModelScope.launch {
            try {
                val list = repo.getPlans(type, level)
                _plans.value = list
            } catch (_: Exception) {
                _plans.value = emptyList()
            }
        }
    }
}
