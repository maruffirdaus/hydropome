package com.motion.hydropome.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.motion.hydropome.R
import com.motion.hydropome.common.type.Difficulty
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun RecommendationCard(
    image: String,
    title: String,
    difficulty: Difficulty,
    duration: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color.Black.copy(alpha = 0.4f),
                spotColor = Color.Black.copy(alpha = 0.4f)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFFFFFFF))
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = title,
            color = Color(0xFF060707),
            fontSize = 14.sp,
            fontWeight = FontWeight.W700,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val difficultyColor = when (difficulty) {
                Difficulty.EASY -> AppColors.difficultyEasy
                Difficulty.MEDIUM -> AppColors.difficultyMedium
                Difficulty.HARD -> AppColors.difficultyHard
            }

            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(difficultyColor)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = difficulty.label,
                color = difficultyColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                maxLines = 1
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                painter = painterResource(R.drawable.ic_clock),
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = Color(0xFF98A0AA)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "$duration Ming",
                color = Color(0xFF98A0AA),
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
private fun RecommendationCardPreview() {
    AppTheme {
        RecommendationCard(
            image = "",
            title = "Selada Hidroponik",
            difficulty = Difficulty.EASY,
            duration = "3-5",
            onClick = {}
        )
    }
}