package com.example.fitlife.data.remote

import com.example.fitlife.data.dto.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("/api/auth/register")
    suspend fun register(@Body request: RegisterRequest): LoginResponse

    @GET("/api/plans")
    suspend fun getPlans(
        @Query("type") type: String? = null,
        @Query("level") level: String? = null,
        @Query("nutritionLevel") nutritionLevel: String? = null
    ): List<PlanDto>

    @Multipart
    @POST("/api/upload")
    suspend fun uploadImage(@Part file: MultipartBody.Part): UploadResponse
}
