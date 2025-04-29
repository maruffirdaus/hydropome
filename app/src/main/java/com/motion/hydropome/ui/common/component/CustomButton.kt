package com.motion.hydropome.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motion.hydropome.ui.theme.AppColors

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(AppColors.primary.copy(alpha = if (isEnabled) 1f else 0.5f))
            .clickable(
                enabled = isEnabled,
                onClick = onClick
            )
            .padding(horizontal = 16.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = AppColors.textLight.copy(alpha = if (isEnabled) 1f else 0.5f),
            fontSize = 16.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Preview
@Composable
private fun CustomButtonPreview() {
    CustomButton(
        text = "Button",
        onClick = {}
    )
}