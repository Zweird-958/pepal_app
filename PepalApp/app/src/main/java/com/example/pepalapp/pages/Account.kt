package com.example.pepalapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pepalapp.ui.theme.Blue300
import com.example.pepalapp.uifun.*

val ITEMS_ACCOUNT= mapOf<String,Int>(
    "Adresse" to 0,
    "Mobile" to 2,
    "E-mail personnelle" to 1,
    "E-mail scolaire" to 3,
)

@Composable
fun AccountScaffold(navController: NavHostController){
    Scaffold(
        //topBar = { TopBar(navController) },
        content = {
            Account(navController)
        },
        bottomBar = { BottomBar(navController) }
    )
}

@Composable
fun Account(navController: NavHostController){

    Column(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier
            .background(color = Blue300)
            .fillMaxWidth()
            .padding(top = 45.dp, bottom = 45.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //Spacer(Modifier.height(40.dp))
            RoundCornerImageViewWithUrl(usernameImage,128.dp,2.5.dp)
            Spacer(Modifier.height(15.dp))
            Text(text = name, color = Color.White, fontWeight = FontWeight.Bold)
            //Spacer(Modifier.height(40.dp))
        }

        Column(
            modifier = Modifier
                .padding(15.dp)
                .padding(top = 20.dp)
                .fillMaxSize(),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            for (item in ITEMS_ACCOUNT){
                TextWithSize(label = item.key, size = 20.sp)
                TextWithSize(label = userInformations[item.value], size = 15.sp)
                Spacer(modifier = Modifier.height(10.dp))
            }

            /*TextWithSize(label = "Adresse", size = 20.sp)
            //Divider(modifier = Modifier.padding(end = 290.dp), color = Blue300, thickness = 2.dp)
            TextWithSize(label = userInformations[0], size = 15.sp)

            Spacer(modifier = Modifier.height(10.dp))

            TextWithSize(label = "Mobile", size = 20.sp)
            //Divider(modifier = Modifier.padding(end = 300.dp), color = Blue300, thickness = 2.dp)
            TextWithSize(label = userInformations[2], size = 15.sp)

            Spacer(modifier = Modifier.height(10.dp))

            TextWithSize(label = "E-mail personnelle", size = 20.sp)
            //Divider(modifier = Modifier.padding(end = 200.dp), color = Blue300, thickness = 2.dp)
            TextWithSize(label = userInformations[1], size = 15.sp)

            Spacer(modifier = Modifier.height(10.dp))

            TextWithSize(label = "E-mail scolaire", size = 20.sp)
            //Divider(modifier = Modifier.padding(end = 230.dp), color = Blue300, thickness = 2.dp)
            TextWithSize(label = userInformations[3], size = 15.sp)*/

            Spacer(modifier = Modifier.height(30.dp))

            logoutButton(baseText = "Déconnexion", navController = navController)



        }
    }
}