package com.example.pepalapp.pages

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.navigation.NavHostController
import classes.Matter
import classes.mattersList
import com.example.pepalapp.MarkClass
import com.example.pepalapp.allMarksClass
import com.example.pepalapp.sortByDate
import com.example.pepalapp.uifun.MakeToast
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.text.SimpleDateFormat
import java.util.*

// Data
var mainActivity: Activity? = null
var dataMap: MutableList<MutableMap<String, String>> = mutableListOf()

// Users informations
var dataUsername = ""
var dataPassword = ""
var avg: Float = 0F
var name = ""
var cookie = ""
var usernameImage = ""
var dataAllNotes: Elements? = null
var dataId: MutableList<String> = mutableListOf()
var resultValidation = ""

// Tests
var userInformations = listOf<String>()

// List de Maps
var dataCalendar: MutableList<MutableMap<String, String>> = mutableListOf()
var dataWorks: MutableList<MutableMap<String, String>> = mutableListOf()
var actualID: Map<String,String> = mapOf()

// URLS
val url = "https://www.pepal.eu/"
val urlLogin = "https://www.pepal.eu/include/php/ident.php"
val urlValidate = "https://www.pepal.eu/student/upload.php"


fun test(){
    Thread {
        val document = Jsoup
            .connect(url)
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
            .cookie("sdv","fokm56vjk6evc68t3tdik18spk")
            .get()

        val user = document.select(".username")
        val classes: Elements = document.select(".username")
        name = classes.text()

        dataPassword = name
    }.start()

}

fun getUserImage(){
    Thread {

        val document = Jsoup
            .connect(url)
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
            .cookie("sdv",cookie)
            .get()

        val imageUrl: String = document.select("img")[1].attr("src")
        usernameImage = imageUrl
    }.start()

}

fun loginVerification() {
    Thread {
        //resetInfo()
        // Date permet d'envoyer les données dans un form
        val loginForm = Jsoup.connect(urlLogin)
            .data("login", dataUsername)
            .data("pass", dataPassword)
            .post()

        if (loginForm.text().contains("Accès valide !") ){
            cookie = loginForm.connection().cookieStore().cookies[0].value

            val document = Jsoup
                .connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
                .cookie("sdv",cookie)
                .get()

            val user: Elements = document.select(".username")
            if (user.text().isNotBlank()){
                name = user.text()
            }

        }





    }.start()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getMarks(){
    Thread {
        val document = Jsoup
            .connect(url+"?my=notes")
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
            .cookie("sdv",cookie)
            .get()


        val marks = document.select("tbody").select("tr")
        marks.forEachIndexed { index, mark ->

            // Si c'est une matière et qu'on peut encore continuer on regarde si il y a des notes
            if (index < marks.size -1 && mark.className() == "info"){
                // Si il y a une note ou non
                if (marks.get(index+1).className() == "note_devoir"){
                    // Valeur pour savoir si on a besoin de créer une nouvelle Note ou pas
                    var Mark: MarkClass? = null

                    // On parcourt donc toutes les notes qui suivent la matière
                    for (i in index+1 until marks.size - 1) {
                        val getMark = marks.get(i) // Note actuell

                        // Si il s'agit d'une nouvelle note on ajoute ses infos
                        if (getMark.className() == "note_devoir" ){
                            Mark = MarkClass(mark.child(0).text())
                            Mark.addCoef(mark.child(1).text().toFloat())
                            Mark.addDate(getMark.child(2).text())
                            Mark.addNote(getMark.child(3).text())
                            Mark.addName(getMark.child(0).text())
                            Mark.addToList()
                        }

                        // Ligne de l'appréciation qu'on ajoute
                        else if (getMark.className() == ""){
                            if (Mark != null) {
                                Mark.addAppreciation(marks.get(i).text())
                            }
                        }

                        // On change de matière, alors on s'arrête là.
                        else {
                            break
                        }
                    }
                }
            }

        }


        // Anglais : { totalMark : 2064, numberOfMarks : 2, avg : 20 }

        //var markOfEachMatter = listOf<String>()
        for (mark in allMarksClass){
           Matter(mark.matiere,1,20.0f, mattersList)
        }

        println("MATTERLIST==========")
        println(mattersList)

        for (matter in mattersList){
            println(matter.name)
            println(matter.totalMarks)
            println(matter.numberOfMarks)
        }

        for (mark in allMarksClass){
            avg += mark.note.toFloat()
        }

        avg/= allMarksClass.size



    }.start()
}

fun allCalendar(){
    Thread {
        val document = Jsoup
            .connect(url+"?my=edt")
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
            .cookie("sdv",cookie)
            .get()

        val script: Element? = document.select("script")[9]
        var courses = script.toString().split("events:[")

        courses = listOf(courses[1])
        courses = courses.toString().split("}],")
        courses = listOf(courses[0])

        val dictEdt = courses.toString().replace("[","")
            .replace("]","")
            .split("},{").toMutableList()

        dictEdt[0] = dictEdt[0].replace("{","")
        // Pour chaque cours
        dictEdt.forEach { child ->
            // On transforme string to List en séparant chaque l'élément (par ": et ,)
            val toList = child.split("\":",",").toMutableList()

            // On supprimer les caractères inutiles
            for (s in toList.indices) {
                toList[s] = toList[s].replace("\"\"","?").replace("\"","")
            }

            // On récupère seulement le nom du cours
            if (toList[3].contains("type_cours")){
                toList[3] = toList[3].split("type_cours")[1]
                    .split(">")[1].split("<")[0]
            }

            // On récupère seulement le nom/prénom de l'intervenant
            if (toList[5].contains("Intervenant")){
                toList[5] = toList[5].split(" : ")[1]
                if (toList[5].isBlank()){
                    toList[5] = "?"
                }
            }

            // On récupère seulement la salle
            if (toList[7].contains("Salle")){
                toList[7] = toList[7].split(" : ")[1]
                if (toList[7].isBlank()){
                    toList[7] = "?"
                }
            }




            // on l'ajoute à notre liste
            if (toList[3]?.contains("Rendu") == true){
                // on crée le dictionnaire
                for (info in toList){
                    //println("=================TEST===========")
                    info.replace("r<br","")//.replace("\\","")
                        .replace("\\u00e9","test")
                    //(info.contains("\\u00e9"))
                    //charReplace(info)
                }

                val coursDict = mutableMapOf<String,String>(
                    toList[0] to toList[1],
                    "Matière" to toList[3],
                    "Description" to toList[5],
                    "Début" to toList[9],
                    "Fin" to toList[11],
                )

                dataWorks += listOf(coursDict) as MutableList<MutableMap<String, String>>
            }
            else{
                // on crée le dictionnaire
                val coursDict = mutableMapOf<String,String>(
                    toList[0] to toList[1],
                    "Matière" to toList[3],
                    "Intervenant" to toList[5],
                    "Salle" to toList[7],
                    "Début" to toList[9],
                    "Fin" to toList[11],
                )
                dataCalendar += listOf(coursDict) as MutableList<MutableMap<String, String>>
            }

        }
        dataCalendar.sortBy { it["Début"] }


    }.start()
}

fun validatePresence(){
    Thread{
        actualID = mapOf()
        dataId = mutableListOf()
        val loginForm = Jsoup.connect(urlLogin)
            .data("login", dataUsername)
            .data("pass", dataPassword)
            .post()

        if (loginForm.text().contains("Accès valide !") ) {
            cookie = loginForm.connection().cookieStore().cookies[0].value

            val document = Jsoup
                .connect(url+"presences")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
                .cookie("sdv",cookie)
                .get()

            val idList = document.select("a").select(".btn") // Les boutons par aller à la page présence de la matière

            for (id in idList){
                val idNumber = id.attr("href").split("s/")[2]
                val idUrl = "presences/s/${idNumber}"
                dataId.add(idUrl)
            }

            // Autre function

            // Format de date et date actuelle
            val dateFormat = SimpleDateFormat("kk'h'mm") // format 0h00
            val currentDate = dateFormat.format(Date())

            println(dataId)
            for (id in dataId){

                val document = Jsoup
                    .connect(url+id)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
                    .cookie("sdv",cookie)
                    .get()

                val tds = document.select("td")
                val matiere = document.select("h4")[1].text()

                val date = tds[0]
                val time = tds[1]
                val listHour = time.text().split(" - ") // Heure du cours
                val firstHour = listHour[0] // début
                val lastHour = listHour[1] // fin

                /*
                println("===================")
                println(currentDate)
                println(firstHour)
                println(lastHour)
                println(currentDate >= firstHour)
                println(currentDate <= lastHour)*/

                if (firstHour <= currentDate && currentDate <= lastHour){
                    val dict = mapOf<String,String>(
                        "url" to id,
                        "Time" to time.text(),
                        "Matière" to matiere,
                        "Date" to date.text(),
                        "id" to id.split("s/")[2]
                    )
                    actualID = dict
                }


            }

            if (actualID["url"]!=null){
                val documentIfOpen = Jsoup
                    .connect(url+ actualID["url"])
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
                    .cookie("sdv",cookie)
                    .get()

                val validationButton = documentIfOpen.select("#body_presence")

                val ifFinish = documentIfOpen.select(".alert")

                if (validationButton.text().contains("Valider la présence")){
                    resultValidation = "Appel Ouvert"
                }
                else if (ifFinish.text().contains("noté")){
                    resultValidation = "Vous êtes déjà noté présent"
                }
                else{
                    resultValidation = "Appel Non Ouvert"
                }
            }

        }

    }.start()

}

fun activatePresence(){
    Thread{
        val document = Jsoup
            .connect(url+ actualID["url"])
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
            .cookie("sdv",cookie)
            .get()

        val validationButton = document.select("#body_presence")

        if (validationButton.text().contains("Valider la présence")){
            val documentPost = Jsoup
                .connect(urlValidate)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
                .cookie("sdv", cookie)
                .data("act","set_present")
                .data("seance_pk", actualID["id"])
                .post()

            if (documentPost.text().contains("Validation impossible")){
                resultValidation = "Impossible de vous mettre présent !"
            }
            else{
                resultValidation = "Vous avez été noté présent !"
            }

        }
        else{
            resultValidation = "L'appel n'est pas encore ouvert"
        }

    }.start()
}

fun charReplace(label: String){
    println("==================")
    println(label)
    label.replace("\\u00e9","é")
    println(label)
}

fun getActivity(act: Activity){
    mainActivity = act
}

fun getInformations(){
    Thread{
        val document = Jsoup
            .connect(url+"?my=file")
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
            .cookie("sdv",cookie)
            .get()

        val infos = document.select("address")[0]
        /*println("====================")
        println(infos)
        println(infos::class.simpleName)

        println(infos.html())*/
        val infoList = infos.html().split(">","<").toMutableList()
        /*for ((s,index) in infoList.withIndex()){
            println("${s} --- ${index}")
        }*/

        //infos.child(8).toString()

        infoList[4] = infoList[4].removeRange(0,1)

        userInformations += "${infoList[4]} ${infoList[6]}"
        userInformations += infoList[22].replace(" ","")
        userInformations += infoList[32].substring(1,infoList[32].length)
        userInformations += infoList[38].replace(" ","")

    }.start()
}

//saving data
fun saveData() {
    val sharedPref = mainActivity?.getPreferences(ComponentActivity.MODE_PRIVATE) ?: return
    with(sharedPref.edit()) {
        putString("username", com.example.pepalapp.pages.dataUsername)
        putString("password", com.example.pepalapp.pages.dataPassword)
        commit()
    }
}

fun getData() {
    val sharedPref = mainActivity?.getPreferences(Context.MODE_PRIVATE) ?: return
    dataUsername = sharedPref.getString("username", "").toString()
    dataPassword = sharedPref.getString("password", "").toString()

}

@RequiresApi(Build.VERSION_CODES.O)
fun connection(navController: NavHostController){
    dataAllNotes = null
    dataCalendar = mutableListOf()
    dataWorks = mutableListOf()
    userInformations = mutableListOf()
    loginVerification() // Se connecte
    MakeToast(label = "Connexion")
    //Thread.sleep
    var limit = 0
    while (cookie.isBlank()) {
        Thread.sleep(100)
        if (limit >= 20){
            break
        }
        limit++
    }
    if (cookie.isNotBlank()){
        getUserImage() // Récupère l'image
        getMarks() // Enregistre les notes dans une liste
        allCalendar() // Enregistre les cours dans une liste
        validatePresence()
        getInformations()
        //getMarksWithCoef()
    }
    else{
        MakeToast(label = "Erreur")
    }
    limit = 0
    while (dataAllNotes == null || dataCalendar.isEmpty() || dataWorks.isEmpty() || userInformations.isEmpty())
    {
        Thread.sleep(100)
        if (limit >= 20){
            break
        }
        limit++
    }


}
