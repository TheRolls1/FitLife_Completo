package com.example.fitlife.data.repository

import com.example.fitlife.data.model.RoutineDto
import com.example.fitlife.data.remote.RoutineApi

class RoutineRepository(
    private val api: RoutineApi
) {

    suspend fun getAll(): List<RoutineDto> =
        api.getRoutines()

    suspend fun create(routine: RoutineDto): RoutineDto =
        api.createRoutine(routine)

    suspend fun delete(id: Long) =
        api.deleteRoutine(id)
}
