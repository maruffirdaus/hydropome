package com.motion.hydropome.ui.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.motion.hydropome.R
import com.motion.hydropome.common.model.Product
import com.motion.hydropome.common.type.Category
import com.motion.hydropome.ui.AppDestination
import com.motion.hydropome.ui.common.component.TitleBar
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductDetailsScreen(
    productId: String,
    uiState: ProductDetailsUiState,
    onProductRefresh: (String) -> Unit,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        onProductRefresh(productId)
    }

    Scaffold(
        topBar = {
            TitleBar(
                title = "Detail Produk",
                onNavigationActionClick = {
                    navController.popBackStack(AppDestination.ProductDetails(productId), true)
                }
            )
        }
    ) { innerPadding ->
        AnimatedVisibility(
            visible = uiState.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding.calculateTopPadding()),
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
                    .padding(top = innerPadding.calculateTopPadding())
            ) {
                val scrollState = rememberScrollState()

                uiState.product?.let { product ->
                    AnimatedVisibility(
                        visible = scrollState.value == 0,
                        enter = fadeIn() + expandVertically(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        if (product.image.isNotBlank()) {
                            AsyncImage(
                                model = product.image,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(256.dp)
                                    .clip(BottomArcShape()),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Image(
                                painter = painterResource(R.drawable.img_product_placeholder),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(256.dp)
                                    .clip(BottomArcShape()),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 12.dp + innerPadding.calculateBottomPadding())
                    ) {
                        Spacer(Modifier.height(256.dp + 12.dp))
                        Text(
                            text = product.category.label,
                            color = Color(0xFF757575),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = product.title,
                            color = Color(0xFF060707),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W700
                        )
                        Spacer(Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = buildString {
                                    append("Rp ")
                                    append(
                                        NumberFormat.getNumberInstance(Locale("in", "ID"))
                                            .format(product.discountedPrice ?: product.regularPrice)
                                    )
                                },
                                color = AppColors.primary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W700,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                            if (product.discountedPrice != null) {
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = buildString {
                                        append("Rp ")
                                        append(
                                            NumberFormat.getNumberInstance(Locale("in", "ID"))
                                                .format(product.regularPrice)
                                        )
                                    },
                                    color = Color(0xFF757575),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W600,
                                    textDecoration = TextDecoration.LineThrough,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )
                            }
                        }
                        Spacer(Modifier.height(24.dp))
                        Text(
                            text = "Deskripsi",
                            color = Color(0xFF060707),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W700
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = product.description,
                            color = Color(0xFF757575),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Justify
                        )
                        product.sellerContact?.let { sellerContact ->
                            Spacer(Modifier.height(24.dp))
                            Text(
                                text = "Kontak Penjual",
                                color = Color(0xFF060707),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W700
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = sellerContact,
                                color = Color(0xFF757575),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProductDetailsScreenPreview() {
    AppTheme {
        ProductDetailsScreen(
            productId = "",
            uiState = ProductDetailsUiState(
                product = Product(
                    title = "Paket Pipa NFT",
                    description = "Basic Starter Kit ini cocok untuk kamu yang baru mulai berkebun hidroponik di rumah. Sistem pipa NFT (Nutrient Film Technique) sangat populer karena perawatannya mudah, hemat air, dan hasil tanamnya cepat dipanen. Kit ini sudah termasuk perlengkapan dasar untuk memulai menanam sayuran seperti selada, bayam, kangkung, atau sawi tanpa tanah.",
                    regularPrice = 50000,
                    discountedPrice = 25000,
                    category = Category.CUSTOMER_PRODUCT,
                    sellerContact = "Whatsapp: 08912345678  (Joshua Theo)"
                )
            ),
            onProductRefresh = {},
            navController = rememberNavController()
        )
    }
}