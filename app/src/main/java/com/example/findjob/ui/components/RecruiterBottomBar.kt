package com.example.findjob.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class RecruiterBottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : RecruiterBottomNavItem(
        route = "recruiterHome",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Jobs : RecruiterBottomNavItem(
        route = "jobLists",
        title = "Post Job",
        icon = Icons.AutoMirrored.Filled.List
    )
    object CreateJob : RecruiterBottomNavItem(
        route = "createJob",
        title = "Company",
        icon = Icons.Default.Create
    )
    object Profile : RecruiterBottomNavItem(
        route = "recruiterProfile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}

@Composable
fun RecruiterBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        RecruiterBottomNavItem.Home,
        RecruiterBottomNavItem.Jobs,
        RecruiterBottomNavItem.CreateJob,
        RecruiterBottomNavItem.Profile
    )

    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
} 