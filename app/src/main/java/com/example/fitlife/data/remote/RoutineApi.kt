package com.example.fitlife.data.remote

import com.example.fitlife.data.model.RoutineDto
import retrofit2.http.*

interface RoutineApi {

    @GET("routines")
    suspend fun getRoutines(): List<RoutineDto>

    @POST("routines")
    suspend fun createRoutine(
        @Body routine: RoutineDto
    ): RoutineDto

    @PUT("routines/{id}")
    suspend fun updateRoutine(
        @Path("id") id: Long,
        @Body routine: RoutineDto
    ): RoutineDto

    @DELETE("routines/{id}")
    suspend fun deleteRoutine(
        @Path("id") id: Long
    )
}

