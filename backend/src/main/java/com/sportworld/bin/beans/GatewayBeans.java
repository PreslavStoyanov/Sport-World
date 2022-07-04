package com.sportworld.bin.beans;

import com.sportworld.gateways.EmailsGateway;
import com.sportworld.gateways.KafkaGateway;
import com.sportworld.lib.events.UserCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class GatewayBeans {
    @Value("com.sportworld.createuser")
    private String userCreatedTopic;

    @Bean
    public EmailsGateway emailsGateway() {
        return new EmailsGateway();
    }

    @Bean
    public KafkaGateway kafkaGateway(
            KafkaTemplate<String, UserCreatedEvent> userCreatedPublisher) {
        return new KafkaGateway(userCreatedTopic, userCreatedPublisher);
    }

}
