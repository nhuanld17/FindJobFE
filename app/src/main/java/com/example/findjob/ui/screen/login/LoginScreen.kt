package com.example.findjob.ui.screen.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.findjob.navigation.Screen
import com.example.findjob.utils.InfoManager
import com.example.findjob.viewmodel.LoginState
import com.example.findjob.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by viewModel.loginState.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(loginState) {
        when (loginState) {
            // Nếu đăng nhập thành công thì kiểm tra role để điều hướng tới
            // màn hình tương ứng
            is LoginState.Success -> {
                val role = viewModel.infoManager.getRole();
                if (role == "ROLE_EMPLOYEE") {
                    navController.navigate(Screen.EmployeeHome.route)
                }

                if (role == "ROLE_RECRUITER") {
                    navController.navigate(Screen.RecruiterHome.route)
                }
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
                .padding(horizontal = 29.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "Welcome Back",
                color = Color(0xFF0D0140),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp, bottom = 100.dp),
                textAlign = TextAlign.Center
            )

            // EMAIL
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // PASSWORD
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(50.dp))

            // LOGIN BUTTON
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    onClick = {
                        viewModel.login(email, password)
                        keyboardController?.hide()
                    },
                    enabled = loginState !is LoginState.Loading,
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF130160))
                ) {
                    Text(
                        "Login",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 18.dp, horizontal = 100.dp)
                    )
                }
            }

            TextButton(
                onClick = { navController.navigate(Screen.Register.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp) // Đặt padding ở đây, không đặt trong Text
            ) {
                Text(
                    text = "Don't have an account? Register",
                    color = Color(0xFF514A6B),
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center // Cho đẹp, canh giữa
                )
            }

        }
    }
}


