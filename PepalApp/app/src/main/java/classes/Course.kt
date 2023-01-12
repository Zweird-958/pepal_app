package classes

class Course (id: Int,matiere: String,val intervenant: String, val salle: String,heureDebut: String,dateDebut : String,heureFin: String,dateFin: String) : Assignment(id,matiere,heureDebut,dateDebut,heureFin,dateFin) {
}