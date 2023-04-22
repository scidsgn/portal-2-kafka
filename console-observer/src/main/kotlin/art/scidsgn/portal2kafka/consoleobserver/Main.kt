package art.scidsgn.portal2kafka.consoleobserver

import art.scidsgn.portal2kafka.consoleobserver.configuration.ConfigurationLoader
import art.scidsgn.portal2kafka.consoleobserver.event.EventParser
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import art.scidsgn.portal2kafka.consoleobserver.observer.ValveConsoleObserver
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

suspend fun main(args: Array<String>) {
    val configuration = ConfigurationLoader.loadFrom("/configuration.yml")

    val observer = ValveConsoleObserver(configuration.gamePath)
    val parser = EventParser()

    val client = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }

    coroutineScope {
        observer.observe { chunk ->
            if (configuration.log.console) {
                PrettyLog.logChunkInfo(chunk)
                println(chunk)
            }

            parser.parseChunk(chunk).map { event ->
                async {
                    if (configuration.log.events) {
                        PrettyLog.logEventInfo(event)
                    }

                    if (configuration.log.requests) {
                        client.post {
                            url(configuration.endpoint)
                            contentType(ContentType.Application.Json)
                            setBody(event)
                        }

                        PrettyLog.logRequestInfo(configuration.endpoint)
                        println(Json.encodeToString(event))
                    }
                }
            }
        }
    }
}
