package com.example.pepalapp.uifun
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pepalapp.pages.*
import com.example.pepalapp.ui.theme.Green
import org.jsoup.select.Elements


@Composable
fun loginButton(baseText: String, listStock: List<MutableState<String>>, navController: NavHostController) {
    Button(
        //modifier = Modifier.width(15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        onClick = {
            val username = listStock[0].value
            val password = listStock[1].value

            if (username.isBlank() || password.isBlank()){
                MakeToast("Entrez un identifiant et un mot de passe !")
                //buttonChangeText.value = "Enter an username and a password !"

            }
            else {
                dataUsername = username
                dataPassword = password

                connection(navController)
                Thread.sleep(100)
                if (cookie.isNotBlank()){
                    saveData()
                    navController.navigate(Routes.Account.route)
                }

            }
        },
        // Uses ButtonDefaults.ContentPadding by default
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        Text(baseText)
    }
}

@Composable
fun calendarButton(baseText: String, navController: NavHostController) {
    Button(
        //modifier = Modifier.width(15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        onClick = {
            navController.navigate(Routes.FullCalendar.route)
        },
        // Uses ButtonDefaults.ContentPadding by default
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        Text(baseText)
    }
}

@Composable
fun refreshButton(baseText: String, navController: NavHostController) {
    Button(
        //modifier = Modifier.width(15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        onClick = {
            validatePresence()
            Thread.sleep(1000)
            if (actualID["id"] != null){
                navController.navigate(Routes.Validate.route)
            }
        },
        // Uses ButtonDefaults.ContentPadding by default
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        Text(baseText)
    }
}

@Composable
fun validateButton(baseText: String, navController: NavHostController) {
    Button(
        //modifier = Modifier.width(15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Green),
        onClick = {
            activatePresence()
            Thread.sleep(1000)
            if (actualID["id"] != null){
                navController.navigate(Routes.Validate.route)
            }
        },
        // Uses ButtonDefaults.ContentPadding by default
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        Text(baseText)
    }
}

@Composable
fun logoutButton(baseText: String, navController: NavHostController) {
    Button(
        //modifier = Modifier.width(15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        onClick = {
            navController.navigate(Routes.Login.route)
            dataUsername = ""
            dataPassword = ""
            avg = 0F
            name = ""
            cookie = ""
            usernameImage = ""
            dataAllNotes = null
            dataId = mutableListOf()
            resultValidation = ""

            dataCalendar = mutableListOf()
            dataWorks = mutableListOf()
            actualID = mapOf()

            saveData()



        },
        // Uses ButtonDefaults.ContentPadding by default
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        Text(baseText, color = Color.White)
    }
}