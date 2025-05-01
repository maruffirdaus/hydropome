package com.motion.hydropome.ui.receipt

import com.motion.hydropome.common.model.User

data class ReceiptUiState(
    val receiptId: String = "",
    val total: Int = 0,
    val nama: String = "",
    val metode: String = "QRIS",
    val tanggal: String = "",
    val waktu: String = "",
    val user: User? = null,
    val isLoading: Boolean = true
)
