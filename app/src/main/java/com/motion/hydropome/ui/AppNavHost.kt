package com.motion.hydropome.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.motion.hydropome.ui.home.HomeScreen
import com.motion.hydropome.ui.home.HomeViewModel
import com.motion.hydropome.ui.login.LoginScreen
import com.motion.hydropome.ui.login.LoginViewModel
import com.motion.hydropome.ui.onboarding.OnboardingScreen
import com.motion.hydropome.ui.onboarding.OnboardingViewModel
import com.motion.hydropome.ui.register.RegisterScreen
import com.motion.hydropome.ui.register.RegisterViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.Onboarding
    ) {
        composable<AppDestination.Onboarding> {
            val viewModel: OnboardingViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            OnboardingScreen(
                uiState = uiState,
                onPreviousPage = viewModel::decrementPage,
                onNextPage = viewModel::incrementPage,
                navController = navController
            )
        }

        composable<AppDestination.Login> {
            val viewModel: LoginViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            LoginScreen(
                uiState = uiState,
                onNameChanged = viewModel::changeEmail,
                onPasswordChanged = viewModel::changePassword,
                onLogin = viewModel::login,
                navController = navController
            )
        }

        composable<AppDestination.Register> {
            val viewModel: RegisterViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            RegisterScreen(
                uiState = uiState,
                onNameChanged = viewModel::changeName,
                onEmailChanged = viewModel::changeEmail,
                onPasswordChanged = viewModel::changePassword,
                onConfirmPasswordChanged = viewModel::changeConfirmPassword,
                onRegister = viewModel::register,
                navController = navController
            )
        }

        composable<AppDestination.Home> {
            val viewModel: HomeViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            HomeScreen(
                uiState = uiState
            )
        }
    }
}