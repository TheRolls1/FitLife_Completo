package com.example.fitlife.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import com.example.fitlife.data.local.TokenManager

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val token = TokenManager.getToken()
        return if (!token.isNullOrEmpty()) {
            val request = original.newBuilder()
                .header("Authorization", "Bearer $token")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        } else {
            chain.proceed(original)
        }
    }
}
