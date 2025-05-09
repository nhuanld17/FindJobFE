package com.example.findjob.ui.screen.recruiter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.findjob.ui.components.RecruiterBottomBar
import com.example.findjob.ui.components.card.RecruiterCard

@Composable
fun RecruiterJobListScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }
    
    Box(modifier = Modifier.fillMaxSize().statusBarsPadding().background(Color(0xFFF9F9F9))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "My Job Post",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                color = Color(0xFF23235B)
            )
            
            // Tab selection buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { selectedTab = 0 },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == 0) Color(0xFF23235B) else Color.White
                    )
                ) {
                    Text(
                        text = "Active Jobs",
                        color = if (selectedTab == 0) Color.White else Color(0xFF23235B)
                    )
                }
                
                Button(
                    onClick = { selectedTab = 1 },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == 1) Color(0xFF23235B) else Color.White
                    )
                ) {
                    Text(
                        text = "Expired Jobs",
                        color = if (selectedTab == 1) Color.White else Color(0xFF23235B)
                    )
                }
            }

            // Show different job lists based on selected tab
            if (selectedTab == 0) {
                // Active jobs
                RecruiterCard(navController)
                RecruiterCard(navController)
                RecruiterCard(navController)
                RecruiterCard(navController)
                RecruiterCard(navController)
                RecruiterCard(navController)
                RecruiterCard(navController)
            } else {
                // Expired jobs
                RecruiterCard(navController)
                RecruiterCard(navController)
            }
            
            Spacer(modifier = Modifier.height(130.dp))
        }
        RecruiterBottomBar(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}