package com.motion.hydropome.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motion.hydropome.R
import com.motion.hydropome.common.model.Plant
import com.motion.hydropome.common.model.Product
import com.motion.hydropome.common.type.Category
import com.motion.hydropome.common.type.Difficulty
import com.motion.hydropome.ui.AppDestination
import com.motion.hydropome.ui.common.component.ProductCard
import com.motion.hydropome.ui.common.component.SearchBox
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.home.component.RecommendationCard
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onUserRefresh: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onPlantsRefresh: () -> Unit,
    onFlashSaleProductsRefresh: () -> Unit,
    navController: NavController
) {
    var recommendationCardWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    LaunchedEffect(Unit) {
        onUserRefresh()
        onPlantsRefresh()
        onFlashSaleProductsRefresh()
    }

    AnimatedVisibility(
        visible = uiState.isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    AnimatedVisibility(
        visible = !uiState.isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val lazyGridState = rememberLazyGridState()
            val isAtTop by remember {
                derivedStateOf {
                    lazyGridState.firstVisibleItemIndex == 0 && lazyGridState.firstVisibleItemScrollOffset == 0
                }
            }

            AnimatedVisibility(
                visible = isAtTop,
                enter = fadeIn() + expandVertically(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + 256.dp
                        )
                        .clip(BottomArcShape())
                        .background(AppColors.background)
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                state = lazyGridState,
                contentPadding = PaddingValues(
                    top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + 28.dp,
                    bottom = 28.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Halo ${uiState.user?.name}! 👋",
                                    color = AppColors.textLight,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W600
                                )
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = "Berkebun Apa Hari Ini?",
                                    color = AppColors.textLight,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.W700
                                )
                            }
                            Image(
                                painter = painterResource(R.drawable.img_profile_default),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.height(28.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(188.dp)
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(16.dp),
                                    ambientColor = Color.Black.copy(alpha = 0.4f),
                                    spotColor = Color.Black.copy(alpha = 0.4f)
                                )
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFFFFFFF))
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ill_tree),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(end = 8.dp)
                                    .width(96.dp),
                                contentScale = ContentScale.FillWidth
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .align(Alignment.CenterStart)
                            ) {
                                Text(
                                    text = "Belum Ada Progress Tanaman Hari Ini...",
                                    modifier = Modifier.padding(end = 120.dp),
                                    color = AppColors.text,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.W700
                                )
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = "Ayo pilih tanaman pertama kamu\n" +
                                            "dan mulai tanam sekarang!",
                                    color = Color(0xFF757575),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W400
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        SearchBox(
                            value = uiState.searchQuery,
                            onValueChange = onSearchQueryChange,
                            placeholder = "Cari tanaman...",
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Rekomendasi Untukmu",
                                color = Color(0xFF060707),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W700
                            )
                            Text(
                                text = "Lihat Semua",
                                color = AppColors.primary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W700
                            )
                        }
                    }
                }

                itemsIndexed(uiState.plants) { index, plant ->
                    val isEvenIndex = index % 2 == 0

                    RecommendationCard(
                        image = plant.image,
                        title = plant.title,
                        difficulty = plant.difficulty,
                        onClick = {
                            navController.navigate(AppDestination.PlantDetails(plant.id))
                        },
                        duration = plant.duration,
                        modifier = Modifier
                            .padding(
                                start = if (isEvenIndex) 20.dp else 0.dp,
                                end = if (isEvenIndex) 0.dp else 20.dp
                            )
                            .onGloballyPositioned { coordinates ->
                                with(density) {
                                    recommendationCardWidth = coordinates.size.width.toDp()
                                }
                            }
                    )
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Starter  Kit Flash Sale \uD83D\uDD25",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W700
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(uiState.flashSaleProducts) { product ->
                                ProductCard(
                                    image = product.image,
                                    category = product.category.label,
                                    title = product.title,
                                    regularPrice = product.regularPrice,
                                    discountedPrice = product.discountedPrice,
                                    onClick = {
                                        navController.navigate(AppDestination.ProductDetails(product.id))
                                    },
                                    modifier = Modifier.width(recommendationCardWidth)
                                )
                            }
                        }
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
                uiState = HomeUiState(
                    plants = listOf(
                        Plant(
                            title = "Selada Hidroponik",
                            difficulty = Difficulty.EASY,
                            duration = "3-5"
                        ),
                        Plant(
                            title = "Bayam Hidroponik",
                            difficulty = Difficulty.MEDIUM,
                            duration = "3-4"
                        ),
                    ),
                    flashSaleProducts = listOf(
                        Product(
                            title = "Basic Starter Kit",
                            regularPrice = 50000,
                            discountedPrice = 25000,
                            category = Category.STARTER_KIT
                        ),
                        Product(
                            title = "Basic Starter Kit",
                            regularPrice = 50000,
                            discountedPrice = 25000,
                            category = Category.STARTER_KIT
                        ),
                        Product(
                            title = "Basic Starter Kit",
                            regularPrice = 50000,
                            discountedPrice = 25000,
                            category = Category.STARTER_KIT
                        )
                    )
                ),
                onUserRefresh = {},
                onSearchQueryChange = {},
                onPlantsRefresh = {},
                onFlashSaleProductsRefresh = {},
                navController = rememberNavController()
            )
        }
    }
}