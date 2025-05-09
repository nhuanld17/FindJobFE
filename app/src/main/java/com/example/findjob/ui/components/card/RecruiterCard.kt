package com.example.findjob.ui.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RecruiterCard(
    navController: NavController
//    data: Any,
//    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
//            .clickable { onClick() }
            .padding(12.dp)
            .background(Color.White, shape = RoundedCornerShape(30.dp))
            .border(width = 1.dp, color = Color(0xFFE5E6EB), shape = RoundedCornerShape(30.dp)),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Avatar placeholder
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color(0xFFD6D6FF), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("", fontSize = 16.sp) // Placeholder for logo
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Product Designer",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 13.sp,
                        color = Color(0xFF1A1D1F),
                    )
                    Text(
                        text = "Google inc · California, USA",
                        fontSize = 10.sp,
                        color = Color(0xFF7C8493),
                        modifier = Modifier.padding(top = 1.dp),
                        lineHeight = 1.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "$15K",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF1A1D1F)
                )
                Text(
                    text = "/Mo",
                    fontSize = 10.sp,
                    color = Color(0xFF7C8493),
                    modifier = Modifier.padding(start = 2.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Tag(text = "Senior designer", fontSize = 9)
                Spacer(modifier = Modifier.width(6.dp))
                Tag(text = "Full time", fontSize = 9)
                Spacer(modifier = Modifier.width(6.dp))
                CurriculumButton(navController)
            }
        }
    }
}

@Composable
private fun Tag(text: String, bgColor: Color = Color(0xFFF3F4F6), textColor: Color = Color(0xFF7C8493), fontSize: Int = 14) {
    Box(
        modifier = Modifier
            .background(bgColor, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = text, color = textColor, fontSize = fontSize.sp)
    }
}

@Composable
private fun CurriculumButton(navController: NavController) {
    Box(
        modifier = Modifier
            .background(Color(0xFFFFE3DD), shape = RoundedCornerShape(8.dp))
            .clickable { navController.navigate("listCurriculum/1") }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Curriculum",
            color = Color(0xFFED7457),
            fontSize = 9.sp
        )
    }
}