package com.motion.hydropome.ui.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.motion.hydropome.R

@Composable
fun OrderStatusSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OrderStatusItem(
            icon = painterResource(id = R.drawable.box),
            label = "Diproses",
            onClick = {}
        )
        OrderStatusItem(
            icon = painterResource(id = R.drawable.shipping),
            label = "Dikirim",
            onClick = {}
        )
        OrderStatusItem(
            icon = painterResource(id = R.drawable.selesai),
            label = "Selesai",
            onClick = {}
        )
    }
}

@Composable
fun OrderStatusItem(
    icon: Painter,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Image(
            painter = icon,
            contentDescription = label,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )
    }
}
@Composable
fun ProfileOptionItem(
    icon: Painter,
    iconBackgroundColor: Color,
    text: String,
    textColor: Color = Color.Black,
    arrowColor: Color = Color.Black,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { onClick() }
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(iconBackgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = text,
                    color = textColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = arrowColor
            )
        }
    }
}
