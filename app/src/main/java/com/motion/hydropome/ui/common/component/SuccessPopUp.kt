package com.motion.hydropome.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.Image
import com.motion.hydropome.R
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SuccessPopUp(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(24.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {

                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF17BF7F)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_success_mark),
                        contentDescription = "CheckList"
                    )
                }


                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 80.dp)
                ) {
                    Text(
                        text = "Produk Ditambahkan Ke Keranjang",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }


                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(top = 140.dp)
                ) {
                    Button(
                        onClick = { /* handle click */ },
                        colors = ButtonDefaults.buttonColors( Color(0xFF17BF7F)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            text = "Lihat Keranjang Saya",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}