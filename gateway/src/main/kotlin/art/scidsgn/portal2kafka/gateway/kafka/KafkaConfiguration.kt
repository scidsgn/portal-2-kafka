package art.scidsgn.portal2kafka.gateway.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaConfiguration {

    @Bean
    fun gatewayTopic(): NewTopic {
        return TopicBuilder.name("portal-gateway").build()
    }
}