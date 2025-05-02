package com.motion.hydropome.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun TitleBar(
    title: String,
    onNavigationActionClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton(
            onClick = onNavigationActionClick
        )
        Text(
            text = title,
            color = Color(0xFF000000),
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
        )
        Spacer(Modifier.width(40.dp))
    }
}

@Preview
@Composable
private fun TitleBarPreview() {
    AppTheme {
        Surface {
            TitleBar(
                title = "Pantau Tanaman",
                onNavigationActionClick = {}
            )
        }
    }
}