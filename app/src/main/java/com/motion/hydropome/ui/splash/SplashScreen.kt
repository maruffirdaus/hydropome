package com.motion.hydropome.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motion.hydropome.R
import com.motion.hydropome.ui.AppDestination
import com.motion.hydropome.ui.theme.AppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    isLoggedIn: () -> Boolean,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        delay(1000L)
        navController.navigate(
            if (isLoggedIn()) {
                AppDestination.Main
            } else {
                AppDestination.Onboarding
            }
        ) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.splash),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    AppTheme {
        SplashScreen(
            isLoggedIn = { false },
            navController = rememberNavController()
        )
    }
}