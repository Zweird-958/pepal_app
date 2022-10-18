package com.example.pepalapp.pages


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pepalapp.ui.theme.Blue300
import com.example.pepalapp.uifun.*

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

    //TitleText("Account")

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier
            .background(color = Blue300)
            .fillMaxWidth()
            .padding(top = 45.dp, bottom = 45.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //Spacer(Modifier.height(40.dp))
            RoundCornerImageViewWithUrl(usernameImage,128.dp,2.5.dp)
            Spacer(Modifier.height(15.dp))
            Text(text = name)
            //Spacer(Modifier.height(40.dp))
        }

        Column(
            modifier = Modifier
                //.padding(15.dp)
                .fillMaxSize(),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(text = name)

            TextWithSize(label = "Adresse", size = 15.sp)
            TextWithSize(label = "12 rue fsjfskfjs 95100 Argenteuil", size = 10.sp)
            for (info in userInformations){
                Text(text = info)
            }

            //CardWithImage(label = name, imageUrl = usernameImage)

            Spacer(modifier = Modifier.height(20.dp))

            logoutButton(baseText = "Déconnexion", navController = navController)



        }
    }
}