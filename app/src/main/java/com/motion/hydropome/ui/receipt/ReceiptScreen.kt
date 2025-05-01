package com.motion.hydropome.ui.receipt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.motion.hydropome.ui.common.component.BackButton
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motion.hydropome.ui.receipt.component.ReceiptCard

import com.motion.hydropome.ui.theme.AppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.theme.AppColors

@Composable
fun ReceiptScreen() {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(statusBarHeight + 200.dp)
                    .clip(BottomArcShape())
                    .background(AppColors.background)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = statusBarHeight + 50.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BackButton(onClick = { /* Handle back */ })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Status Bayar",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(start = 70.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        ReceiptCard(
            total = 35000,
            nama = "Hydropome",
            metode = "QRIS",
            tanggal = "1 mei 2025",
            waktu = "12.01 PM",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = statusBarHeight + 140.dp)
        )
    }
}



@Preview
@Composable
private fun ReceiptScreenPreview() {
    AppTheme {
        ReceiptScreen(

        )
    }
}