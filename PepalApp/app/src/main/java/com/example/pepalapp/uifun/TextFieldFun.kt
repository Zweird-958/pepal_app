package com.example.pepalapp.uifun

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier
import com.example.pepalapp.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LabelAndStock(yourLabel: String,option: KeyboardType, icon: ImageVector, description: String,
                  rememberText: MutableState<String>, fillType: List<AutofillType>) {


    val autofillNode = AutofillNode(
        autofillTypes = fillType,
        onFill = { rememberText.value = it }
    )
    val autofill = LocalAutofill.current

    LocalAutofillTree.current += autofillNode

    TextField(
        value = rememberText.value,
        onValueChange = {
            rememberText.value = it
        },
        leadingIcon = { Icon(imageVector = icon, contentDescription = description) },
        label = { Text(text = yourLabel) },
        keyboardOptions = KeyboardOptions(keyboardType = option),

    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LabelAndStockPass(yourLabel: String, icon: ImageVector, description: String,
                  rememberText: MutableState<String>, fillType: List<AutofillType>) {

    val autofillNode = AutofillNode(
        autofillTypes = fillType,
        onFill = { rememberText.value = it }
    )
    val autofill = LocalAutofill.current

    LocalAutofillTree.current += autofillNode

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = rememberText.value,
        onValueChange = {
            rememberText.value = it
        },
        leadingIcon = { Icon(imageVector = icon, contentDescription = description) },
        label = { Text(text = yourLabel) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {

            val image = if (passwordVisible)
                painterResource(id = R.drawable.visibility_on)
            else painterResource(id = R.drawable.visibility_off)
            // Please provide localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(painter  = image, description)
            }
        }

    )
}

@Composable
fun LabelAndPlaceHolder(yourLabel: String, yourPlaceHolder: String, option: KeyboardType, icon: ImageVector, description: String, rememberText: MutableState<String>) {
    TextField(
        value = rememberText.value,
        onValueChange = {
            rememberText.value = it
        },
        leadingIcon = { Icon(imageVector = icon, contentDescription = description) },
        label = { Text(text = yourLabel) },
        placeholder = { Text(text = yourPlaceHolder) },
        keyboardOptions = KeyboardOptions(keyboardType = option),
    )
}