package com.example.presentation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.presentation.component.SignUpScreen

@Composable
fun LoginNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LoginRoute.WelcomeScreen.route
    ) {
        composable(LoginRoute.WelcomeScreen.route) {
            WelcomeScreen {
                navController.navigate(LoginRoute.LoginScreen.route)
            }
        }

        composable(LoginRoute.LoginScreen.route) {
            LoginScreen {
                navController.navigate(LoginRoute.SignUpScreen.route)
            }
        }
        composable(LoginRoute.SignUpScreen.route) {
            SignUpScreen {
                navController.navigate(
                    route = LoginRoute.LoginScreen.route,
                    navOptions = navOptions {
                        popUpTo(LoginRoute.WelcomeScreen.route)
                    })
            }
        }
    }
}