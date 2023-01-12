package com.example.pepalapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.invalidateGroupsWithKey
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

val MONTHS = listOf<String>(
    "jan",
    "feb",
    "mar","avr","mai","juin","juil","aout","sept","oct","nov","dec"
)


var allMarksClass = listOf<MarkClass>()
class MarkClass (matiere: String) {

    var matiere = matiere
    var indexToList: Int = 0
    var name: String = ""
    var date: String = ""
    var note: String = ""
    var appreciation: String = ""
    var coef: Float = 0.0F
    var formatDate: String = date

    fun addName(name: String){
        this.name = name
    }

    fun addDate(date: String){
        this.date = date
    }

    fun addNote(note: String){
        this.note = note
    }

    fun addAppreciation(appreciation: String){
        this.appreciation = appreciation
    }


    fun addToList() {
        this.indexToList = allMarksClass.size
        allMarksClass += this
        println(this.matiere + "added to list !")
    }

    fun addCoef(coef: Float) {
        this.coef = coef
    }

    fun changeFormatDate(){

    }

    fun showMark(){
        println("ID :  ${this.indexToList} ")
        println("Matière :  ${this.matiere} ")
        println("Coef :  ${this.coef} ")
        println("NAME :  ${this.name} ")
        println("DATE :  ${this.date} ")
        println("NOTE :  ${this.note} ")
        println("Appréciation :  ${this.appreciation} ")
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun sortByDate(): List<MarkClass> {
    var newList: List<MarkClass> = listOf()
    var dateList: List<Map<String,Any>> = listOf()
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    for (mark in allMarksClass){
        val date = mark.date.split(" ")
        val month = date.get(2).replace(".","")
        val day = date.get(1)
        val year = date.get(3)
        if (MONTHS.contains(month)){
            var monthInt = (MONTHS.indexOf(month) + 1).toString()
            if (monthInt.toInt() < 10) {
                monthInt = "0${monthInt}"
            }
            val newDate = "${year}-${monthInt}-${day}"
            dateList += mapOf("Date" to formatter.parse(newDate), "Mark" to mark)
        }

    }

    dateList = dateList.sortedBy { it["Date"] as Comparable<Any> }

    for (markDate in dateList){
        val mark = markDate.get("Mark") as MarkClass
        newList += mark
    }

    return newList
}

fun sortByMatiere(): List<MarkClass>{
    return allMarksClass.sortedBy { it.matiere }
}


fun sortByNote(): List<MarkClass>{
    return allMarksClass.sortedBy { it.note }
}