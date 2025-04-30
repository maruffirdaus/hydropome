package com.motion.hydropome.ui.common.shape


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
            val width = size.width
            val height = size.height
            val arcHeight = width / 8

            moveTo(0f, 0f)
            lineTo(0f, height - arcHeight)

            quadraticTo(
                width / 2f,
                height + arcHeight,
                width,
                height - arcHeight
            )

            lineTo(width, 0f)
            close()
        }

        return Outline.Generic(path)
    }
}
