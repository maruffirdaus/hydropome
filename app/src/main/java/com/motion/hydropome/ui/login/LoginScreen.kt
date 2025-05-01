package com.motion.hydropome.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
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
fun LoginScreen(
    uiState: LoginUiState,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: (() -> Unit) -> Unit,
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
                        navController.popBackStack(AppDestination.Onboarding, false)
                    }
                )
                Spacer(Modifier.height(40.dp))
                Text(
                    text = "Selamat Datang Kembali HydropoMate! \uD83C\uDF3F\uD83C\uDF43",
                    color = AppColors.text,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(Modifier.height(40.dp))
                Text(
                    text = "Email",
                    color = AppColors.text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(Modifier.height(8.dp))
                CustomTextField(
                    value = uiState.email,
                    onValueChange = onNameChange,
                    placeholder = "Masukkan Email",
                    errorMessage = uiState.emailErrorMessage
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Password",
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
                Spacer(Modifier.height(40.dp))
                CustomButton(
                    text = "Masuk",
                    onClick = {
                        onLogin {
                            navController.navigate(AppDestination.Personalization) {
                                popUpTo(0)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isEnabled = uiState.isFormValid
                )
                Spacer(Modifier.height(16.dp))

                val annotatedString = buildAnnotatedString {
                    append("Belum punya akun? ")
                    withStyle(style = SpanStyle(color = AppColors.primary)) {
                        append("Daftar")
                    }
                }

                Text(
                    text = annotatedString,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                navController.navigate(AppDestination.Register)
                            }
                        ),
                    color = Color(0xFF1E232C),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onNameChange = {},
            onPasswordChange = {},
            onLogin = {},
            navController = rememberNavController()
        )
    }
}