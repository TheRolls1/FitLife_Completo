package com.example.fitlife.repository

import android.content.Context
import com.example.fitlife.data.dto.PlanDto
import com.example.fitlife.data.remote.RetrofitClient

class PlanRepositoryImpl(private val context: Context) : PlanRepository {
    private val api by lazy { RetrofitClient.getClient("http://10.0.2.2:3000", context).create(com.example.fitlife.data.remote.ApiService::class.java) }
    override suspend fun getPlans(type: String?, level: String?): List<PlanDto> {
        return api.getPlans(type, level)
    }
}
