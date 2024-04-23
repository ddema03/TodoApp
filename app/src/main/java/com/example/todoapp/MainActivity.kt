package com.example.todoapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoAppTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(navController = navController)
                        }
                        composable("register") {
                            RegisterScreen(navController = navController)
                        }

                        composable("todo") { // New navigation route
                            TodoListPage(navController)

                            // Uncomment or add more composables for additional pages
                            // composable("todo") {
                            //     TodoListPage(navController = navController)
                            // }
                            // composable("profile") {
                            //     ProfileScreen(navController = navController)
                            // }
                        }
                    }
                }
            }
        }
    }
}

