package com.example.pepalapp.pages

// TODO Laisser le titre au dessus

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ListItem
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
import androidx.compose.ui.graphics.Color
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
fun FullCalendarScaffold(navController: NavHostController){
    Scaffold(
        topBar = { TopBar(navController, "Calendar") },
        content = {
            FullCalendar()
        },
        bottomBar = { BottomBar(navController) }
    )
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FullCalendar(){

    val showEntreprise = remember { mutableStateOf(true) }
    Column(modifier = Modifier
        .fillMaxSize()) {

        //TitleText("Calendar")

        Row(modifier = Modifier
            .padding(start = 20.dp, top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,) {

            Text(text = "Entreprise :")
            CheckBoxOnOff(stockVal = showEntreprise)

        }



        LazyColumn(
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 40.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val currentDate = dateFormat.format(Date())


            items(courseList.size) { index ->
                val cours = courseList[index]

                var cardText:List<String> = listOf()

                if (!cours.heureDebut.isNullOrEmpty() && !cours.dateDebut.isNullOrEmpty()){

                        // Pour les cours pas passé ou celui actuel
                        if (cours.dateDebut >= currentDate){
                            cardText += "Matière : ${cours.matiere}"
                            cardText += "Intervenant : ${cours.intervenant}"
                            cardText += "Salle : ${cours.salle}"
                            val formatDate = cours.dateDebut.split("-").reversed()
                            cardText += "Date : ${formatDate[0]}/${formatDate[1]}/${formatDate[2]}"
                            cardText += "Heure : ${cours.heureDebut}"

                            if (cours.matiere == "Entreprise"){
                                if (showEntreprise.value){
                                    CardWithMultipleViews(cardText)
                                }
                            }
                            else{
                                CardWithMultipleViews(cardText)
                            }

                        }

                }

            }

        }
    }
}