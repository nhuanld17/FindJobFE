package com.example.findjob.ui.screen.employee

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.findjob.ui.components.EmployeeBottomBar

@Composable
fun EmployeeProfileScreen(navController: NavController) {
    // State lưu uri ảnh đại diện
    var avatarUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher để chọn ảnh từ gallery
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            avatarUri = uri
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background gradient top
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF4B3FAE), Color(0xFF6C63FF))
                    ),
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
        ) {}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, bottom = 130.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            // Top bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.clickable { navController.popBackStack() }
                )
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Avatar
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                if (avatarUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(avatarUri),
                        contentDescription = "Avatar",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                // Nếu chưa chọn ảnh thì để màu xám hoặc icon mặc định
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Orlando Diggs",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "California, USA",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Nút chọn ảnh
            TextButton(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.textButtonColors(containerColor = Color.White.copy(alpha = 0.2f))
            ) {
                Text("Change image", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Form
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                color = Color.White,
                shape = RoundedCornerShape(24.dp),
                shadowElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    OutlinedTextField(
                        value = "Brandone Louis",
                        onValueChange = {},
                        label = { Text("Fullname") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = "06 August 1992",
                        onValueChange = {},
                        label = { Text("Date of birth") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Lock"
                            )
                        },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Gender", fontWeight = FontWeight.Medium, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    var gender by remember { mutableStateOf("Male") }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (gender == "Male") Color(0xFFFFF6E5) else Color(0xFFF3F3F3))
                                .clickable { gender = "Male" }
                                .padding(vertical = 8.dp)
                        ) {
                            RadioButton(
                                selected = gender == "Male",
                                onClick = { gender = "Male" },
                                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFF9900))
                            )
                            Text("Male", color = Color(0xFF1A1446))
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (gender == "Female") Color(0xFFFFF6E5) else Color(0xFFF3F3F3))
                                .clickable { gender = "Female" }
                                .padding(vertical = 8.dp)
                        ) {
                            RadioButton(
                                selected = gender == "Female",
                                onClick = { gender = "Female" },
                                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFF9900))
                            )
                            Text("Female", color = Color(0xFF1A1446))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = "Brandonelouis@gmail.com",
                        onValueChange = {},
                        label = { Text("Email address") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = "619 3456 7890",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            label = { Text("Phone number") }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = "California, United states",
                        onValueChange = {},
                        label = { Text("Location") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = "Password",
                        onValueChange = {},
                        label = { Text("") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Password"
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Lock"
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1446))
                    ) {
                        Text("SAVE", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        }
        EmployeeBottomBar(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}