package com.example.fitlife.repository


import android.content.Context
import com.example.fitlife.data.dto.LoginRequest
import com.example.fitlife.data.dto.LoginResponse
import com.example.fitlife.data.dto.RegisterRequest
import com.example.fitlife.data.dto.User
import com.example.fitlife.data.local.TokenManager
import com.example.fitlife.domain.user.DefaultCredentials
import com.example.fitlife.domain.user.UserRole
import java.util.UUID


class AuthRepositoryImpl(private val context: Context) : AuthRepository {


    // The real network API is temporarily disabled for local testing.
    // private val baseUrl = "http://10.0.2.2:3000"
    // private val api: ApiService by lazy { RetrofitClient.getClient(baseUrl, context).create(ApiService::class.java) }


    override suspend fun login(request: LoginRequest): LoginResponse {
        // --- MOCK LOGIN IMPLEMENTATION ---
        val email = request.email
        val password = request.password


        // Find the role associated with the provided email
        val userEntry = DefaultCredentials.credentials.entries.find { it.value.first == email }


        if (userEntry != null && userEntry.value.second == password) {
            val role = userEntry.key
            val user = User(id = 1, nombre = role.name.lowercase().replaceFirstChar { it.uppercase() }, email = email, role = role)
            val fakeToken = "fake-token-${UUID.randomUUID()}"


            // Set the session using the mock data
            TokenManager.setSession(fakeToken, user.role, context)


            // Return a successful mock response
            return LoginResponse(token = fakeToken, user = user)
        } else {
            // If credentials don't match, simulate a login failure
            throw RuntimeException("Credenciales incorrectas. Inténtalo de nuevo.")
        }
    }


    override suspend fun register(request: RegisterRequest): LoginResponse {
        // --- MOCK REGISTRATION IMPLEMENTATION ---
        // In mock mode, we'll create a new user with the USER role and log them in.
        val newUser = User(
            id = (100..999).random(), // Random ID for mock user
            nombre = request.nombre,
            email = request.email,
            role = UserRole.USER // New registrations are always standard users
        )
        val fakeToken = "fake-token-${UUID.randomUUID()}"


        // Automatically log the new user in
        TokenManager.setSession(fakeToken, newUser.role, context)


        // Return a response as if the server did
        return LoginResponse(token = fakeToken, user = newUser)
    }


    override suspend fun logout() {
        // Logout works locally, so no changes are needed here.
        TokenManager.clearSession(context)
    }
}



