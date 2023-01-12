package classes

import kotlin.time.measureTime

abstract class Assignment (val id: Int,val matiere: String,val heureDebut: String?,val dateDebut : String?, val heureFin: String?, val dateFin: String?){
    override fun toString(): String {
        println(id)
        println(matiere)
        println(heureDebut)
        println(heureFin)
        println(dateDebut)
        println(dateFin)
        return ""
    }
}