package com.motion.hydropome.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.motion.hydropome.ui.theme.AppTheme
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.motion.hydropome.R
import com.motion.hydropome.ui.common.component.HomeCard
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.theme.AppColors

data class CardItem(
    val imageRes: Int,
    val title: String,
    val difficulty: String,
    val duration: String
)
@Composable
fun HomeScreen(
    uiState: HomeUiState
) {
    val items = listOf(
        CardItem(R.drawable.onboarding_1, "Selada Hidroponik", "Mudah", "3-5 Ming"),
        CardItem(R.drawable.onboarding_0, "Bayam Hidroponik", "Mudah", "3-4 Ming"),
        CardItem(R.drawable.onboarding_1, "Pakcoy Hidroponik", "Mudah", "4-5 Ming"),
        CardItem(R.drawable.onboarding_0, "Tomat Cherry", "Sedang", "8-10 Ming"),
        CardItem(R.drawable.onboarding_0, "Seledri Hidroponik", "Sedang", "5-6 Ming"),
        CardItem(R.drawable.onboarding_1, "Stroberi Hidroponik", "Sulit", "12-16 Ming"),
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp)
                .clip(BottomArcShape())
                .background(AppColors.background)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Halo Mellafesa! 👋", color = Color.White, fontSize = 16.sp)
                        Text(
                            "Berkebun Apa Hari Ini?",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.ic_eye),
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Orange Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(265.dp)
                        .padding(horizontal = 20.dp)
                        .background(Color(0xFFFB9A23), shape = RoundedCornerShape(15.dp))
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Search Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Cari tanaman...",
                            color = Color.Gray,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Rekomendasi Untukmu",
                        color = AppColors.text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700
                    )
                    Text(
                        text = "Lihat Semua",
                        color = Color(0xFF179778),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700
                    )
                }
            }

            // Grid of items
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.heightIn(max = 2000.dp) // Prevent infinite height error
                ) {
                    items(items) { item ->
                        HomeCard(
                            imageRes = item.imageRes,
                            title = item.title,
                            difficultyLabel = item.difficulty,
                            durationLabel = item.duration,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }


}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        Surface {
            HomeScreen(
                uiState = HomeUiState()
            )
        }
    }
}