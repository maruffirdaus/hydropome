package com.motion.hydropome.ui.editprofile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProfileAvatar(
    avatarResId: Int,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit = {}
) {
    Box(
        modifier = modifier.size(150.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFFE6F0EC), CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = avatarResId),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(80.dp)
            )
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF2E7D32), CircleShape)
                .clip(CircleShape)
                .clickable { onEditClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
