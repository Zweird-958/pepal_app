package com.example.pepalapp.pages

// TODO Afficher la moyenne (en haut à droite constant)
// TODO Afficher le coefficient et le calculer avec la moyenne

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pepalapp.*
import com.example.pepalapp.uifun.*
import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.math.roundToLong

@RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Marks(){
    
    TitleText("Notes")

    val marksLists: List<List<MarkClass>> = listOf(sortByMatiere(), sortByDate(), sortByDate().reversed(), sortByNote(), sortByNote().reversed())
    val sortMarks = remember { mutableStateOf(0) }



    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, end = 20.dp),
    horizontalAlignment = Alignment.End ) {

        Text("Moyenne : ${roundWithTwo(avg)}", fontSize = 17.sp)

    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 88.dp, start = 20.dp),
        horizontalAlignment = Alignment.Start ) {

        ButtonSort(stockVal = sortMarks, limit = marksLists.size-1, 17.sp)

    }



    LazyColumn(
        modifier = Modifier
            .padding(top = 150.dp, bottom = 45.dp, start = 20.dp, end = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        //allMarksClass.let {
            items(marksLists[sortMarks.value].size) { index ->
                val mark = marksLists[sortMarks.value][index]
                val infoMatiere = "Matière : " + mark.matiere
                val infoName = "Nom : " + mark.name
                val infoDate = "Date : " + mark.date
                val infoNote = "Note : " + mark.note
                val infoCoef = "Coéfficient : " + mark.coef
                val infoAppr = "Appréciation : " + if (mark.appreciation != "") {
                    mark.appreciation
                }  else {
                    "X"
                }
                val cardText: List<String> = listOf(infoMatiere, infoName, infoDate, infoNote, infoCoef, infoAppr)
                CardWithMultipleViews(cardText)
            }
        //}


    }
}