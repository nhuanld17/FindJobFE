package com.example.findjob.ui.components.form


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.ui.res.painterResource


@Composable
fun ProfileForm(onSave: (about: String, website: String) -> Unit = { _, _ -> }) {
    var about by remember { mutableStateOf("") }
    var website by remember { mutableStateOf("") }
    var industry by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, start = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // About us card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9FB)),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFE5E5EF), RoundedCornerShape(20.dp))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = com.example.findjob.R.drawable.ic_education),
                        contentDescription = "Back",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "About us",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = Color(0xFF1A1444)
                    )
                }
                Divider(
                    color = Color(0xFFE5E5EF),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
                OutlinedTextField(
                    value = about,
                    onValueChange = { about = it },
                    placeholder = { Text("Enter about us...") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFE5E5EF),
                        unfocusedBorderColor = Color(0xFFE5E5EF),
                        cursorColor = Color(0xFF1A1444)
                    ),
                    maxLines = 4
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        // Website card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9FB)),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFE5E5EF), RoundedCornerShape(20.dp))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = com.example.findjob.R.drawable.ic_about_me),
                        contentDescription = "Back",
                        tint = Color(0xFF1A1444),
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Website",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = Color(0xFF1A1444)
                    )
                }
                Divider(
                    color = Color(0xFFE5E5EF),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
                OutlinedTextField(
                    value = website,
                    onValueChange = { website = it },
                    placeholder = { Text("https://yourwebsite.com") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFE5E5EF),
                        unfocusedBorderColor = Color(0xFFE5E5EF),
                        cursorColor = Color(0xFF1A1444)
                    ),
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        // Website card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9FB)),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFE5E5EF), RoundedCornerShape(20.dp))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = com.example.findjob.R.drawable.ic_about_me),
                        contentDescription = "about me",
                        tint = Color(0xFF1A1444),
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Industry",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = Color(0xFF1A1444)
                    )
                }
                Divider(
                    color = Color(0xFFE5E5EF),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
                OutlinedTextField(
                    value = industry,
                    onValueChange = { industry = it },
                    placeholder = { Text("Enter Industry") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFE5E5EF),
                        unfocusedBorderColor = Color(0xFFE5E5EF),
                        cursorColor = Color(0xFF1A1444)
                    ),
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        // Website card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9FB)),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFE5E5EF), RoundedCornerShape(20.dp))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = com.example.findjob.R.drawable.ic_about_me),
                        contentDescription = "Location",
                        tint = Color(0xFF1A1444),
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Location",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = Color(0xFF1A1444)
                    )
                }
                Divider(
                    color = Color(0xFFE5E5EF),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    placeholder = { Text("Enter your location") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFE5E5EF),
                        unfocusedBorderColor = Color(0xFFE5E5EF),
                        cursorColor = Color(0xFF1A1444)
                    ),
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        // Save button
        Button(
            onClick = {
                onSave(about, website)
                Toast.makeText(context, "About: $about\nWebsite: $website", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1444))
        ) {
            Text(
                text = "SAVE",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                letterSpacing = 4.sp
            )
        }
    }
}