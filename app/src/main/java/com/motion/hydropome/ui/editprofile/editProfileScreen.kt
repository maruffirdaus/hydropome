package com.motion.hydropome.ui.editprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.motion.hydropome.R
import com.motion.hydropome.ui.common.component.BackButton
import com.motion.hydropome.ui.theme.AppTheme
import androidx.compose.material3.*

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motion.hydropome.ui.common.component.CustomButton
import com.motion.hydropome.ui.common.component.CustomTextField
import com.motion.hydropome.ui.editprofile.component.ProfileAvatar

@Composable
fun EditProfileScreen(
    uiState: EditProfileUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNewPasswordChange: (String) -> Unit,
    onSaveClick: (() -> Unit) -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton {  }
            Text(
                "Edit Profile",
                modifier = Modifier.padding(start = 60.dp),
                fontSize = 27.sp,
                fontWeight = FontWeight.W700
            )
        }

        ProfileAvatar(
            avatarResId = R.drawable.img_profile_default,
            onEditClick = {  },
            modifier = Modifier
                .padding(top = 20.dp)
                .offset(x = -23.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text("Nama Lengkap", fontSize = 19.sp, fontWeight = FontWeight.W700)
            CustomTextField(
                value = uiState.name,
                onValueChange = onNameChange,
                placeholder = "Enter your name",
                isPassword = false,
                errorMessage = uiState.nameErrorMessage
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Email", fontSize = 19.sp, fontWeight = FontWeight.W700)
            CustomTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                placeholder = "Enter your email",
                isPassword = false,
                errorMessage = uiState.emailErrorMessage
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Password", fontSize = 19.sp, fontWeight = FontWeight.W700)
            CustomTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                placeholder = "Enter current password",
                isPassword = true,
                errorMessage = uiState.passwordErrorMessage
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Ubah Password", fontSize = 19.sp, fontWeight = FontWeight.W700)
            CustomTextField(
                value = uiState.newPassword,
                onValueChange = onNewPasswordChange,
                placeholder = "Enter new password",
                isPassword = true,
                errorMessage = uiState.newPasswordErrorMessage
            )

            Spacer(modifier = Modifier.height(50.dp))

            CustomButton(
                text = if (uiState.isLoading) "Menyimpan..." else "Simpan",
                onClick = {
                    onSaveClick {
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                isEnabled = uiState.isFormValid && !uiState.isLoading
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun EditProfileScreenPreview() {
    AppTheme {
        EditProfileScreen(
            uiState = EditProfileUiState(
                name = "John Doe",
                email = "john.doe@example.com",
                password = "password123",
                newPassword = "newpassword123",
                isFormValid = true,
                isLoading = false
            ),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onNewPasswordChange = {},
            onSaveClick = {},
            navController = rememberNavController()
        )
    }
}
