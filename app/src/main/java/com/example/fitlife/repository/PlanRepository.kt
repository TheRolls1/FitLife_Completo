package com.example.fitlife.repository

import com.example.fitlife.data.dto.PlanDto

interface PlanRepository {
    suspend fun getPlans(type: String? = null, level: String? = null): List<PlanDto>
}
