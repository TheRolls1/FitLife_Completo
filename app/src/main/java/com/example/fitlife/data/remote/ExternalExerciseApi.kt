package com.fitlife.app.data.remote

import com.fitlife.app.data.model.ExternalExerciseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ExternalExerciseApi {

    @GET("exercises")
    suspend fun getExercises(
        @Query("muscle") muscle: String
    ): List<ExternalExerciseDto>
}
