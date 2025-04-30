package com.motion.hydropome.ui.personalization.constant

import com.motion.hydropome.R
import com.motion.hydropome.ui.personalization.model.Question

val questions = listOf(
    listOf(
        Question(
            id = "0",
            title = "Apakah kamu pernah mencoba menanam hidroponik sebelumnya?",
            answers = listOf(
                "Belum pernah",
                "Pernah, tapi masih pemula",
                "Sudah cukup berpengalaman"
            ),
            isMultipleAnswer = false
        ),
        Question(
            id = "1",
            title = "Apa tujuan utama kamu menggunakan aplikasi HydropoMe?",
            answers = listOf(
                "Belajar hidroponik dari awal",
                "Merawat tanaman hidroponik yang sudah ada",
                "Membeli peralatan dan perlengkapan hidroponik",
                "Menjual hasil panen"
            ),
            isMultipleAnswer = false
        )
    ),
    listOf(
        Question(
            id = "2",
            title = "Jenis tanaman apa saja yang ingin kamu tanam?",
            answers = listOf(
                "Sayuran daun (misalnya selada, bayam)",
                "Buah (misalnya tomat, stroberi)",
                "Tanaman herbal",
                "Belum tahu / ingin rekomendasi"
            ),
            isMultipleAnswer = true
        ),
        Question(
            id = "3",
            title = "Berapa  waktu yang bisa diluangkan per hari untuk merawat tanaman?",
            answers = listOf(
                "< 10 menit",
                "10–30 menit",
                "> 30 menit"
            ),
            isMultipleAnswer = false
        ),
        Question(
            id = "4",
            title = "Di mana kamu akan menanam tanaman hidroponik?",
            answers = listOf(
                "Dalam ruangan",
                "Luar ruangan",
                "Balkon atau teras"
            ),
            isMultipleAnswer = false
        )
    ),
    listOf(
        Question(
            id = "5",
            title = "Berapa luas area tanam yang kamu miliki?",
            answerImages = listOf(
                R.drawable.answer_image_0,
                R.drawable.answer_image_1,
                R.drawable.answer_image_2
            ),
            answers = listOf(
                "< 1 m²",
                "1–3 m²",
                "> 3 m²"
            ),
            isMultipleAnswer = false
        )
    )
)