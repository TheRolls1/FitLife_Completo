package com.example.fitlife.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fitlife.data.dto.* // Importa todos los DTOs, incluyendo User
import com.example.fitlife.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class AuthViewModelTest {


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var mockAuthRepository: AuthRepository


    private lateinit var viewModel: AuthViewModel


    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = AuthViewModel(mockAuthRepository)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    // --- PRUEBAS ---


    @Test fun `estado inicial del viewModel es Idle`() {
        assertTrue(viewModel.uiState.value is AuthUiState.Idle)
    }


    @Test
    fun `login con email vacío devuelve estado de Error`() {
        viewModel.login("", "password123")
        val currentState = viewModel.uiState.value
        assertTrue(currentState is AuthUiState.Error)
        assertEquals("Completa email y contraseña", (currentState as AuthUiState.Error).message)
    }


    @Test
    fun `login con contraseña vacía devuelve estado de Error`() {
        viewModel.login("test@example.com", "")
        val currentState = viewModel.uiState.value
        assertTrue(currentState is AuthUiState.Error)
        assertEquals("Completa email y contraseña", (currentState as AuthUiState.Error).message)
    }


    @Test
    fun `login exitoso cambia el estado a Loading y luego a Success`() = runTest {


        val mockUser = User(id = "1", nombre = "Matias", email = "test@example.com")
        val mockResponse = LoginResponse("token_exitoso", mockUser)
        whenever(mockAuthRepository.login(any<LoginRequest>())).thenReturn(mockResponse)


        viewModel.login("test@example.com", "password123")
        assertTrue(viewModel.uiState.value is AuthUiState.Loading)
        testDispatcher.scheduler.advanceUntilIdle()
        val finalState = viewModel.uiState.value
        assertTrue(finalState is AuthUiState.Success)
        assertEquals(mockUser, (finalState as AuthUiState.Success).user)
    }


    @Test
    fun `login con fallo de red cambia el estado a Loading y luego a Error`() = runTest {
        val errorMessage = "Error de conexión"
        whenever(mockAuthRepository.login(any<LoginRequest>())).thenThrow(RuntimeException(errorMessage))
        viewModel.login("test@example.com", "password123")
        assertTrue(viewModel.uiState.value is AuthUiState.Loading)
        testDispatcher.scheduler.advanceUntilIdle()
        val finalState = viewModel.uiState.value
        assertTrue(finalState is AuthUiState.Error)
        assertEquals(errorMessage, (finalState as AuthUiState.Error).message)
    }


    @Test
    fun `register con nombre vacío devuelve estado de Error`() {
        viewModel.register("", "test@example.com", "password123")
        val currentState = viewModel.uiState.value
        assertTrue(currentState is AuthUiState.Error)
        assertEquals("Completa todos los campos", (currentState as AuthUiState.Error).message)
    }


    @Test
    fun `register exitoso cambia el estado a Loading y luego a Success`() = runTest {




        val mockUser = User(id = "1", nombre = "Matias", email = "test@example.com") // Sin comillas
        val mockResponse = RegisterResponse("token_registro", mockUser)






        viewModel.register("Matias", "matias@example.com", "password123")
        assertTrue(viewModel.uiState.value is AuthUiState.Loading)
        testDispatcher.scheduler.advanceUntilIdle()
        val finalState = viewModel.uiState.value
        assertTrue(finalState is AuthUiState.Success)
    }
}
