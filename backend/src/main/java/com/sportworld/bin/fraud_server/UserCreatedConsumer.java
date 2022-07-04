package com.sportworld.bin.fraud_server;

import com.sportworld.lib.events.UserCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedConsumer {
    @KafkaListener(
            topics = "com.sportworld.createuser",
            groupId = "groupId",
            containerFactory = "userCreatedContainerFactory"
    )
    void listener(UserCreatedEvent data) {
        System.out.println("Listener received: " + data + " ");
        if (data.user.username.equals("manolchoo")) {
            System.out.println("Something is fishy... This guy is called Manol, but no one is called like that anymore!");
        }
    }
}
