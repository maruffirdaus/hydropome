package com.motion.hydropome.ui.qris

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motion.hydropome.R
import com.motion.hydropome.ui.AppDestination
import com.motion.hydropome.ui.common.component.BackButton


@Composable
fun QRISScreen(
    uiState: QRISUiState,
    onStartCountdown: () -> Unit,
    onSetLoadingState: (Boolean) -> Unit,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        onStartCountdown()
    }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                BackButton(
                    onClick = {
                        navController.popBackStack(AppDestination.Main, false)
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text("QRIS", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Pindai kode QRIS dibawah untuk menyelesaikan pembayaran",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))


            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.qris),
                    contentDescription = "QR Code",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Selesaikan pembayaran dalam", color = Color.Gray, fontSize = 14.sp)

            Text(
                text = uiState.timer,
                color = Color(0xFF17BF7F),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {  },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF17BF7F))
            ) {
                Text("Unduh QRIS", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun QRISScreenPreview() {
    QRISScreen(
        uiState = QRISUiState(),
        onStartCountdown = {},
        onSetLoadingState = {},
        navController = rememberNavController()
    )
}
