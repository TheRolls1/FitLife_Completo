package com.example.fitlife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.fitlife.ui.navigation.NavGraph
import com.example.fitlife.ui.components.DrawerContent
import com.example.fitlife.ui.theme.FitLifeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitLifeTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            title = { Text("FitLife SPA") },
                            elevation = 6.dp,
                            navigationIcon = {
                                IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                                }
                            }
                        )
                    },
                    drawerContent = {
                        DrawerContent(onNavigate = { route ->
                            scope.launch { scaffoldState.drawerState.close() }
                            navController.navigate(route)
                        })
                    }
                ) { innerPadding ->
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
