package art.scidsgn.portal2kafka.processor.dto

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

data class PortalEventDto(
    val type: String,
    val target: String,
    val position: VectorDto,
    val velocity: VectorDto,
    val map: String
) {
    companion object {
        fun serdes(): Serde<PortalEventDto>? {
            val objectMapper = ObjectMapper().registerKotlinModule()

            val serializer = JsonSerializer<PortalEventDto>()
            val deserializer = JsonDeserializer(PortalEventDto::class.java, objectMapper)

            val serde = Serdes.serdeFrom(serializer, deserializer)
            serde.configure(
                mapOf(
                    Pair(
                        JsonDeserializer.TYPE_MAPPINGS,
                        "portal_event:art.scidsgn.portal2kafka.processor.dto.PortalEventDto"
                    )
                ).toMap(),
                false
            )

            return serde
        }
    }
}
