package com.motion.hydropome.ui.receipt.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.motion.hydropome.ui.receipt.shape.ReceiptShape
import com.motion.hydropome.R
import com.motion.hydropome.ui.receipt.function.generateRandomID

@Composable
fun ReceiptCard(
    total: Int,
    nama: String,
    metode:String,
    tanggal: String,
    waktu: String,
    modifier: Modifier = Modifier
) {
    val randomID = remember { generateRandomID(8) }

    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        shape = ReceiptShape(scallopRadius = 13f),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.checklist),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Pembayaran Berhasil", fontWeight = FontWeight.Bold)
            Text(
                text = "Rp %,d".format(total).replace(',', '.'),
                color = Color(0xFF179778),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                InfoRow("ID Pesanan", randomID)
                InfoRow("Nama", nama)
                InfoRow("Metode Pembayaran", metode)
                InfoRow("Tanggal Pemesanan", tanggal)
                InfoRow("Waktu Pemesanan", waktu)
            }

            DashedDivider(modifier = Modifier.padding(vertical = 16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", fontWeight = FontWeight.SemiBold)
                Text(
                    "Rp %,d".format(total).replace(',', '.'),
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF179778)
                )
            }
        }
    }
}
