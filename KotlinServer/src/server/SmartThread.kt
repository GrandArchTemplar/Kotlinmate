package server

import game.Game

/**
 * Created by GrandTemplar on 7/16/2016.
 */
class SmartThread(val id : Int) : Thread() {
    var runnedGames = mutableListOf<Game>()
    var isExit = false
    override fun run() {
        while (!isExit) {
            if ((id == goodThread)&&(readyGames.isNotEmpty())) {
                runnedGames.add(Game(readyGames[id]?.first, readyGames[id]?.second))
                readyGames.remove(id)
            }
            runnedGames.map { it.step() }
        }
    }
}