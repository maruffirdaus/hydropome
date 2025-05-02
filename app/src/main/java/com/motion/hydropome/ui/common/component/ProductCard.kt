package com.motion.hydropome.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.motion.hydropome.R
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductCard(
    image: String,
    category: String,
    title: String,
    regularPrice: Int,
    discountedPrice: Int?,
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
        if (image.isNotBlank()) {
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(R.drawable.img_product_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = category,
            color = Color(0xFF757575),
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(Modifier.height(4.dp))
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildString {
                    append("Rp ")
                    append(
                        NumberFormat.getNumberInstance(Locale("in", "ID"))
                            .format(discountedPrice ?: regularPrice)
                    )
                },
                color = AppColors.primary,
                fontSize = 14.sp,
                fontWeight = FontWeight.W700,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            if (discountedPrice != null) {
                Spacer(Modifier.width(4.dp))
                Text(
                    text = buildString {
                        append("Rp ")
                        append(
                            NumberFormat.getNumberInstance(Locale("in", "ID"))
                                .format(regularPrice)
                        )
                    },
                    color = Color(0xFF757575),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W700,
                    textDecoration = TextDecoration.LineThrough,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
private fun FlashSaleCardPreview() {
    AppTheme {
        ProductCard(
            image = "",
            category = "Starter Kit",
            title = "Basic Starter Kit",
            regularPrice = 50000,
            discountedPrice = 25000,
            onClick = {}
        )
    }
}