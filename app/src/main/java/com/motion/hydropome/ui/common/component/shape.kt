package com.motion.hydropome.ui.common.component


import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection


class BottomArcShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, size.height * 0.76f) // Go lower

            quadraticBezierTo(
                size.width / 2f,
                size.height * 1.2f, // Deeper curve
                size.width,
                size.height * 0.76f
            )

            lineTo(size.width, 0f)
            close()
        }

        return Outline.Generic(path)
    }
}
