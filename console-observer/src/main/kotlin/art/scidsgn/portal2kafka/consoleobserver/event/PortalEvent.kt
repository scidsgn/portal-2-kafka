package art.scidsgn.portal2kafka.consoleobserver.event

import kotlinx.serialization.Serializable

@Serializable
data class PortalEvent(
    val type: String,
    val target: String,
    val position: Vector,
    val velocity: Vector,
    val map: String
) {

    @Serializable
    data class Vector(val x: Float, val y: Float, val z: Float)
}
