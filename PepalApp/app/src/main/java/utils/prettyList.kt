package utils

fun prettyList(list: MutableList<String>): List<String> {
    return list.map{ it.replace("r<br","").replace("\\","")
        .replace("\u00e9","é").replace("u00e9","é")}
}