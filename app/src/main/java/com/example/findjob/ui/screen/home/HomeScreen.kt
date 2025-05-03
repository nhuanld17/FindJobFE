package com.example.findjob.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.findjob.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val accessToken = remember { viewModel.tokenManager.getAccessToken() } ?: "No Access Token"
    val refreshToken = remember { viewModel.tokenManager.getRefreshToken() } ?: "No Refresh Token"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome! 🎉", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Access Token:", style = MaterialTheme.typography.bodyMedium)
        Text(accessToken.take(60), style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Refresh Token:", style = MaterialTheme.typography.bodyMedium)
        Text(refreshToken.take(60), style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            viewModel.logout()
        }) {
            Text("Logout")
        }
    }
}
