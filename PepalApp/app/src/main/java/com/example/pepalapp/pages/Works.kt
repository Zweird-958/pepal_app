package com.example.pepalapp.pages

// TODO Régler le problème des accents avec le replace

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
import classes.courseList
import classes.rendersList
import com.example.pepalapp.uifun.*
import utils.removeOneDay
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorksScaffold(navController: NavHostController){
    Scaffold(
        //topBar = { TopBar(navController) },
        content = {
            Works()
        },
        bottomBar = { BottomBar(navController) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Works(){

    TitleText("Rendus")

    LazyColumn(
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 40.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = dateFormat.format(Date())


        items(rendersList.size) { index ->
            val render = rendersList[index]

            var cardText:List<String> = listOf()

            if (!render.heureDebut.isNullOrEmpty() && !render.dateDebut.isNullOrEmpty()){

                // Pour les cours pas passé ou celui actuel
                //if (render.dateDebut >= currentDate){
                    cardText += "Matière : ${render.matiere}"
                    if (!render.dateFin.isNullOrEmpty()){
                        val formatDate = removeOneDay(render.dateFin)
                        cardText += "Date : ${formatDate?.get(0)}/${formatDate?.get(1)}/${formatDate?.get(2)}"
                    }
                    cardText += "Heure : 23:59"


                        CardWithMultipleViews(cardText)


               // }

            }

        }

    }



}
