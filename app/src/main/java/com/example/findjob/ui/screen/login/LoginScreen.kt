package com.example.findjob.ui.screen.login

//@Composable
//fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    val loginState by viewModel.loginState.collectAsState()
//
//    LaunchedEffect(loginState) {
//        when (loginState) {
//            is LoginState.Success -> {
//                navController.navigate(Screen.Home.route) {
//                    popUpTo(Screen.Login.route) { inclusive = true }
//                }
//            }
//            else -> {}
//        }
//    }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
//        Spacer(modifier = Modifier.height(8.dp))
//        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick = { viewModel.login(email, password) },
//            enabled = loginState !is LoginState.Loading
//        ) {
//            Text(if (loginState is LoginState.Loading) "Logging in..." else "Login")
//        }
//        if (loginState is LoginState.Error) {
//            Text(
//                text = (loginState as LoginState.Error).message,
//                color = MaterialTheme.colorScheme.error
//            )
//        }
//        TextButton(onClick = { navController.navigate(Screen.Register.route) }) {
//            Text("Don't have an account? Register")
//        }
//    }
//}

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
            is LoginState.Success -> {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
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
                fontSize = 40.sp, // Tăng tiêu đề
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 102.dp, bottom = 30.dp)
            )

            // EMAIL
            Column(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Email",
                    color = Color(0xFF0D0140),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 12.dp, start = 3.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text("Enter your email", fontSize = 20.sp) // Placeholder to
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(fontSize = 22.sp), // Font người dùng nhập
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp) // Tăng chiều cao tương ứng
                        .border(
                            width = 2.dp,
                            color = if (email.isNotEmpty()) Color(0xFF130160) else Color(0xFFCCCCCC),
                            shape = RoundedCornerShape(12.dp)
                        )
                )
            }

            // PASSWORD
            Column(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Password",
                    color = Color(0xFF0D0140),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 12.dp, start = 3.dp)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text("Enter your password", fontSize = 20.sp)
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(fontSize = 22.sp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp)
                        .border(
                            width = 2.dp,
                            color = if (password.isNotEmpty()) Color(0xFF130160) else Color(0xFFCCCCCC),
                            shape = RoundedCornerShape(12.dp)
                        )
                )

            }

            // LOGIN BUTTON
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


