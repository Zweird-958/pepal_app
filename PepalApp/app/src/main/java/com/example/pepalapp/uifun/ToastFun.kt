package com.example.pepalapp.uifun

import android.widget.Toast
import androidx.compose.runtime.Composable
import com.example.pepalapp.pages.mainActivity


fun MakeToast(label: String){
    Toast.makeText(mainActivity, label, Toast.LENGTH_SHORT).show()
}