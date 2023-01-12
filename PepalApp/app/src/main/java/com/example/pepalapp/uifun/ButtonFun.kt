package com.example.pepalapp.uifun
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pepalapp.pages.*
import com.example.pepalapp.ui.theme.Green
import org.jsoup.internal.StringUtil.padding
import org.jsoup.select.Elements
import java.lang.reflect.Modifier


@RequiresApi(Build.VERSION_CODES.O)
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
                MakeToast(resultValidation)
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

@Composable
fun SimpleButton(label: String, stockVal: MutableState<Boolean>) {
    var textChange by remember {
        mutableStateOf("no click")
    }
    //TextWithSize(textChange,40.sp)
    Button(onClick = {
        textChange = "clicked"
        stockVal.value = !stockVal.value
    },
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ){
        Text(text = label)
    }
}

@Composable
fun CheckBoxOnOff(stockVal: MutableState<Boolean>) {
    val checkedState = remember { mutableStateOf(true) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = {
            checkedState.value = it
            stockVal.value = !stockVal.value
                          },
        colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
    )
}

@Composable
fun ButtonSort(stockVal: MutableState<Int>, limit: Int, fontSize: TextUnit) {
    val issues: List<String> = listOf("Matière", "Date ⬇", "Date ⬆", "Note ⬇", "Note ⬆")

    Button(onClick = {
        if (stockVal.value < limit){
            stockVal.value++
        }
        else{
            stockVal.value = 0
        }
    },
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ){
        Text(text = "⬇⬆ : ${issues[stockVal.value]}", fontSize = fontSize)
    }
}