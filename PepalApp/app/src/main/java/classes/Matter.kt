package classes

var mattersList = mutableListOf<Matter>()

class Matter (val name: String, var numberOfMarks: Int, var totalMarks: Float){

    constructor(name: String, numberOfMarks: Int, totalMarks: Float,mattersList: MutableList<Matter>) :
            this(name, numberOfMarks, totalMarks) {
        if (mattersList.any { it.name == name }) {
            println("Error : Name already taken")
        } else {
            mattersList.add(this)
        }
    }

    fun addNewMark(newMark: Float){
        this.numberOfMarks += 1
        this.totalMarks += newMark
    }
}