package com.example.findjob.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
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

sealed class EmployeeBottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : EmployeeBottomNavItem(
        route = "employeeHome",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Search : EmployeeBottomNavItem(
        route = "searchJob",
        title = "Search",
        icon = Icons.Default.Search
    )
    object Profile : EmployeeBottomNavItem(
        route = "employeeProfile",
        title = "Profile",
        icon = Icons.Default.AccountCircle
    )
    object Notification : EmployeeBottomNavItem(
        route = "notification",
        title = "Notification",
        icon = Icons.Default.Notifications
    )
    object Favourite : EmployeeBottomNavItem(
        route = "savedJobs",
        title = "Favourite",
        icon = Icons.Default.FavoriteBorder
    )
}

@Composable
fun EmployeeBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        EmployeeBottomNavItem.Home,
        EmployeeBottomNavItem.Search,
        EmployeeBottomNavItem.Profile,
        EmployeeBottomNavItem.Notification,
        EmployeeBottomNavItem.Favourite
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