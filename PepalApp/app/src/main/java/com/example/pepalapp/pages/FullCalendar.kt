package com.example.pepalapp.pages

// TODO Laisser le titre au dessus

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun FullCalendarScaffold(navController: NavHostController){
    Scaffold(
        topBar = { TopBar(navController) },
        content = {
            FullCalendar()
        },
        bottomBar = { BottomBar(navController) }
    )
}

var test: MutableList<Unit> = mutableListOf()

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FullCalendar(){

    test = mutableListOf()

    Column(modifier = Modifier
        .fillMaxSize()) {

        TitleText("Calendar")

        LazyColumn(
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp , end = 20.dp, bottom = 40.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val dateFormat = SimpleDateFormat("yyyy-M-dd")
            val currentDate = dateFormat.format(Date())

            items(dataCalendar.size) { index ->
                val cours = dataCalendar[index]
                var cardText:List<String> = listOf()

                if (cours["Début"]?.contains("T") == true){

                    val coursAndDate = cours["Début"]?.split("T")
                    val getCoursDate = coursAndDate?.get(0)
                    val getCoursHour = coursAndDate?.get(1)

                    if (getCoursDate != null){
                        // Pour les cours pas passé ou celui actuel
                        if (getCoursDate >= currentDate){
                            for (info in cours){
                                // On ne prend pas l'id ni la date et heure de fin
                                if (info.key != "id" && info.key != "Fin"){
                                    if (info.key == "Début"){
                                        val formatDate = getCoursDate.split("-").reversed()
                                        cardText += "Date : " + formatDate[0]+"/"+formatDate[1]+"/"+formatDate[2]
                                        cardText += "Heure : " + getCoursHour
                                    }
                                    else{
                                        cardText += info.key + " : " + info.value
                                    }
                                }
                            }
                            test += CardWithMultipleViews(cardText)
                        }
                    }
                }

            }

            println("================")
            println(test.size)

        }
    }
}