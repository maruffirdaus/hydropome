package com.motion.hydropome.ui.receipt


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.motion.hydropome.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ReceiptViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReceiptUiState())
    val uiState: StateFlow<ReceiptUiState> = _uiState

    init {
        loadUserAndReceipt()
    }

     fun loadUserAndReceipt() {
        viewModelScope.launch {
            val userResult = userRepository.getUser()

            userResult.onSuccess { user ->
                val currentTime = LocalDateTime.now()
                _uiState.value = ReceiptUiState(
                    receiptId = generateReceiptId(),
                    nama = user?.name.orEmpty(),
                    total = 35000,
                    metode = "QRIS",
                    tanggal = currentTime.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                    waktu = currentTime.format(DateTimeFormatter.ofPattern("HH:mm a"))
                )
            }.onFailure { exception ->

            }
        }
    }

    private fun generateReceiptId(): String {
        return "RCP-${System.currentTimeMillis()}"
    }
}
