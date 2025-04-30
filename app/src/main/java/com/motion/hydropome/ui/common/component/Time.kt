package com.motion.hydropome.ui.common.component
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CountdownTimer(hours: Int, minutes: Int, seconds: Int) {
    var remainingTime by remember { mutableStateOf(hours * 3600 + minutes * 60 + seconds) }

    LaunchedEffect(Unit) {
        while (remainingTime > 0) {
            delay(1000L)
            remainingTime -= 1
        }
    }

    val hrs = remainingTime / 3600
    val mins = (remainingTime % 3600) / 60
    val secs = remainingTime % 60

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TimeBox(unit = hrs)
        Text(":", fontSize = 20.sp, fontWeight = FontWeight.W700)
        TimeBox(unit = mins)
        Text(":", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        TimeBox(unit = secs)
    }
}

@Composable
fun TimeBox(unit: Int) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF179778)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = unit.toString().padStart(2, '0'),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}
