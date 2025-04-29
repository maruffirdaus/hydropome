package com.motion.hydropome.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motion.hydropome.R
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme
import com.motion.hydropome.ui.theme.plusJakartaSansFontFamily

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var isPasswordVisible by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = TextStyle(
            color = AppColors.text,
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            fontFamily = plusJakartaSansFontFamily
        ),
        singleLine = true,
        visualTransformation = if (!isPassword || isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        interactionSource = interactionSource,
        cursorBrush = SolidColor(AppColors.primary),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 1.dp,
                        color = if (isFocused || value.isNotBlank()) {
                            AppColors.primary
                        } else {
                            Color(0xFFE8ECF4)
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(Color(0xFFF7F8F9))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (value.isNotBlank()) {
                        innerTextField()
                    } else {
                        Text(
                            text = placeholder,
                            color = Color(0xFF8391A1),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                    if (isPassword) {
                        Box(
                            modifier = Modifier
                                .absoluteOffset(x = 8.dp)
                                .size(36.dp)
                                .clip(RoundedCornerShape(50))
                                .clickable {
                                    isPasswordVisible = !isPasswordVisible
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = if (isPasswordVisible) {
                                    painterResource(id = R.drawable.ic_eye_slash)
                                } else {
                                    painterResource(id = R.drawable.ic_eye)
                                },
                                contentDescription = null,
                                tint = Color(0xFF8391A1)
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    AppTheme {
        CustomTextField(
            value = "Nama",
            placeholder = "Masukkan Nama",
            onValueChange = {}
        )
    }
}

@Preview
@Composable
private fun CustomPasswordTextFieldPreview() {
    AppTheme {
        CustomTextField(
            value = "",
            placeholder = "Masukkan Password",
            onValueChange = {},
            isPassword = true
        )
    }
}