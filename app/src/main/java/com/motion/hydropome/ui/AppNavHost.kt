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
import com.motion.hydropome.ui.main.MainScreen
import com.motion.hydropome.ui.main.MainViewModel
import com.motion.hydropome.ui.marketplace.MarketplaceScreen
import com.motion.hydropome.ui.marketplace.MarketplaceViewModel
import com.motion.hydropome.ui.monitorplants.MonitorPlantsScreen
import com.motion.hydropome.ui.monitorplants.MonitorPlantsViewModel
import com.motion.hydropome.ui.onboarding.OnboardingScreen
import com.motion.hydropome.ui.onboarding.OnboardingViewModel
import com.motion.hydropome.ui.personalization.PersonalizationScreen
import com.motion.hydropome.ui.personalization.PersonalizationViewModel
import com.motion.hydropome.ui.profile.ProfileScreen
import com.motion.hydropome.ui.profile.ProfileViewModel
import com.motion.hydropome.ui.qris.QRISScreen
import com.motion.hydropome.ui.qris.QrisViewModel
import com.motion.hydropome.ui.register.RegisterScreen
import com.motion.hydropome.ui.register.RegisterViewModel
import com.motion.hydropome.ui.splash.SplashScreen
import com.motion.hydropome.ui.splash.SplashViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.Qris
    ) {
        composable<AppDestination.Splash> {
            val viewModel: SplashViewModel = hiltViewModel()

            SplashScreen(
                isLoggedIn = viewModel::isLoggedIn,
                navController = navController
            )
        }

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
                onNameChange = viewModel::changeEmail,
                onPasswordChange = viewModel::changePassword,
                onLogin = viewModel::login,
                navController = navController
            )
        }

        composable<AppDestination.Register> {
            val viewModel: RegisterViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            RegisterScreen(
                uiState = uiState,
                onNameChange = viewModel::changeName,
                onEmailChange = viewModel::changeEmail,
                onPasswordChange = viewModel::changePassword,
                onConfirmPasswordChange = viewModel::changeConfirmPassword,
                onRegister = viewModel::register,
                navController = navController
            )
        }

        composable<AppDestination.Personalization> {
            val viewModel: PersonalizationViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            PersonalizationScreen(
                uiState = uiState,
                onNextPage = viewModel::incrementPage,
                onPreviousPage = viewModel::decrementPage,
                onSelectedAnswerChange = viewModel::changeSelectedAnswers,
                onSavePreferences = viewModel::savePreferences,
                navController = navController
            )
        }
        composable<AppDestination.Qris> {
            val viewModel: QrisViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            QRISScreen(
                uiState = uiState,
                onStartCountdown = viewModel::startCountdown,
                onSetLoadingState = viewModel::setLoadingState,
                navController = navController
            )
        }

        composable<AppDestination.Main> {
            val mainViewModel: MainViewModel = hiltViewModel()
            val mainUiState by mainViewModel.uiState.collectAsState()

            val homeViewModel: HomeViewModel = hiltViewModel()
            val homeUiState by homeViewModel.uiState.collectAsState()

            val monitorPlantsViewModel: MonitorPlantsViewModel = hiltViewModel()
            val monitorPlantsUiState by monitorPlantsViewModel.uiState.collectAsState()

            val marketplaceViewModel: MarketplaceViewModel = hiltViewModel()
            val marketplaceUiState by marketplaceViewModel.uiState.collectAsState()

            val profileViewModel: ProfileViewModel = hiltViewModel()
            val profileUiState by profileViewModel.uiState.collectAsState()

            MainScreen(
                uiState = mainUiState,
                homeScreen = {
                    HomeScreen(
                        uiState = homeUiState,
                        onUserRefresh = homeViewModel::refreshUser,
                        onSearchQueryChange = homeViewModel::changeSearchQuery,
                        onPlantsRefresh = homeViewModel::refreshPlants
                    )
                },
                monitorPlantsScreen = {
                    MonitorPlantsScreen(
                        uiState = monitorPlantsUiState
                    )
                },
                marketplaceScreen = {
                    MarketplaceScreen(
                        uiState = marketplaceUiState
                    )
                },
                profileScreen = {
                    ProfileScreen(
                        uiState = profileUiState
                    )
                },
                onSelectedNavItemChange = mainViewModel::changeSelectedIndex
            )
        }
    }
}