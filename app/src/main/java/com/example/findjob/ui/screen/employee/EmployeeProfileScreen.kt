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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.findjob.const.VietnamProvinces
import com.example.findjob.ui.components.EmployeeBottomBar
import com.example.findjob.viewmodel.EmployeeProfileViewModel
import com.example.findjob.viewmodel.ProfileState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeProfileScreen(
    navController: NavController,
    viewModel: EmployeeProfileViewModel = hiltViewModel()
) {
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

    // State cho ngày sinh
    var day by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var dateError by remember { mutableStateOf<String?>(null) }
    
    // State cho dropdown
    var expandedDay by remember { mutableStateOf(false) }
    var expandedMonth by remember { mutableStateOf(false) }
    var expandedYear by remember { mutableStateOf(false) }
    
    // Tạo danh sách ngày, tháng, năm
    val days = (1..31).map { it.toString().padStart(2, '0') }
    val months = (1..12).map { it.toString().padStart(2, '0') }
    val years = (1900..2024).map { it.toString() }
    
    // State cho location
    var location by remember { mutableStateOf("") }
    var expandedLocation by remember { mutableStateOf(false) }
    
    // State cho các trường thông tin
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    
    // State cho mật khẩu
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Collect state từ ViewModel
    val profileState by viewModel.state.collectAsState()

    // Gọi API khi màn hình được tạo
    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }

    // Xử lý state từ API
    LaunchedEffect(profileState) {
        when (profileState) {
            is ProfileState.Success -> {
                val profile = (profileState as ProfileState.Success).profile
                fullName = profile.fullName ?: ""
                email = profile.email ?: ""
                phoneNumber = profile.phoneNumber ?: ""
                gender = profile.gender ?: ""
                location = profile.location ?: ""
                
                // Xử lý dateOfBirth an toàn hơn
                profile.dateOfBirth?.let { dob ->
                    day = dob.day?.toString() ?: ""
                    month = dob.month?.toString() ?: ""
                    year = dob.year?.toString() ?: ""
                }
            }
            else -> {}
        }
    }

    // Hàm validate date
    fun validateDate(d: String, m: String, y: String): Boolean {
        if (d.isEmpty() || m.isEmpty() || y.isEmpty()) return true
        
        val dayNum = d.toIntOrNull() ?: return false
        val monthNum = m.toIntOrNull() ?: return false
        val yearNum = y.toIntOrNull() ?: return false
        
        if (yearNum < 1900 || yearNum > 2024) {
            dateError = "Năm phải từ 1900 đến 2024"
            return false
        }
        
        if (monthNum < 1 || monthNum > 12) {
            dateError = "Tháng phải từ 1 đến 12"
            return false
        }
        
        val daysInMonth = when (monthNum) {
            2 -> if (yearNum % 4 == 0 && (yearNum % 100 != 0 || yearNum % 400 == 0)) 29 else 28
            4, 6, 9, 11 -> 30
            else -> 31
        }
        
        if (dayNum < 1 || dayNum > daysInMonth) {
            dateError = "Ngày không hợp lệ"
            return false
        }
        
        dateError = null
        return true
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

        // Hiển thị loading
        if (profileState is ProfileState.Loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }

        // Hiển thị error
        if (profileState is ProfileState.Error) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (profileState as ProfileState.Error).message,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
        }

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
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = { Text("Fullname") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Date of birth",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Ngày
                        OutlinedTextField(
                            value = day,
                            onValueChange = { input ->
                                if (input.length <= 2 && input.all { it.isDigit() }) {
                                    day = input
                                    validateDate(day, month, year)
                                }
                            },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            placeholder = { Text("DD") },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF4B3FAE),
                                unfocusedBorderColor = Color.Gray
                            )
                        )
                        
                        // Tháng
                        OutlinedTextField(
                            value = month,
                            onValueChange = { input ->
                                if (input.length <= 2 && input.all { it.isDigit() }) {
                                    month = input
                                    validateDate(day, month, year)
                                }
                            },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            placeholder = { Text("MM") },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF4B3FAE),
                                unfocusedBorderColor = Color.Gray
                            )
                        )
                        
                        // Năm
                        OutlinedTextField(
                            value = year,
                            onValueChange = { input ->
                                if (input.length <= 4 && input.all { it.isDigit() }) {
                                    year = input
                                    validateDate(day, month, year)
                                }
                            },
                            modifier = Modifier.weight(1.5f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            placeholder = { Text("YYYY") },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF4B3FAE),
                                unfocusedBorderColor = Color.Gray
                            )
                        )
                    }
                    if (dateError != null) {
                        Text(
                            text = dateError!!,
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Gender", fontWeight = FontWeight.Medium, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
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
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email address") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text("Phone number") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ExposedDropdownMenuBox(
                        expanded = expandedLocation,
                        onExpandedChange = { expandedLocation = it },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = location,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Location") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedLocation) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF4B3FAE),
                                unfocusedBorderColor = Color.Gray
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = expandedLocation,
                            onDismissRequest = { expandedLocation = false }
                        ) {
                            VietnamProvinces.provinces.forEach { province ->
                                DropdownMenuItem(
                                    text = { Text(province) },
                                    onClick = {
                                        location = province
                                        expandedLocation = false
                                    }
                                )
                            }
                        }
                    }
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
            
            // Box mật khẩu
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                color = Color.White,
                shape = RoundedCornerShape(24.dp),
                shadowElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Change Password",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF1A1446)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = currentPassword,
                        onValueChange = { currentPassword = it },
                        label = { Text("Current Password") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Password"
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = { Text("New Password") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Password"
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirm New Password") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Password"
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
                        Text("CHANGE PASSWORD", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
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