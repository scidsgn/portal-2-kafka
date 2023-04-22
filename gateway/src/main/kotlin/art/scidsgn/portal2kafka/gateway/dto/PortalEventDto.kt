package art.scidsgn.portal2kafka.gateway.dto

data class PortalEventDto(
    val type: String,
    val target: String,
    val position: VectorDto,
    val velocity: VectorDto,
    val map: String
)
