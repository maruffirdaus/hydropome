package com.motion.hydropome.ui.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motion.hydropome.R
import com.motion.hydropome.common.model.User
import com.motion.hydropome.ui.AppDestination
import com.motion.hydropome.ui.common.component.CustomButton
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onUserRefresh: () -> Unit,
    onLogout: (() -> Unit) -> Unit,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        onUserRefresh()
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
            val scrollState = rememberScrollState()

            AnimatedVisibility(
                visible = scrollState.value == 0,
                enter = fadeIn() + expandVertically(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + 152.dp
                        )
                        .clip(BottomArcShape())
                        .background(AppColors.background)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_profile_default),
                        contentDescription = null,
                        modifier = Modifier.size(68.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        uiState.user?.let { user ->
                            Text(
                                text = user.name,
                                color = AppColors.textLight,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W700,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = user.email,
                                color = AppColors.textLight,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                    }
                    Spacer(Modifier.width(20.dp))
                    CustomButton(
                        text = "Edit Profil",
                        onClick = {},
                        width = 80.dp,
                        height = 36.dp,
                        radius = 12.dp,
                        paddingHorizontal = 8.dp,
                        fontSize = 12.sp
                    )
                }
                Spacer(Modifier.height(96.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(16.dp),
                            ambientColor = Color.Black.copy(alpha = 0.4f),
                            spotColor = Color.Black.copy(alpha = 0.4f)
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFFFFFFF))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Status Pesanan Marketplace",
                        color = AppColors.text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {}
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_3d_square),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = AppColors.primary
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "Diproses",
                                color = AppColors.text,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                        Column(
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {}
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_truck_fast),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = AppColors.primary
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "Dikirim",
                                color = AppColors.text,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                        Column(
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {}
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_medal_star),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = AppColors.primary
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "Selesai",
                                color = AppColors.text,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(12.dp),
                            ambientColor = Color.Black.copy(alpha = 0.4f),
                            spotColor = Color.Black.copy(alpha = 0.4f)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFFFFF))
                        .clickable {
                            navController.navigate(AppDestination.Personalization)
                        }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ill_plant_circle),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Personalisasi",
                        color = Color(0xFF000000),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(Modifier.weight(1f))
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_right),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF292D32)
                    )
                }
                Spacer(Modifier.height(16.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(12.dp),
                            ambientColor = Color.Black.copy(alpha = 0.4f),
                            spotColor = Color.Black.copy(alpha = 0.4f)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFFFFF))
                        .clickable {
                            onLogout {
                                navController.navigate(AppDestination.Onboarding) {
                                    popUpTo(0)
                                }
                            }
                        }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ill_logout_circle),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Logout",
                        color = Color(0xFFFC4343),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(Modifier.weight(1f))
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_right),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFFFC4343)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ProfileScreenPreview() {
    AppTheme {
        Surface {
            ProfileScreen(
                uiState = ProfileUiState(
                    User(
                        name = "Mellafesa",
                        email = "mellafesaa@gmail.com"
                    )
                ),
                onUserRefresh = {},
                onLogout = {},
                navController = rememberNavController()
            )
        }
    }
}