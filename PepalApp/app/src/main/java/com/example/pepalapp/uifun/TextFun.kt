package com.example.pepalapp.uifun

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(text: String){
    Row(modifier = Modifier
        .padding(top = 10.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top) {

        Text(text = text,fontSize = 45.sp, maxLines = 1, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TextWithSize(label: String, size: TextUnit) {
    Text(label, fontSize = size, modifier = Modifier.fillMaxWidth())
}
