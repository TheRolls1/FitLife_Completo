package com.example.fitlife.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class ProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun profileScreen_HeaderIsDisplayed() {
        composeTestRule.setContent {
            ProfileScreen(navController = rememberNavController())
        }
        composeTestRule.onNodeWithText("Nombre de Usuario").assertIsDisplayed()
        composeTestRule.onNodeWithText("usuario@email.com").assertIsDisplayed()
    }

    @Test
    fun profileScreen_EditProfileButtonIsDisplayed() {
        composeTestRule.setContent {
            ProfileScreen(navController = rememberNavController())
        }
        composeTestRule.onNodeWithText("Editar Perfil").assertIsDisplayed()
    }

    @Test
    fun settingsItem_Progreso_IsDisplayed() {
        composeTestRule.setContent {
            ProfileScreen(navController = rememberNavController())
        }
        composeTestRule.onNodeWithText("Progreso").assertIsDisplayed()
    }

    @Test
    fun settingsItem_Entrenamiento_IsDisplayed() {
        composeTestRule.setContent {
            ProfileScreen(navController = rememberNavController())
        }
        composeTestRule.onNodeWithText("Entrenamiento").assertIsDisplayed()
    }

    @Test
    fun settingsItem_Privacidad_IsDisplayed() {
        composeTestRule.setContent {
            ProfileScreen(navController = rememberNavController())
        }
        composeTestRule.onNodeWithText("Privacidad").assertIsDisplayed()
    }

    @Test
    fun settingsItem_Ayuda_IsDisplayed() {
        composeTestRule.setContent {
            ProfileScreen(navController = rememberNavController())
        }
        composeTestRule.onNodeWithText("Ayuda").assertIsDisplayed()
    }

    @Test
    fun profileScreen_LogoutButtonIsDisplayed() {
        composeTestRule.setContent {
            ProfileScreen(navController = rememberNavController())
        }
        composeTestRule.onNodeWithText("Cerrar Sesión").assertIsDisplayed()
    }
}