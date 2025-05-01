package com.motion.hydropome.ui.jualBarang.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.motion.hydropome.R


@Composable
fun SellProductForm(
    imageUrl: String? = null,
    onImageClick: () -> Unit = {},
    productName: String,
    onProductNameChange: (String) -> Unit,
    category: String,
    onCategoryChange: (String) -> Unit,
    price: String,
    onPriceChange: (String) -> Unit,
    contact: String,
    onContactChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Gambar", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF6F6F6))
                .clickable { onImageClick() },
            contentAlignment = Alignment.Center
        ) {
            if (imageUrl == null) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.addimage),
                        contentDescription = "Placeholder Image",
                        modifier = Modifier.size(64.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "+Tambah Foto",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "Uploaded Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        LabelWithTextField(
            label = "Nama Barang",
            value = productName,
            onValueChange = onProductNameChange,
            placeholder = "Masukkan Nama Barang"
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabelWithTextField(
            label = "Kategori",
            value = category,
            onValueChange = onCategoryChange,
            placeholder = "Kategori Barang"
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabelWithTextField(
            label = "Harga",
            value = price,
            onValueChange = onPriceChange,
            placeholder = "Masukkan Harga",
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabelWithTextField(
            label = "Kontak Penjual",
            value = contact,
            onValueChange = onContactChange,
            placeholder = "Masukkan Kontak Penjual",
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabelWithTextField(
            label = "Deskripsi",
            value = description,
            onValueChange = onDescriptionChange,
            placeholder = "Masukkan Deskripsi",
            singleLine = false,
            minLines = 5
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelWithTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    singleLine: Boolean = true,
    minLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF6F6F6), RoundedCornerShape(12.dp)),
            singleLine = singleLine,
            minLines = minLines,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}
