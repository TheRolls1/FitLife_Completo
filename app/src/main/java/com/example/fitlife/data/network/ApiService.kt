package com.example.fitlife.data.network

import com.example.fitlife.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("usuario/me")
    suspend fun getProfile(): Response<User>

    @Multipart
    @POST("upload")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("userId") userId: RequestBody
    ): Response<Any>
}
