package art.scidsgn.portal2kafka.consoleobserver

import art.scidsgn.portal2kafka.consoleobserver.event.PortalEvent

class PrettyLog {
    companion object {
        fun log(title: String, message: String, titleColor: Color = Color.Reset) {
            println("\n${titleColor.ansiString}${Color.Black.ansiString} $title ${Color.Reset.ansiString} $message")
        }

        fun logChunkInfo(chunk: String) {
            log("CHUNK", "Received chunk of length ${chunk.length}", Color.BgPurple)
            println(chunk)
        }

        fun logEventInfo(event: PortalEvent) {
            log("EVENT", event.toString(), Color.BgCyan)
        }

        fun logRequestInfo(endpoint: String, body: String) {
            log("REQUEST", "POST $endpoint", Color.BgGreen)
            println(body)
        }
    }

    enum class Color(val ansiString: String) {
        Reset("\u001B[0m"),
        Black("\u001B[30m"),
        BgPurple("\u001B[45m"),
        BgCyan("\u001B[46m"),
        BgGreen("\u001B[42m")
    }
}