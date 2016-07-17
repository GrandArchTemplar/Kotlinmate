package figures

/**
 * Created by GrandTemplar on 7/16/2016.
 */
abstract class Figure {
    abstract fun move(posBegin : Int, posEnd : Int)
    abstract fun canMove(pos : Int) : Boolean
}