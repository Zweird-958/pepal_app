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
import classes.courseList
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

    TitleText("Emploi du Temps")
    
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = dateFormat.format(Date())
        var found: Boolean = false
        var lastCours = 0

            for (cours in courseList){
                var cardText:List<String> = listOf()

                if (!cours.heureDebut.isNullOrEmpty() && !cours.dateDebut.isNullOrEmpty()){

                        if (cours.dateDebut == currentDate){

                            /*println("PROPRIETE============")
                            for (property in cours::class.members) {
                                println("${property.name}: ${property.call(cours)}")
                            }*/
                            cardText += "Matière : ${cours.matiere}"
                            cardText += "Intervenant : ${cours.intervenant}"
                            cardText += "Salle : ${cours.salle}"
                            val formatDate = cours.dateDebut.split("-").reversed()
                            cardText += "Date : ${formatDate[0]}/${formatDate[1]}/${formatDate[2]}"
                            cardText += "Heure : ${cours.heureDebut}"
                            found = true
                            CardWithMultipleViews(cardText)

                        }
                        else if (currentDate < cours.dateDebut) {
                            lastCours++
                        }

                }
            }

        if (!found){
            val cours = courseList[lastCours]

            var cardText:List<String> = listOf()
            cardText += "Matière : ${cours.matiere}"
            cardText += "Intervenant : ${cours.intervenant}"
            cardText += "Salle : ${cours.salle}"
            if (!cours.dateDebut.isNullOrEmpty()){
                val formatDate = cours.dateDebut.split("-").reversed()
                cardText += "Date : ${formatDate[0]}/${formatDate[1]}/${formatDate[2]}"
            }
            else{
                cardText += "Date : X"
            }
            cardText += "Heure : ${cours.heureDebut}"

            CardWithMultipleViews(cardText)
        }

        Spacer(modifier = Modifier.height(25.dp))

        calendarButton(baseText = "VOIR PLUS", navController = navController)

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