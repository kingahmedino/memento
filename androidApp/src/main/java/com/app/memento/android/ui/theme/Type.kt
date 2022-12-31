package com.app.memento.android.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.memento.android.R

val CircularStd = FontFamily(
    Font(R.font.circularstd_regular),
    Font(R.font.circularstd_medium, FontWeight(500)),
    Font(R.font.circularstd_bold, FontWeight.Bold)
)

val typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    //buttons, page top right title
    bodyLarge = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    //subtitle texts
    bodyMedium = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    //captions like: "Personal Information"
    bodySmall = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)