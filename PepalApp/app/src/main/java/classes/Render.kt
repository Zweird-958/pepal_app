package classes

var rendersList = mutableListOf<Render>()

class Render (id: Int,matiere: String,val description: String, heureDebut: String?,dateDebut : String?,heureFin: String?,dateFin: String?) : Assignment(id,matiere,heureDebut,dateDebut,heureFin,dateFin) {
    constructor(id: Int,matiere: String,description: String,heureDebut: String?,dateDebut : String?,heureFin: String?,dateFin: String?, rendersList: MutableList<Render>) : this(id,matiere,description,heureDebut,dateDebut,heureFin,dateFin){
        //if (rendersList.any { it.id != id }){
            rendersList.add(this)
        //}
    }
}