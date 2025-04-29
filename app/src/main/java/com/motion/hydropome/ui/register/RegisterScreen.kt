package com.motion.hydropome.ui.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motion.hydropome.ui.common.component.BackButton
import com.motion.hydropome.ui.common.component.CustomButton
import com.motion.hydropome.ui.common.component.CustomTextField
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun RegisterScreen(
    uiState: RegisterUiState,
    changeName: (String) -> Unit,
    changeEmail: (String) -> Unit,
    changePassword: (String) -> Unit,
    changeConfirmPassword: (String) -> Unit,
    onRegister: (()-> Unit) -> Unit,
    navController: NavController
) {
    Scaffold { innerPadding ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                BackButton(
                    onClick = {
                        navController.popBackStack()
                    }
                )
                Spacer(Modifier.height(40.dp))
                Text(
                    text = "Daftar Akun dan Mulai Bertani Hidroponik! \uD83C\uDF3F",
                    color = AppColors.text,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(Modifier.height(40.dp))
                Text(
                    text = "Nama",
                    color = AppColors.text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(Modifier.height(8.dp))
                CustomTextField(
                    value = uiState.name,
                    onValueChange = changeName,
                    placeholder = "Masukkan Nama",
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Email",
                    color = AppColors.text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(Modifier.height(8.dp))
                CustomTextField(
                    value = uiState.email,
                    onValueChange = changeEmail,
                    placeholder = "Masukkan Email",
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Buat Password",
                    color = AppColors.text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(Modifier.height(8.dp))
                CustomTextField(
                    value = uiState.password,
                    onValueChange = changePassword,
                    placeholder = "Masukkan Password",
                    isPassword = true
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Konfirmasi Password",
                    color = AppColors.text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(Modifier.height(8.dp))
                CustomTextField(
                    value = uiState.confirmPassword,
                    onValueChange = changeConfirmPassword,
                    placeholder = "Masukkan Password",
                    isPassword = true
                )
                Spacer(Modifier.height(40.dp))
                CustomButton(
                    text = "Daftar Akun",
                    onClick = {
                        onRegister {
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    AppTheme {
        RegisterScreen(
            uiState = RegisterUiState(),
            changeName = {},
            changeEmail = {},
            changePassword = {},
            changeConfirmPassword = {},
            onRegister = {},
            navController = rememberNavController()
        )
    }
}