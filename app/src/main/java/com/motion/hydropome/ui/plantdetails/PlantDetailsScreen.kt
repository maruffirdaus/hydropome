package com.motion.hydropome.ui.plantdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.motion.hydropome.R
import com.motion.hydropome.common.model.Plant
import com.motion.hydropome.common.type.Difficulty
import com.motion.hydropome.ui.AppDestination
import com.motion.hydropome.ui.common.component.BackButton
import com.motion.hydropome.ui.common.component.CustomButton
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun PlantDetailsScreen(
    plantId: String,
    uiState: PlantDetailsUiState,
    videoPlayer: @Composable (String) -> Unit,
    onPlantRefresh: (String) -> Unit,
    onStartPlant: (() -> Unit) -> Unit,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        onPlantRefresh(plantId)
    }

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
            uiState.plant?.let { plant ->
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val scrollState = rememberScrollState()

                    AnimatedVisibility(
                        visible = scrollState.value == 0,
                        enter = fadeIn() + expandVertically(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        AsyncImage(
                            model = plant.image,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(innerPadding.calculateTopPadding() + 256.dp)
                                .clip(BottomArcShape()),
                            placeholder = painterResource(R.drawable.img_onboarding_1),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .padding(innerPadding)
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        Box {
                            BackButton(
                                onClick = {
                                    navController.popBackStack(
                                        AppDestination.PlantDetails(plantId),
                                        true
                                    )
                                }
                            )
                            Spacer(Modifier.height(256.dp))
                        }
                        Spacer(Modifier.height(20.dp))
                        Text(
                            text = plant.title,
                            color = AppColors.text,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W700
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val difficultyColor = when (plant.difficulty) {
                                Difficulty.EASY -> AppColors.difficultyEasy
                                Difficulty.MEDIUM -> AppColors.difficultyMedium
                                Difficulty.HARD -> AppColors.difficultyHard
                            }

                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(difficultyColor)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = plant.difficulty.label,
                                color = difficultyColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400
                            )
                            Spacer(Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(R.drawable.ic_clock),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = Color(0xFF98A0AA)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = "${plant.duration} Ming",
                                color = Color(0xFF98A0AA),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400
                            )
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
                            text = plant.description,
                            color = Color(0xFF757575),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Justify
                        )
                        Spacer(Modifier.height(24.dp))
                        Text(
                            text = "Alat dan Bahan",
                            color = Color(0xFF060707),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W700
                        )
                        Spacer(Modifier.height(8.dp))

                        val annotatedString = buildAnnotatedString {
                            plant.toolsAndMaterials.forEachIndexed { index, content ->
                                append("${index + 1}. ")
                                append(content.title)
                                content.description?.let { description ->
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color(0xFF757575),
                                            fontWeight = FontWeight.W400
                                        )
                                    ) {
                                        append(" - $description")
                                    }
                                }
                                append("\n")
                            }
                        }

                        Text(
                            text = annotatedString,
                            color = AppColors.text,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W700,
                            textAlign = TextAlign.Justify
                        )
                        Spacer(Modifier.height(24.dp))
                        Text(
                            text = "Tutorial Menanam",
                            color = Color(0xFF060707),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W700
                        )
                        Spacer(Modifier.height(8.dp))
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
                                .padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(16f / 9f)
                                    .clip(RoundedCornerShape(10.dp))
                            ) {
                                videoPlayer(plant.videoTutorial)
                            }
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Menanam ${plant.title}",
                                color = AppColors.text,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W700
                            )
                        }
                        Spacer(Modifier.height(48.dp))
                        CustomButton(
                            text = "Mulai Tanam dan Pantau",
                            onClick = {
                                onStartPlant {
                                    navController.popBackStack(
                                        AppDestination.PlantDetails(plantId),
                                        true
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Preview(device = "spec:width=1080px,height=3060px,dpi=440")
@Composable
fun PlantDetailsScreenPreview() {
    AppTheme {
        PlantDetailsScreen(
            plantId = "",
            uiState = PlantDetailsUiState(
                plant = Plant(
                    title = "Selada Hidroponik",
                    difficulty = Difficulty.EASY,
                    duration = "3-5",
                    description = "Selada merupakan salah satu tanaman paling populer dalam budidaya hidroponik karena pertumbuhannya yang cepat dan perawatannya yang mudah. Tanaman ini cocok untuk pemula karena tidak memerlukan banyak nutrisi khusus atau perawatan intensif. Selada tumbuh subur di sistem hidroponik seperti NFT (Nutrient Film Technique) dan dapat dipanen dalam waktu 3–5 minggu setelah tanam",
                    toolsAndMaterials = listOf(
                        Plant.ToolMaterial(
                            title = "Wadah atau Bak Tanam",
                            description = "Tempat air nutrisi dan tanaman diletakkan."
                        ),
                        Plant.ToolMaterial(
                            title = "Net Pot / Pot kecil berlubang",
                            description = "Untuk menahan tanaman dan media tanam."
                        ),
                        Plant.ToolMaterial(
                            title = "Spons/Rockwool",
                            description = "Media tanam tempat benih tumbuh."
                        ),
                        Plant.ToolMaterial(
                            title = "Benih selada"
                        ),
                        Plant.ToolMaterial(
                            title = "Air Bersih"
                        )
                    )
                ),
            ),
            videoPlayer = {},
            onPlantRefresh = {},
            onStartPlant = {},
            navController = rememberNavController()
        )
    }
}