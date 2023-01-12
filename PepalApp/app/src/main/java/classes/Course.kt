package classes

var courseList = listOf<Course>()

class Course (id: Int,matiere: String,val intervenant: String, val salle: String,heureDebut: String?,dateDebut : String?,heureFin: String?,dateFin: String?) : Assignment(id,matiere,heureDebut,dateDebut,heureFin,dateFin) {
    constructor(id: Int,matiere: String,intervenant: String,   salle: String,heureDebut: String?,dateDebut : String?,heureFin: String?,dateFin: String?, courseList: MutableList<Course>) : this(id,matiere,intervenant,salle,heureDebut,dateDebut,heureFin,dateFin){
        if (courseList.any { it.id != id }){
            courseList.add(this)
        }
    }
}