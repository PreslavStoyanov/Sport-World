package com.sportworld.gateways;

import com.sportworld.lib.events.UserCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaGateway {
    private final KafkaTemplate<String, UserCreatedEvent> userCreatedPublisher;
    private final String userCreatedTopic;

    public KafkaGateway(
            String userCreatedTopic,
            KafkaTemplate<String, UserCreatedEvent> userCreatedPublisher) {
        this.userCreatedTopic = userCreatedTopic;
        this.userCreatedPublisher = userCreatedPublisher;
    }

    public void sendUserCreatedEvent(UserCreatedEvent e) {
        userCreatedPublisher.send(userCreatedTopic, e);
    }
}
