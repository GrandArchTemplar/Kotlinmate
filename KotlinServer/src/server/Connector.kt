package server

import player.Player

/**
 * Created by GrandTemplar on 7/16/2016.
 */
fun createNewGame(player: Player) {
    games.put(player.id, player)
}

fun joinGame(id : Int, joinPlayer: Player) {
    if (games.containsKey(id)) {
        readyGames.put(id, Pair(games[id], joinPlayer))
    }
}

val goodThread : Int get() = threads.sortedBy { it.runnedGames.size }[0].id
var threads = listOf<SmartThread>()
var games = mutableMapOf<Int, Player>()
var readyGames = mutableMapOf<Int, Pair<Player?, Player?>>()