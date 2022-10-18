package com.example.pepalapp.pages

// TODO Tester si le cours suivant s'affiche en week-end

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
fun CalendarScaffold(navController: NavHostController){
    Scaffold(
        //topBar = { TopBar(navController) },
        content = {
            Calendar(navController)
        },
        bottomBar = { BottomBar(navController) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(navController: NavHostController){

    TitleText("Calendar")
    
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val dateFormat = SimpleDateFormat("yyyy-M-dd")
        val currentDate = dateFormat.format(Date())
        var found: Boolean = false
        var lastCours = 0
        for (cours in dataCalendar){
            var cardText:List<String> = listOf()

            if (cours["Début"]?.contains("T") == true){
                val coursAndDate = cours["Début"]?.split("T")
                val getCoursDate = coursAndDate?.get(0)
                val getCoursHour = coursAndDate?.get(1)
                if (getCoursDate != null){
                    if (getCoursDate == currentDate){
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
                            found = true
                        }
                        CardWithMultipleViews(cardText)
                    }
                    else if (currentDate < getCoursDate){
                        lastCours++
                    }
                }

            }


        }

        if (!found){
            val cours = dataCalendar[lastCours]
            val coursAndDate = cours["Début"]?.split("T")
            val getCoursDate = coursAndDate?.get(0)
            val getCoursHour = coursAndDate?.get(1)
            var cardText:List<String> = listOf()
            for (info in cours){
                // On ne prend pas l'id ni la date et heure de fin
                if (info.key != "id" && info.key != "Fin"){
                    if (info.key == "Début"){
                        val formatDate = getCoursDate?.split("-")?.reversed()
                        cardText += "Date : " + (formatDate?.get(0)) +"/"+ (formatDate?.get(1)) +"/"+ (formatDate?.get(2))
                        cardText += "Heure : " + getCoursHour
                    }
                    else{
                        cardText += info.key + " : " + info.value
                    }
                }
                found = true
            }
            CardWithMultipleViews(cardText)
        }

        Spacer(modifier = Modifier.height(25.dp))

        calendarButton(baseText = "FULL EDT", navController = navController)

    }
}

/* Tous les cours
for (cours in dataCalendar){
    var cardText:List<String> = listOf()
    for (info in cours){
        if (info.key != "id"){
            cardText += info.key + " : " + info.value
        }
    }
    CardWithMultipleViews(cardText)
}*/