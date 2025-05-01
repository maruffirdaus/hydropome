package com.motion.hydropome.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.LaunchedEffect

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motion.hydropome.R
import com.motion.hydropome.ui.common.component.CustomButton
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.profile.component.OrderStatusSection
import com.motion.hydropome.ui.profile.component.ProfileOptionItem
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onGetUserData: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    LaunchedEffect(Unit) {
        onGetUserData()
    }
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
                    .height(statusBarHeight + 200.dp)
                    .clip(BottomArcShape())
                    .background(AppColors.background)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(top = 30.dp, start = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            uiState.userName,
                            fontSize = 27.sp,
                            color = Color.White,
                            fontWeight = FontWeight.W700
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            uiState.userEmail,
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.W400
                        )
                    }

                    CustomButton(
                        text = "Edit Profile",
                        onClick = {},
                        modifier = Modifier.offset(y = 35.dp)                    )
                }
            }

            Text(
                "Status Pesanan Marketplace",
                fontSize = 21.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier
                    .padding(top = 110.dp, start = 40.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                OrderStatusSection()

                Spacer(modifier = Modifier.height(20.dp))

                ProfileOptionItem(
                    icon = painterResource(id = R.drawable.personalisasi),
                    iconBackgroundColor = Color(0xFFE8F5F2),
                    text = "Personalisasi",
                    onClick = { }
                )

                Spacer(modifier = Modifier.height(10.dp))

                ProfileOptionItem(
                    icon = painterResource(id = R.drawable.exit),
                    iconBackgroundColor = Color(0xFFFFF0F0),
                    text = "Logout",
                    textColor = Color.Red,
                    arrowColor = Color.Red,
                    onClick = {  }
                )
            }
        }
    }
}


@Preview
@Composable
fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(
            uiState = ProfileUiState(),
            onGetUserData = {}
        )
    }
}