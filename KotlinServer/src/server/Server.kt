package server

import player.Player
import util.*
import java.io.IOException
import java.net.ServerSocket
import kotlin.system.exitProcess

/**
 * Created by GrandTemplar on 7/16/2016.
 */

class Server {
    fun parse(player: Player) {
        val message = player.message.toUpperCase()
        val tokens = message.split(' ')
        if (tokens.size < 2) {
            player.tryClosing("Not enough tokens")
            return
        }
        if (tokens[0] != "SHIT") {
            player.tryClosing("Need more shit")
            return
        }
        if (tokens[1] == "NEW") {
            createNewGame(player)
        }
        if (tokens[1] == "JOIN") {
            if (tokens.size < 3) {
                player.tryClosing("Need ID for joining")
                return
            }
            var id = try {
                tokens[3].toInt()
            } catch (e: NumberFormatException) {
                player.tryClosing("Please use valid ID. It must be an integer from -1 to ${Int.MAX_VALUE}")
                return
            }
            id = if (id == -1) games.toList()[generator.nextInt(games.size)].first else return
            joinGame(id, player)
            return
        }
        player.tryClosing("Please use valid command: NEW or JOIN")
        return
    }

    fun run(port : Int) {
        val server  = try {
            ServerSocket(port)
        } catch (e : IOException) {
            crashPrint(e, "binding server")
            exitProcess(-1)
        }
        while (true) {
            val player = Player(try {
                    server.accept()
                } catch (e : IOException) {
                    crashPrint(e, "accepting socket")
                    continue
                }, nextID)
            parse(player)
        }

    }

}

