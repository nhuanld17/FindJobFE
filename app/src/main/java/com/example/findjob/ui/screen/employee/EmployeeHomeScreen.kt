package com.example.findjob.ui.screen.employee

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.findjob.ui.components.EmployeeBottomBar
import com.example.findjob.viewmodel.HomeViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.clickable

@Composable
fun EmployeeHomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize().statusBarsPadding().background(Color(0xFFF9F9F9))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

                .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 130.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Hello",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1446)
                    )
                    Text(
                        text = "Orlando Diggs.",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1446)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Find Your Job",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF1A1446)
                    )
                }
                // Avatar
                Image(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate("employeeProfile")
                        }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFB8F1FF), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    // Icon thay thế bằng icon mặc định
                    Image(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Remote Job",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "44.5k",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF1A1446)
                    )
                    Text(
                        text = "Remote Job",
                        fontSize = 14.sp,
                        color = Color(0xFF1A1446)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFD6C7FF), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "66.8k",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF1A1446)
                    )
                    Text(
                        text = "Full Time",
                        fontSize = 14.sp,
                        color = Color(0xFF1A1446)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFFFE1B8), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "38.9k",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF1A1446)
                    )
                    Text(
                        text = "Part Time",
                        fontSize = 14.sp,
                        color = Color(0xFF1A1446)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Most apply jobs
            Text(
                text = "Most apply jobs",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1446)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Job Card (fix cứng 2 job)
            repeat(2) {
                JobCard()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }


        // Bottom bar
        EmployeeBottomBar(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}

@Composable
fun JobCard() {
    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Icon Apple
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFFE5E1FF), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        // Thay thế bằng icon mặc định
                        Image(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Company Logo",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Product Designer",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1446)
                        )
                        Text(
                            text = "Google inc · California, USA",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
                // Bookmark icon
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Bookmark"
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "$15K/Mo",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1446)
            )
            Spacer(modifier = Modifier.height(12.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Senior designer",
                    modifier = Modifier
                        .background(Color(0xFFF3F3F3), RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    fontSize = 12.sp,
                    color = Color(0xFF1A1446)
                )
                Text(
                    text = "Full time",
                    modifier = Modifier
                        .background(Color(0xFFF3F3F3), RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    fontSize = 12.sp,
                    color = Color(0xFF1A1446)
                )
                Text(
                    text = "Apply",
                    modifier = Modifier
                        .background(Color(0xFFFFE1B8), RoundedCornerShape(12.dp))
                        .padding(horizontal = 20.dp, vertical = 6.dp),
                    fontSize = 12.sp,
                    color = Color(0xFF1A1446),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
