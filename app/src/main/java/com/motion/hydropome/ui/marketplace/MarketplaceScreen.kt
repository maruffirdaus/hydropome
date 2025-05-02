package com.motion.hydropome.ui.marketplace

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motion.hydropome.R
import com.motion.hydropome.common.model.Product
import com.motion.hydropome.common.type.Category
import com.motion.hydropome.ui.AppDestination
import com.motion.hydropome.ui.common.component.CustomButton
import com.motion.hydropome.ui.common.component.ProductCard
import com.motion.hydropome.ui.common.component.SearchBox
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.marketplace.component.CategoryChip
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun MarketplaceScreen(
    uiState: MarketplaceUiState,
    onProductsRefresh: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSelectedCategoryChange: (Category) -> Unit,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        onProductsRefresh()
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
                            WindowInsets.statusBars.asPaddingValues()
                                .calculateTopPadding() + 136.dp
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
                    top = WindowInsets.statusBars.asPaddingValues()
                        .calculateTopPadding() + 28.dp,
                    bottom = 28.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Marketplace",
                                color = AppColors.textLight,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W700
                            )
                            CustomButton(
                                text = "Jual Produk",
                                onClick = {
                                    navController.navigate(AppDestination.SellProduct)
                                },
                                height = 36.dp,
                                radius = 8.dp,
                                paddingHorizontal = 8.dp,
                                fontSize = 12.sp,
                                icon = R.drawable.ic_add
                            )
                        }
                        Spacer(Modifier.height(24.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            SearchBox(
                                value = uiState.searchQuery,
                                onValueChange = onSearchQueryChange,
                                placeholder = "Cari di marketplace...",
                                containerColor = Color(0xFFF7F8F9),
                                isShadowEnabled = true,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFE8E8E8),
                                        shape = CircleShape
                                    )
                                    .background(Color(0xFFFFFFFF))
                                    .clickable {},
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_shopping_cart),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = Color(0xFF171717)
                                )
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(Category.entries) { category ->
                                CategoryChip(
                                    label = category.label,
                                    onClick = {
                                        onSelectedCategoryChange(category)
                                    },
                                    isSelected = category == uiState.selectedCategory
                                )
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                    }
                }
                
                itemsIndexed(uiState.products) { index, product ->
                    val isEvenIndex = index % 2 == 0

                    ProductCard(
                        image = product.image,
                        category = product.category.label,
                        title = product.title,
                        regularPrice = product.regularPrice,
                        discountedPrice = product.discountedPrice,
                        onClick = {},
                        modifier = Modifier
                            .padding(
                                start = if (isEvenIndex) 20.dp else 0.dp,
                                end = if (isEvenIndex) 0.dp else 20.dp
                            )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MarketplaceScreenPreview() {
    AppTheme {
        Surface {
            MarketplaceScreen(
                uiState = MarketplaceUiState(
                    products = listOf(
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
                onProductsRefresh = {},
                onSearchQueryChange = {},
                onSelectedCategoryChange = {},
                navController = rememberNavController()
            )
        }
    }
}