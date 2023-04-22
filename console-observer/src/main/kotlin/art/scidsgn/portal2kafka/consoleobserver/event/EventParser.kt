package art.scidsgn.portal2kafka.consoleobserver.event

class EventParser {
    private val eventCheckRegex = Regex("^portal2kafka event: .*$")

    private val numberPattern = "\\d+\\.\\d+"
    private val positionPattern =
        "\\(vector : \\(((?<posx>$numberPattern), (?<posy>$numberPattern), (?<posz>$numberPattern)\\))\\)"
    private val velocityPattern =
        "\\(vector : \\(((?<velx>$numberPattern), (?<vely>$numberPattern), (?<velz>$numberPattern)\\))\\)"
    private val eventDataRegex = Regex(
        "^portal2kafka event: (?<type>\\S+) on (?<target>\\S+) @ $positionPattern, v: $velocityPattern on map (?<map>.*)$"
    )

    fun parseChunk(chunk: String): List<PortalEvent> {
        return chunk.split(Regex("\\r?\\n")).mapNotNull(this::parseLine)
    }

    fun parseLine(line: String): PortalEvent? {
        if (!eventCheckRegex.matches(line)) {
            return null
        }

        val matches = eventDataRegex.matchEntire(line) ?: return null

        return PortalEvent(
            matches.groups["type"]!!.value,
            matches.groups["target"]!!.value,
            PortalEvent.Vector(
                matches.groups["posx"]!!.value.toFloat(),
                matches.groups["posy"]!!.value.toFloat(),
                matches.groups["posz"]!!.value.toFloat()
            ),
            PortalEvent.Vector(
                matches.groups["velx"]!!.value.toFloat(),
                matches.groups["vely"]!!.value.toFloat(),
                matches.groups["velz"]!!.value.toFloat()
            ),
            matches.groups["map"]!!.value,
        )
    }
}