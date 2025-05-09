package com.example.findjob.ui.screen.recruiter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.findjob.ui.components.Preview.Preview
import com.example.findjob.viewmodel.HomeViewModel
import com.example.findjob.ui.components.RecruiterBottomBar
import com.example.findjob.ui.components.card.RecruiterCard
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.findjob.ui.components.avatar.Avatar

@Composable
fun RecruiterHomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize().statusBarsPadding().background(Color(0xFFF9F9F9))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Avatar(imageUrl = null, navController)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Create Your Job",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                color = Color(0xFF23235B)
            )
            Preview()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Recent Job Post",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                color = Color(0xFF23235B)
            )
            RecruiterCard(navController)
            RecruiterCard(navController)
            RecruiterCard(navController)
            RecruiterCard(navController)
            RecruiterCard(navController)
            Spacer(modifier = Modifier.height(130.dp))
        }
        RecruiterBottomBar(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}