package com.motion.hydropome.ui.receipt.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DashedDivider(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(1.dp)
    ) {
        val dashWidth = 10.dp.toPx()
        val spaceWidth = 5.dp.toPx()
        var x = 0f
        while (x < size.width) {
            drawLine(
                color = Color.Gray,
                start = Offset(x, 0f),
                end = Offset(x + dashWidth, 0f),
                strokeWidth = size.height
            )
            x += dashWidth + spaceWidth
        }
    }
}
