package art.scidsgn.portal2kafka.consoleobserver.observer

import dev.vishna.watchservice.KWatchChannel
import dev.vishna.watchservice.KWatchEvent
import dev.vishna.watchservice.asWatchChannel
import kotlinx.coroutines.channels.consumeEach
import java.io.File

class ValveConsoleObserver(gamePath: String) {

    private val consoleFile = File(gamePath).resolve("console.log")
    private val channel = consoleFile.asWatchChannel(KWatchChannel.Mode.SingleFile)

    private var currentContents = ""

    suspend fun observe(chunkHandler: (chunk: String) -> Unit) {
        channel.consumeEach {
            if (it.kind == KWatchEvent.Kind.Deleted) {
                clear()
            } else {
                val chunk = modify(consoleFile.readText())

                if (chunk.isNotEmpty()) {
                    chunkHandler(chunk)
                }
            }
        }
    }

    private fun modify(newContents: String): String {
        var chunk: String

        if (newContents.length < currentContents.length) {
            chunk = newContents.trim()
        } else {
            chunk = newContents.substring(currentContents.length)
        }

        currentContents = newContents

        return chunk.trim()
    }

    private fun clear() {
        currentContents = ""
    }
}