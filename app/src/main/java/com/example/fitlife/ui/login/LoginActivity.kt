package com.example.fitlife.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.fitlife.MainActivity
import com.example.fitlife.data.network.RetrofitClient
import com.example.fitlife.data.repository.AuthRepository
import com.example.fitlife.utils.SessionManager

class LoginActivity : ComponentActivity() {

    private lateinit var sessionManager: SessionManager
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            AuthRepository(
                RetrofitClient.create { sessionManager.getToken() }
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)

        setContent {
            LoginScreen(
                onLoginClick = { email, password ->
                    viewModel.login(email, password) { success ->
                        if (success) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }
                }
            )
        }
    }
}
