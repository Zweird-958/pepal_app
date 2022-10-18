package com.example.pepalapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.pepalapp.pages.*
import com.example.pepalapp.ui.theme.PepalAppTheme
import com.example.pepalapp.uifun.logoutButton


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PepalAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {
                    getActivity(this)
                    getData()
                    ScreenMain()

                }
            }
        }

    }

    override fun onResume() {
        super.onResume()

        println("=========RESUME============")
    }

}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

/*@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    PepalAppTheme {
        Greeting("Android")
    }
}
*/


@Composable
fun SimpleButton(label: String) {
    var textChange by remember {
        mutableStateOf("no click")
    }
    //TextWithSize(textChange,40.sp)
    Button(onClick = {
        textChange = "clicked"
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
fun createText() {

    MaterialTheme {
        Box(modifier = Modifier.fillMaxWidth()) {
            val text = remember { mutableStateOf("Hello world") }
            //createButton("Base text", text)
            Text(text.value)
        }
    }

}

