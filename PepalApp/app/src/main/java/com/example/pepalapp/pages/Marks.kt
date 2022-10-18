package com.example.pepalapp.pages

// TODO Afficher la moyenne (en haut à droite constant)
// TODO Afficher le coefficient et le calculer avec la moyenne

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pepalapp.uifun.*
import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.math.roundToLong

@Composable
fun MarksScaffold(navController: NavHostController){
    Scaffold(
        //topBar = { TopBar(navController) },
        content = {
            Marks()
        },
        bottomBar = { BottomBar(navController) }
    )
}

@Composable
fun Marks(){
    
    TitleText("Marks")



    Column(Modifier.fillMaxWidth().padding(top = 100.dp, end = 20.dp),
    horizontalAlignment = Alignment.End ) {

        Text("Moyenne : ${roundWithTwo(avg)}", fontSize = 17.sp)
    }



    LazyColumn(
        modifier = Modifier
            .padding(top = 150.dp, bottom = 45.dp, start = 20.dp , end = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        dataAllNotes?.let {
            items(it.size){ index ->
                val note = dataAllNotes!![index]
                val info = note.select("td")
                val infoName = "Matière : "+info[0].text()
                val infoDate = "Date : "+info[2].text()
                val infoNote = "Note : "+info[3].text()
                val cardText:List<String> = listOf(infoName,infoDate,infoNote)
                CardWithMultipleViews(cardText)
            }
        }

        dataAllNotes?.let {
            items(it.size){ index ->
                val note = dataAllNotes!![index]
                val info = note.select("td")
                val infoName = "Matière : "+info[0].text()
                val infoDate = "Date : "+info[2].text()
                val infoNote = "Note : "+info[3].text()
                val cardText:List<String> = listOf(infoName,infoDate,infoNote)
                CardWithMultipleViews(cardText)
            }
        }


    }
}