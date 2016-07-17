package game

import player.Player

/**
 * Created by GrandTemplar on 7/16/2016.
 */
class Game(val p1 : Player?, val p2 : Player?)  {
    fun turn(player : Player?, color : Int) {

    }
    fun step() {
        turn(p1, -1)
        turn(p2, 1)
    }
}