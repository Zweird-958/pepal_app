package com.example.pepalapp.uifun

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pepalapp.R
import com.example.pepalapp.ui.theme.Teal200
import java.net.URL


@Composable
fun RoundCornerImageView() {
    Image(painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = "Round corner image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(10))
            .border(5.dp, Teal200, RoundedCornerShape(10))  )
}

@Composable
fun RoundCornerImageViewWithUrl(url: String, imgSize: Dp, borderSize: Dp){

    Image(painter = rememberAsyncImagePainter(url),
        contentDescription = "Round corner image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(imgSize)
            .clip(RoundedCornerShape(100))
            .border(borderSize, Color.White, RoundedCornerShape(100))  )
}