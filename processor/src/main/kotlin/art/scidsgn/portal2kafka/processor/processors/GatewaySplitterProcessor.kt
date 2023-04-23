package art.scidsgn.portal2kafka.processor.processors

import art.scidsgn.portal2kafka.processor.dto.PortalEventDto
import jakarta.annotation.Resource
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Branched
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.Named
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GatewaySplitterProcessor {

    @Resource(name = "gatewayTopic")
    lateinit var gatewayTopic: NewTopic

    @Resource(name = "playerTopic")
    lateinit var playerTopic: NewTopic

    @Resource(name = "chamberTopic")
    lateinit var chamberTopic: NewTopic

    @Autowired
    fun buildPipeline(streamsBuilder: StreamsBuilder) {
        val gatewayStream =
            streamsBuilder.stream(gatewayTopic.name(), Consumed.with(Serdes.String(), PortalEventDto.serdes()))

        val branches = gatewayStream.split(Named.`as`("portal-"))
            .branch({ _, v -> v.target == "player" }, Branched.`as`("player"))
            .branch({ _, v -> v.target != "player" }, Branched.`as`("chamber"))
            .defaultBranch()

        branches["portal-player"]!!.to(playerTopic.name())
        branches["portal-chamber"]!!.to(chamberTopic.name())
    }
}