package com.motion.hydropome.ui.marketplace.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motion.hydropome.common.type.Category
import com.motion.hydropome.ui.theme.AppColors

@Composable
fun CategoryChip(
    label: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Box(
        modifier = Modifier
            .height(30.dp)
            .clip(RoundedCornerShape(8.dp))
            .let {
                if (isSelected) {
                    it
                } else {
                    it.border(
                        width = 1.dp,
                        color = AppColors.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }
            .background(if (isSelected) AppColors.primary else Color(0xFFE8F5F2))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = if (isSelected) {
                AppColors.textLight
            } else {
                AppColors.primary
            },
            fontSize = 12.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Preview
@Composable
fun SelectedCategoryChipPreview() {
    CategoryChip(
        label = Category.STARTER_KIT.label,
        onClick = {},
        isSelected = true
    )
}

@Preview
@Composable
fun UnselectedCategoryChipPreview() {
    CategoryChip(
        label = Category.PLANTING_MEDIUM.label,
        onClick = {},
        isSelected = false
    )
}