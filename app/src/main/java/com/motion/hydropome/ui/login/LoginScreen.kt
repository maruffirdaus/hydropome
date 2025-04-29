package com.motion.hydropome.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun LoginScreen(
    uiState: LoginUiState,
    onLogin: (() -> Unit) -> Unit,
    onNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    navController: NavController
) {

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()

        ) {
            BackButton(
                onClick = {
                    navController.popBackStack()
                }
            )
            Spacer(Modifier.height(40.dp))
            Text(
                color = AppColors.text,
                fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                text = "Selamat Datang Kembali Hydro! \uD83C\uDF3F\uD83C\uDF43"
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
                onValueChange = onNameChanged,
                placeholder = "Masukkan Email",
                errorMessage = uiState.emailErrorMessage
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Password",
                color = AppColors.text,
                fontSize = 16.sp,
                fontWeight = FontWeight.W700
            )
            CustomTextField(
                value = uiState.password,
                onValueChange = onPasswordChanged,
                placeholder = "Masukkan Password",
                errorMessage = uiState.passwordErrorMessage
            )
            Spacer(Modifier.height(40.dp))
            CustomButton(
                text = "Masuk",
                onClick = {
                    onLogin {
                        navController.navigate(AppDestination.Home)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onLogin = {},
            onNameChanged = {},
            onPasswordChanged = {},
            navController = rememberNavController()
        )
    }
}