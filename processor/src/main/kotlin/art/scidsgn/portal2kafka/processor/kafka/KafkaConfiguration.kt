package art.scidsgn.portal2kafka.processor.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration
import org.springframework.kafka.config.KafkaStreamsConfiguration
import org.springframework.kafka.config.TopicBuilder

@Configuration
@EnableKafka
@EnableKafkaStreams
class KafkaConfiguration {
    @Value("\${spring.kafka.bootstrapServers}")
    lateinit var bootstrapServers: String

    @Bean(KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    fun kafkaStreamsConfig(): KafkaStreamsConfiguration {
        val props = hashMapOf(
            Pair(StreamsConfig.APPLICATION_ID_CONFIG, "portal-processor"),
            Pair(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers),
            Pair(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().javaClass.name),
            Pair(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().javaClass.name)
        )

        return KafkaStreamsConfiguration(props.toMap())
    }

    @Bean("gatewayTopic")
    fun gatewayTopic(): NewTopic {
        return TopicBuilder.name("portal-gateway").build()
    }

    @Bean("playerTopic")
    fun playerTopic(): NewTopic {
        return TopicBuilder.name("portal-player").build()
    }

    @Bean("chamberTopic")
    fun chamberTopic(): NewTopic {
        return TopicBuilder.name("portal-chamber").build()
    }
}