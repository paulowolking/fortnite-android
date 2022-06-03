package com.wolking.fortnite.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.wolking.fortnite.R

private val Burbank = FontFamily(
    Font(R.font.burbank_big_condensed_black),
    Font(R.font.burbank_big_condensed_black, FontWeight.W500),
    Font(R.font.burbank_big_condensed_black, FontWeight.W600)
)

val FortnaticosTypography = Typography(
    h4 = TextStyle(
        fontFamily = Burbank,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = Burbank,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = Burbank,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Burbank,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Burbank,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Burbank,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Burbank,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = Burbank,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = Burbank,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = Burbank,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)