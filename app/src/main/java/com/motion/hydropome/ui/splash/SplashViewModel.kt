package com.motion.hydropome.ui.splash

import androidx.lifecycle.ViewModel
import com.motion.hydropome.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun isLoggedIn(): Boolean = authRepository.isLoggedIn()
}