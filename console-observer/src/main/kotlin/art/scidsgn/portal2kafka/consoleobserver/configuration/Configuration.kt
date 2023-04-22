package art.scidsgn.portal2kafka.consoleobserver.configuration

import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
    val gamePath: String,
    val endpoint: String,
    val log: LogConfiguration
) {
    @Serializable
    data class LogConfiguration(val console: Boolean, val events: Boolean, val requests: Boolean)
}
