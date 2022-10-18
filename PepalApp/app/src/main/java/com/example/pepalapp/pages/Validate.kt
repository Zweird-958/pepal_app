package com.example.pepalapp.pages

// TODO A Tester

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pepalapp.uifun.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ValidateScaffold(navController: NavHostController){
    Scaffold(
        //topBar = { TopBar(navController) },
        content = {
            Validate(navController)
        },
        bottomBar = { BottomBar(navController) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Validate(navController: NavHostController){

    TitleText("Présence")
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        println("=============")
        println(actualID)

        if (actualID["id"]!=null){
            var cardText = listOf<String>()
            for (info in actualID){
                if (info.key == "url" || info.key == "id"){
                    continue
                }
                cardText += "${info.key} : ${info.value}"     
            }
            CardWithMultipleViews(cardText = cardText)
            
        }
        else{
            Text(text = "Pas de cours actuellement")
        }

        Text(text = resultValidation)

        if (resultValidation == "Appel Ouvert"){
            Spacer(modifier = Modifier.height(25.dp))
            validateButton(baseText = "Valider la présence", navController = navController)
        }
        
        Spacer(modifier = Modifier.height(25.dp))

        refreshButton(baseText = "RAFRAICHIR", navController = navController)

    }
}
