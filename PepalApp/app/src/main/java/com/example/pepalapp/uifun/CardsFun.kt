package com.example.pepalapp.uifun

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pepalapp.ui.theme.Orange

@Composable
fun CardWithMultipleViewsTest(cardText: List<String>) {
    val paddingModifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp  )
    Card(
        elevation = 10.dp,
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(10.dp, Orange),
    ) {
        Column(modifier = paddingModifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            cardText.forEach{
                Text(text = it, fontSize = 15.sp, maxLines = 2)
            }
        }
    }
    Spacer(modifier = Modifier.height(25.dp))
}

@Composable
fun CardWithMultipleViews(cardText: List<String>) {
    val paddingModifier = Modifier.padding(10.dp)
    Card(
        elevation = 10.dp,
        border = BorderStroke(1.dp, Orange),

        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = paddingModifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            cardText.forEach{
                Text(text = it, fontSize = 15.sp, maxLines = 2)
            }
        }
    }
    Spacer(modifier = Modifier.height(25.dp))
}

@Composable
fun Card(cardText: String) {
    val paddingModifier = Modifier.padding(start = 100.dp, end = 100.dp, top = 15.dp, bottom = 15.dp  )
    Card(
        elevation = 10.dp,
        modifier = paddingModifier,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            TextWithSize(cardText,30.sp)
        }
    }
}

@Composable
fun CardWithImage(label: String, imageUrl: String) {
    val paddingModifier = Modifier.padding(start = 40.dp, end = 30.dp, top = 15.dp, bottom = 15.dp  )
    Card(
        elevation = 10.dp,
        modifier = paddingModifier
    ) {
        Row(modifier = paddingModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {

            RoundCornerImageViewWithUrl(imageUrl,64.dp,2.5.dp)
            //Spacer(modifier = Modifier.width(10.dp))
            Text(text = label, fontSize = 20.sp, maxLines = 1)

        }
    }
}