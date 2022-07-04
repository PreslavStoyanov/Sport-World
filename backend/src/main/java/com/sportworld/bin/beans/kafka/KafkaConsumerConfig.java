package com.sportworld.bin.beans.kafka;

import com.sportworld.lib.events.UserCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("localhost:9092")
    private String bootstrapServers;

    public Map<String, Object> consumerConfig(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    public ConsumerFactory<String, UserCreatedEvent> userCreatedConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(), new StringDeserializer(), new JsonDeserializer<>(UserCreatedEvent.class));
    }

    @Bean
    public KafkaListenerContainerFactory<
            ConcurrentMessageListenerContainer<String, UserCreatedEvent>> userCreatedContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> f =
                new ConcurrentKafkaListenerContainerFactory<>();
        f.setConsumerFactory(userCreatedConsumerFactory());
        return f;
    }
}
