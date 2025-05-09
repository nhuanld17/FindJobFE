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
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import com.example.findjob.ui.components.bottomSheet.SingleChoiceBottomSheet
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.rememberDatePickerState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileForm(onSave: (about: String, website: String) -> Unit = { _, _ -> }) {
    var about by remember { mutableStateOf("") }
    var website by remember { mutableStateOf("") }
    var industry by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var since by remember { mutableStateOf("") }
    var specialization by remember { mutableStateOf("") }
    val context = LocalContext.current

    // 63 tỉnh thành Việt Nam
    val provinces = listOf(
        "An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu", "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cần Thơ", "Cao Bằng", "Đà Nẵng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội", "Hà Tĩnh", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "TP Hồ Chí Minh", "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"
    )
    var showLocationSheet by remember { mutableStateOf(false) }
    val locationSheetState = androidx.compose.material3.rememberModalBottomSheetState()

    // Date picker for Since
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

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
        // Location card (dropdown)
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9FB)),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFE5E5EF), RoundedCornerShape(20.dp))
                .clickable { showLocationSheet = true }
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
                Text(
                    text = if (location.isNotBlank()) location else "Select your location",
                    color = if (location.isNotBlank()) Color(0xFF1A1444) else Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        // Since card (date picker)
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9FB)),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFE5E5EF), RoundedCornerShape(20.dp))
                .clickable { showDatePicker = true }
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = com.example.findjob.R.drawable.ic_about_me),
                        contentDescription = "since",
                        tint = Color(0xFF1A1444),
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Since",
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
                Text(
                    text = if (since.isNotBlank()) since else "Select date",
                    color = if (since.isNotBlank()) Color(0xFF1A1444) else Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
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
                        text = "Specialization",
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
                    value = specialization,
                    onValueChange = { specialization = it },
                    placeholder = { Text("Enter your Specialization") },
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
                Toast.makeText(context, "About: $about\nWebsite: $website ", Toast.LENGTH_SHORT).show()
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

    // Bottom sheet chọn location
    SingleChoiceBottomSheet(
        showSheet = showLocationSheet,
        onDismiss = { showLocationSheet = false },
        sheetState = locationSheetState,
        title = "Choose Location",
        description = "Select your location",
        options = provinces,
        selectedOption = location,
        onOptionSelected = { location = it }
    )

    // Đặt ngoài Column, cuối hàm ProfileForm
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            since = dateFormat.format(Date(millis))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}