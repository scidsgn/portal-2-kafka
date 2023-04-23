package art.scidsgn.portal2kafka.gateway.kafka

import art.scidsgn.portal2kafka.gateway.dto.PortalEventDto
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class KafkaGateway(val gatewayTopic: NewTopic, val kafkaTemplate: KafkaTemplate<String, PortalEventDto>) {

    fun send(eventDto: PortalEventDto) {
        val message = MessageBuilder
            .withPayload(eventDto)
            .setHeader(KafkaHeaders.TOPIC, gatewayTopic.name())
            .build()

        kafkaTemplate.send(message)
    }
}