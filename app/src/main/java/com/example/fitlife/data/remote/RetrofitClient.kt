package com.example.fitlife.data.remote

import android.content.Context
import com.example.fitlife.data.local.TokenManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String, context: Context): Retrofit {
        if (retrofit == null) {
            TokenManager.init(context, com.example.fitlife.App.appScope)
            val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(AuthInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}
