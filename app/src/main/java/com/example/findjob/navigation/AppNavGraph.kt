package com.example.findjob.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.findjob.ui.screen.employee.AIScreen
import com.example.findjob.ui.screen.employee.CompanyInfoScreen
import com.example.findjob.ui.screen.employee.EmployeeHomeScreen
import com.example.findjob.ui.screen.employee.EmployeeProfileScreen
import com.example.findjob.ui.screen.employee.FilterScreen
import com.example.findjob.ui.screen.employee.JobDescriptionScreen
import com.example.findjob.ui.screen.employee.NotificationScreen
import com.example.findjob.ui.screen.employee.SavedJobScreen
import com.example.findjob.ui.screen.employee.SearchJobScreen
import com.example.findjob.ui.screen.employee.UploadCVScreen
import com.example.findjob.ui.screen.login.LoginScreen
import com.example.findjob.ui.screen.recruiter.CreateJobScreen
import com.example.findjob.ui.screen.recruiter.RecruiterHomeScreen
import com.example.findjob.ui.screen.recruiter.ListCurriculumSceen
import com.example.findjob.ui.screen.recruiter.RecruiterJobListScreen
import com.example.findjob.ui.screen.recruiter.RecruiterProfileScreen
import com.example.findjob.ui.screen.register.RegisterScreen
import com.example.findjob.ui.screen.splash.SplashScreen
import com.example.findjob.utils.InfoManager

@Composable
fun AppNavGraph(
    navController: NavHostController,
    infoManager: InfoManager
) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") { 
            SplashScreen(navController, infoManager)
        }
        
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }

        // Employee Screen
        composable("employeeHome") { EmployeeHomeScreen(navController) }
        composable("companyInfo") { CompanyInfoScreen(navController) }
        composable("employeeProfile") { EmployeeProfileScreen(navController, infoManager = infoManager) }
        composable("filter") { FilterScreen(navController) }
        composable("jobDescription") { JobDescriptionScreen(navController) }
        composable("notification") { NotificationScreen(navController) }
        composable("savedJobs") { SavedJobScreen(navController) }
        composable("searchJob") { SearchJobScreen(navController) }
        composable("uploadCV") { UploadCVScreen() }
        composable("chatAI") { AIScreen(navController) }

        // Recruiter Screen
        composable("recruiterHome") { RecruiterHomeScreen(navController) }
        composable("createJob") { CreateJobScreen(navController) }
        composable("jobLists") { RecruiterJobListScreen(navController) }
        composable("recruiterProfile") { RecruiterProfileScreen(navController) }
        composable("listCurriculum/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            ListCurriculumSceen(navController, id)
        }
    }
}