package player

import util.crashPrint
import util.logPrint
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.net.Socket
import kotlin.system.exitProcess

/**
 * Created by GrandTemplar on 7/16/2016.
 */
class Player(val socket : Socket, val id : Int) {
    val input = try {
        socket.inputStream.bufferedReader()
    } catch (e : IOException) {
        crashPrint(e, "input stream")
        socket.tryClosing("getting input stream")
    }

    val output = try {
        socket.outputStream.bufferedWriter()
    } catch (e : IOException) {
        crashPrint(e, "output stream")
        socket.tryClosing("getting output stream")
    }

    fun Socket.tryClosing(msg : String) {
        try {
            if (!this.isClosed) {
                this.close()
            }
        } catch (e : IOException) {
            crashPrint(e, "$msg in closing socket (LOL WHAT)")
            exitProcess(-1)
        }
    }

    fun BufferedReader.tryClosing() {
        try {
            this.close()
        } catch (e: IOException) {
            crashPrint(e, "UNBELIEVABLE")
        }
    }

    fun BufferedWriter.tryClosing() {
        try {
            this.close()
        } catch (e: IOException) {
            crashPrint(e, "when flushing buffer")
        }
    }

    val message : String get() = (input as BufferedReader).readLine()

    fun tryClosing(msg : String) {
        socket.tryClosing(msg)
        (input as BufferedReader).tryClosing()
        (output as BufferedWriter).tryClosing()
        logPrint(msg)
    }
}