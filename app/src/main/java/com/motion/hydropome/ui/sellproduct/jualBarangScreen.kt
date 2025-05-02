package com.motion.hydropome.ui.sellproduct
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motion.hydropome.ui.common.component.BackButton
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.sellproduct.component.PostAndSellButton
import com.motion.hydropome.ui.sellproduct.component.SellProductForm
import com.motion.hydropome.ui.theme.AppColors

@Composable
fun FormJualBarangScreen(
    uiState: SellProductUiState,
    onProductNameChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onContactChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    val scrollState = rememberScrollState()
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(bottom = 80.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(statusBarHeight + 330.dp)
                    .clip(BottomArcShape())
                    .background(AppColors.background)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, start = 30.dp)
                ) {
                    BackButton { }
                    Text(
                        "Jual Barang",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier.padding(start = 50.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 110.dp, start = 20.dp, end = 20.dp)
                ) {
                    Text(
                        "Kamu Bisa Jual Apa",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.W700,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        "Saja di sini!",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.W700,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Kamu bisa jual tanamanmu, alat dan bahan atau perlengkapan hidroponik lainnya di sini.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.White
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                SellProductForm(
                    productName = uiState.productName,
                    onProductNameChange = onProductNameChange,
                    category = uiState.category,
                    onCategoryChange = onCategoryChange,
                    price = uiState.price,
                    onPriceChange = onPriceChange,
                    contact = uiState.contact,
                    onContactChange = onContactChange,
                    description = uiState.description,
                    onDescriptionChange = onDescriptionChange
                )

                Spacer(modifier = Modifier.height(20.dp))

                PostAndSellButton(
                    onClick = onSubmit
                )
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (uiState.isSuccess) {
            LaunchedEffect(Unit) {
                println("Product successfully posted!")
            }
        }

        uiState.errorMessage?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormJualBarangScreenPreview() {
    FormJualBarangScreen(
        uiState = SellProductUiState(),
        onProductNameChange = {},
        onCategoryChange = {},
        onPriceChange = {},
        onContactChange = {},
        onDescriptionChange = {},
        onSubmit = {}
    )
}

