package com.example.pepalapp.uifun

import android.text.Layout
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.pepalapp.pages.Routes
import com.example.pepalapp.ui.theme.Purple200
import com.example.pepalapp.ui.theme.Teal200

import com.example.pepalapp.R
import com.example.pepalapp.ui.theme.customColor
import androidx.compose.ui.Modifier




@Composable
fun TopBar(navController: NavHostController, label:String) {
    TopAppBar(
            title = {
                Text(text = label)
            },
            navigationIcon = {
                IconButton(onClick = {navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            },
            backgroundColor = colors.background,
            /*contentColor = Teal200,*/
            //elevation = 5.dp,
            //modifier = Modifier.padding(30.dp)
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val currentRoute = navController.currentDestination?.route
    BottomAppBar () {

        BottomNavigationItem(icon = {Column(
                verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Default.AccountBox,"")
        }
        },
            //label = { /*Text(text = "Account") */},
            selected = (currentRoute == Routes.Account.route),
            onClick = {
                customColor = true
                navController.navigate(Routes.Account.route)
            })

        BottomNavigationItem(icon = {
            Icon(painter = painterResource(id = R.drawable.school_24),"")
        },
            //label = {/* Text(text = "Marks") */},
            selected = (currentRoute == Routes.Marks.route),
            onClick = {
                customColor = false
                navController.navigate(Routes.Marks.route)

            }
        )

        BottomNavigationItem(icon = {
            Icon(painter = painterResource(id = R.drawable.calendar),"")
        },
            //label = {/* Text(text = "EDT") */},
            selected = (currentRoute == Routes.Calendar.route || currentRoute == Routes.FullCalendar.route),
            onClick = {
                customColor = false
                navController.navigate(Routes.Calendar.route)
            }
        )

        BottomNavigationItem(icon = {
            Icon(painter = painterResource(id = R.drawable.work_24),"")
        },
            //label = { Text(text = "") },
            selected = (currentRoute == Routes.Works.route),
            onClick = {
                customColor = false
                navController.navigate(Routes.Works.route)
            }
        )

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Check,"")
        },
            //label = { /*Text(text = "Check")*/ },
            selected = (currentRoute == Routes.Validate.route),
            onClick = {
                customColor = false
                navController.navigate(Routes.Validate.route)
            }
        )


    }
}

