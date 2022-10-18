package com.example.pepalapp.pages

// TODO Auto Fill
// TODO Save Login

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pepalapp.ui.theme.Blue300
import com.example.pepalapp.uifun.*
import java.util.*


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Login(navController: NavHostController){
    val username = remember { mutableStateOf("") } // stock text
    val password = remember { mutableStateOf("") }
    val cookieLabel = remember { mutableStateOf("") }
    val listStock: List<MutableState<String>> = listOf(username,password,cookieLabel)
    val focusManager = LocalFocusManager.current



    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText("Connexion")

        Spacer(modifier = Modifier.height(25.dp))

        LabelAndStock(yourLabel = "Identifiant",option = KeyboardType.Text, icon = Icons.Default.AccountBox,
            description = "usernameicon",username, listOf(AutofillType.Username))

        Spacer(modifier = Modifier.height(15.dp))

        LabelAndStockPass(yourLabel = "Mot de Passe", icon = Icons.Default.Lock,
            description = "passicon",password, listOf(AutofillType.Password))

        Spacer(modifier = Modifier.height(25.dp))

        loginButton("Connexion", listStock, navController)
    }
}
