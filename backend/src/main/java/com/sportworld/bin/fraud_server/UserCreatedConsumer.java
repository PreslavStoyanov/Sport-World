package com.sportworld.bin.fraud_server;

import com.sportworld.lib.events.UserCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UserCreatedConsumer {
    private final Logger logger = Logger.getLogger(UserCreatedConsumer.class.getName());

    @KafkaListener(
            topics = "com.sportworld.createuser",
            groupId = "groupId",
            containerFactory = "userCreatedContainerFactory"
    )
    void listener(UserCreatedEvent data) {
        logger.info(String.format("Listener received: %s", data));
        if (data.user.getUsername().equals("manolchoo")) {
            logger.warning("Something is fishy... This guy is called Manol, but no one is called like that anymore!");
        }
    }
}
