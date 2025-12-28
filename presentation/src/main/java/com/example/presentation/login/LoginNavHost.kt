package com.example.presentation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            LoginScreen()
        }
        composable(LoginRoute.SignUpScreen.route) {
            SignUpScreen(
                id = TODO(),
                name = TODO(),
                pwd1 = TODO(),
                pwd2 = TODO(),
                onIdChange = TODO(),
                onNameChange = TODO(),
                onPwd1Change = TODO(),
                onPwd2Change = TODO(),
                onSignUpClick = TODO()
            )
        }
    }
}