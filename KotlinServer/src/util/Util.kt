package util

import java.util.*


/**
 * Created by GrandTemplar on 7/16/2016.
 */
val generator = Random()
var genID = 0
var idSet = mutableSetOf<Int>()
fun crashPrint(e : Exception, msg : String) {
}

val nextID : Int get() {
    idSet.add(genID)
    return genID++
}

fun logPrint(msg : String) {
}
