package com.example.pepalapp.uifun

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(text: String){
    Row(modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top) {

        Text(text = text,fontSize = 50.sp)
    }
}

@Composable
fun TextWithSize(label: String, size: TextUnit) {
    Text(label, fontSize = size)
}
