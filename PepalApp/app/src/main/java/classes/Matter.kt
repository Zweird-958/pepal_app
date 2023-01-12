package classes

var mattersList = mutableListOf<Matter>()

class Matter (val name: String, var totalMarks: Float){

    public var numberOfMarks: Int = 0

    constructor(name: String, totalMarks: Float, mattersList: MutableList<Matter>) :
            this(name, totalMarks) {
        val matter = mattersList.firstOrNull { it.name == name }
        if (matter != null) {
            matter.addNewMark(totalMarks)
        } else {
            this.numberOfMarks = 1
            mattersList.add(this)
        }
    }

    fun addNewMark(newMark: Float){
        this.numberOfMarks += 1
        this.totalMarks += newMark
    }
}