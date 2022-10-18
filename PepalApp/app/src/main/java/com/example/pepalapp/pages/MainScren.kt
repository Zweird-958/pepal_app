package com.example.pepalapp.pages

import android.os.Build
import android.provider.ContactsContract
import android.util.Log.d
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pepalapp.uifun.MakeToast

// TODO afficher uniquement quand c'est une page différente
// TODO En dernier faire une page ou il y a toutes les comptes d'enregistrés

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenMain() {
    val navController = rememberNavController()
    //var currentRoute: Routes? = null
    var start: String? = null

    if (dataUsername != "" && dataPassword != "") {
        connection(navController)
        if (cookie.isNotBlank()){
            start = Routes.Account.route
        }
        else{
            start = Routes.Login.route
        }
    } else {
        start = Routes.Login.route
    }

    NavHost(navController = navController, startDestination = start) {


        composable(Routes.Login.route) {
            //if (currentRoute != Routes.Login.route){
            Login(navController)
            BackHandler(true) {} // Bloquer le retour en arrière
            //}
        }
        composable(Routes.Marks.route) {
            //if (currentRoute != Routes.Marks){
            MarksScaffold(navController)
            BackHandler(true) {} // Bloquer le retour en arrière

            /*   currentRoute = Routes.Marks
            }*/
        }
        composable(Routes.Account.route) {
            //if (currentRoute != Routes.Account.route){
            AccountScaffold(navController)
            BackHandler(true) {} // Bloquer le retour en arrière
            //}
        }
        composable(Routes.Calendar.route) {
            //if (currentRoute != Routes.Calendar.route){
            CalendarScaffold(navController)
            BackHandler(true) {} // Bloquer le retour en arrière
            /*    currentRoute = navController.currentDestination?.route as Nothing?
            }*/
        }

        composable(Routes.FullCalendar.route) {
            //if (currentRoute != Routes.FullCalendar.route){
            FullCalendarScaffold(navController)
            //}
        }

        composable(Routes.Works.route) {
            //if (currentRoute != Routes.Works.route){
            WorksScaffold(navController)
            BackHandler(true) {} // Bloquer le retour en arrière
            //}
        }

        composable(Routes.Validate.route) {
            //if (currentRoute != Routes.Validate.route){
            ValidateScaffold(navController)
            BackHandler(true) {} // Bloquer le retour en arrière
            //}
        }


    }
}
