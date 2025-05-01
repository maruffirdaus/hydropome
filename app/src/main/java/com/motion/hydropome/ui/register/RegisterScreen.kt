package com.motion.hydropome.ui.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.motion.hydropome.ui.AppDestination
import com.motion.hydropome.ui.common.component.BackButton
import com.motion.hydropome.ui.common.component.CustomButton
import com.motion.hydropome.ui.common.component.CustomTextField
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun RegisterScreen(
    uiState: RegisterUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onRegister: (()-> Unit) -> Unit,
    navController: NavController
) {
    Scaffold { innerPadding ->
        AnimatedVisibility(
            visible = uiState.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        AnimatedVisibility(
            visible = !uiState.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.ime.asPaddingValues())
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                BackButton(
                    onClick = {
                        navController.popBackStack(AppDestination.Login, false)
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
                    text = "Nama Lengkap",
                    color = AppColors.text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(Modifier.height(8.dp))
                CustomTextField(
                    value = uiState.name,
                    onValueChange = onNameChange,
                    placeholder = "Masukkan Nama Lengkap",
                    errorMessage = uiState.nameErrorMessage
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
                    onValueChange = onEmailChange,
                    placeholder = "Masukkan Email",
                    errorMessage = uiState.emailErrorMessage
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
                    onValueChange = onPasswordChange,
                    placeholder = "Masukkan Password",
                    isPassword = true,
                    errorMessage = uiState.passwordErrorMessage
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
                    onValueChange = onConfirmPasswordChange,
                    placeholder = "Masukkan Password",
                    isPassword = true,
                    errorMessage = uiState.confirmPasswordErrorMessage
                )
                Spacer(Modifier.height(40.dp))
                CustomButton(
                    text = "Daftar Akun",
                    onClick = {
                        onRegister {
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isEnabled = uiState.isFormValid
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
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onRegister = {},
            navController = rememberNavController()
        )
    }
}