package com.motion.hydropome.ui.sellproduct

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.motion.hydropome.ui.AppDestination
import com.motion.hydropome.ui.common.component.CustomButton
import com.motion.hydropome.ui.common.component.CustomTextField
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun SellProductScreen(
    uiState: SellProductUiState,
    onTitleChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onContactChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSell: (() -> Unit) -> Unit,
    navController: NavController
) {
    Scaffold { innerPadding ->
        AnimatedVisibility(
            visible = uiState.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
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
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            ) {
                val scrollState = rememberScrollState()

                AnimatedVisibility(
                    visible = scrollState.value == 0,
                    enter = fadeIn() + expandVertically(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(innerPadding.calculateTopPadding() + 224.dp)
                            .clip(BottomArcShape())
                            .background(AppColors.background)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(innerPadding)
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 28.dp + innerPadding.calculateBottomPadding())
                ) {
                    Box {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(66.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFFE8F5F2).copy(alpha = 0.2f))
                                        .clickable {
                                            navController.popBackStack(AppDestination.SellProduct, true)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_arrow_left),
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp),
                                        tint = Color(0xFFE8F5F2)
                                    )
                                }
                                Text(
                                    text = "Jual Produk",
                                    color = AppColors.textLight,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.W700
                                )
                                Spacer(Modifier.width(42.dp))
                            }
                            Spacer(Modifier.height(12.dp))
                            Text(
                                text = "Kamu Bisa Jual Apa Saja di sini!",
                                modifier = Modifier.fillMaxWidth(0.5f),
                                color = AppColors.textLight,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W700
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Kamu bisa jual tanamanmu, alat dan bahan atau perlengkapan hidroponik lainnya di sini.",
                                modifier = Modifier.fillMaxWidth(0.75f),
                                color = AppColors.textLight,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                        Spacer(Modifier.height(224.dp))
                    }
                    Spacer(Modifier.height(28.dp))
                    Text(
                        text = "Gambar",
                        color = AppColors.text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(152.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 1.dp,
                                color = Color(0xFFE8ECF4),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(Color(0xFFF7F8F9))
                            .clickable {},
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_gallery_add),
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = Color(0xFF717171)
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "+Tambah Foto",
                            color = Color(0xFF717171),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W700
                        )
                    }
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "Nama Produk",
                        color = AppColors.text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(Modifier.height(8.dp))
                    CustomTextField(
                        value = uiState.title,
                        onValueChange = onTitleChange,
                        placeholder = "Masukkan Nama Produk",
                        errorMessage = uiState.titleErrorMessage
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "Harga",
                        color = AppColors.text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(Modifier.height(8.dp))
                    CustomTextField(
                        value = uiState.price,
                        onValueChange = onPriceChange,
                        placeholder = "Masukkan Harga",
                        errorMessage = uiState.priceErrorMessage
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "Kontak Penjual",
                        color = AppColors.text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(Modifier.height(8.dp))
                    CustomTextField(
                        value = uiState.contact,
                        onValueChange = onContactChange,
                        placeholder = "Masukkan Kontak Penjual",
                        errorMessage = uiState.contactErrorMessage
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "Deskripsi",
                        color = AppColors.text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(Modifier.height(8.dp))
                    CustomTextField(
                        value = uiState.description,
                        onValueChange = onDescriptionChange,
                        placeholder = "Masukkan Deskripsi",
                        isSingleLine = false,
                        minLines = 8,
                        errorMessage = uiState.descriptionErrorMessage
                    )
                    Spacer(Modifier.height(42.dp))
                    CustomButton(
                        text = "Posting dan Jual",
                        onClick = {
                            onSell {
                                navController.popBackStack(AppDestination.SellProduct, true)
                            }
                        },
                        isEnabled = uiState.isFormValid,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview(device = "spec:width=1080px,height=3060px,dpi=440")
@Composable
fun SellProductScreenPreview() {
    AppTheme {
        SellProductScreen(
            uiState = SellProductUiState(),
            onTitleChange = {},
            onPriceChange = {},
            onContactChange = {},
            onDescriptionChange = {},
            onSell = {},
            navController = rememberNavController()
        )
    }
}