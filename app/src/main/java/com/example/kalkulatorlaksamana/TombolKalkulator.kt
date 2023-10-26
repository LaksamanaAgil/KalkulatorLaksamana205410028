package com.example.kalkulatorlaksamana

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable //TODO: MEMBUAT FUNGSI COMPOSABLE UNTUK MEMBUAT TOMBOL KALKULATOR
fun TombolKalkulator(
    symbol: String, //TODO: ATRIBUT-ATRIBUT UNTUK TOMBOL KALKULATOR
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    textStyle: TextStyle = TextStyle(),
) {
    Box( //TODO: FUNGSI BOX UNTUK MEMBERI BENTUK, WARNA TOMBOL
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color)
            .then(modifier)
    ) {
        Text( //TODO: FUNGSI TEXT UNTUK MENYETTING TEKS/SIMBOL PADA TOMBOL KALKULATOR
            text = symbol,
            style = textStyle,
            fontSize = 25.sp,
            color = Color.White,
        )
    }
}