package com.example.presentation.login

import android.util.Log

sealed class LoginRoute(val route:String) {
    object WelcomeScreen: LoginRoute("WelcomeScreen")
    object LoginScreen: LoginRoute("LoginScreen")
    object SignUpScreen: LoginRoute("SignUpScreen")
}