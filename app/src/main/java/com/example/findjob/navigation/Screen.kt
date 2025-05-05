package com.example.findjob.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object EmployeeHome : Screen("employeeHome")
    object RecruiterHome : Screen("recruiterHome")
}