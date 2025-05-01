package com.motion.hydropome.ui.marketplace

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motion.hydropome.ui.common.component.SearchBox
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.marketplace.component.CategoryRow
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun MarketplaceScreen(
    uiState: MarketplaceUiState
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    var selectedCategory by remember { mutableStateOf("Starter Kit") }
    val categories = listOf("Starter Kit", "Dari Customer", "Media Tanam")
    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(statusBarHeight + 200.dp)
                    .clip(BottomArcShape())
                    .background(AppColors.background)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = statusBarHeight + 50.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Marketplace",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .padding(top = 40.dp)
                            .offset(y = -25.dp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(
                        onClick = { /* your click logic here */ },
                        modifier = Modifier
                            .wrapContentWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .padding(top = 45.dp)
                            .padding(start = 28.dp)
                            .offset(y = -25.dp)
                        ,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF179778))
                    ) {
                        Row{
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "Jual Barang",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W700,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }

                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 160.dp)
                    .clip(RoundedCornerShape(19.dp))
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp, start = 15.dp, end = 24.dp)
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    SearchBox(
                        value = "",
                        onValueChange = { },
                        placeholder = "Cari di marketplace....",
                        modifier = Modifier.width(280.dp)

                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 30.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Add",
                                tint = Color.Black // Use white to show on black background
                            )
                        }
                    }

                }

            }
            //categories
            Row(
                modifier = Modifier
                    .padding(top = 240.dp)
                    .fillMaxWidth()
            ){
                CategoryRow(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onCategorySelected = { selectedCategory = it }
                )

            }
            //card
            Row(
                modifier = Modifier
                    .padding(top = 240.dp)
                    .fillMaxWidth()
            ){

            }
        }
    }
}

@Preview
@Composable
fun MarketplaceScreenPreview() {
    AppTheme {
        MarketplaceScreen(
            uiState = MarketplaceUiState()
        )
    }
}