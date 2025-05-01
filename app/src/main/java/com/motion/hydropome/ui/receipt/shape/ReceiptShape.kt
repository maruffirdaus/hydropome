package com.motion.hydropome.ui.receipt.shape

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class ReceiptShape(private val scallopRadius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path()

        with(density) {
            val scallopDiameter = scallopRadius * 2
            val width = size.width
            val height = size.height


            var x = 0f
            while (x < width) {
                path.arcTo(
                    Rect(x, -scallopRadius, x + scallopDiameter, scallopRadius),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = x == 0f
                )
                x += scallopDiameter
            }

            path.lineTo(width, height - 40.dp.toPx() - scallopRadius)

            path.arcTo(
                Rect(
                    width - scallopRadius,
                    height - 40.dp.toPx() - scallopRadius,
                    width + scallopRadius,
                    height - 40.dp.toPx() + scallopRadius
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )

            path.lineTo(width, height - scallopRadius)
            x = width
            while (x > 0f) {
                path.arcTo(
                    Rect(x - scallopDiameter, height - scallopDiameter, x, height + scallopRadius),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = false
                )
                x -= scallopDiameter
            }

            path.lineTo(0f, height - 40.dp.toPx() + scallopRadius)

            path.arcTo(
                Rect(
                    -scallopRadius,
                    height - 40.dp.toPx() - scallopRadius,
                    scallopRadius,
                    height - 40.dp.toPx() + scallopRadius
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )

            path.lineTo(0f, scallopRadius)

            path.close()
        }

        return Outline.Generic(path)
    }
}

