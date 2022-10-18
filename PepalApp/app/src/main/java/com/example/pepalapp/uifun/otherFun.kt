package com.example.pepalapp.uifun

import kotlin.math.roundToInt

fun roundWithTwo(number: Float): Double {
    return (number * 100.0).roundToInt().toDouble()/100
}