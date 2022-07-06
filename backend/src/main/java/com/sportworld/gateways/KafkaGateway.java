package com.sportworld.gateways;

import com.sportworld.lib.events.UserCreatedEvent;
import com.sportworld.lib.events.UserNotificationEmailJob;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaGateway {
    private final String userCreatedTopic;
    private final String userNotificationEmailJobTopic;
    private final Integer userNotificationEmailJobPortionsCnt;
    private final KafkaTemplate<String, UserCreatedEvent> userCreatedPublisher;
    private final KafkaTemplate<String, UserNotificationEmailJob> userNotificationEmailJobPublisher;

    public KafkaGateway(
            String userCreatedTopic,
            String userNotificationEmailJobTopic,
            Integer userNotificationEmailJobPortionsCnt,
            KafkaTemplate<String, UserCreatedEvent> userCreatedPublisher,
            KafkaTemplate<String, UserNotificationEmailJob> userNotificationEmailJobPublisher) {
        this.userCreatedTopic = userCreatedTopic;
        this.userNotificationEmailJobTopic = userNotificationEmailJobTopic;
        this.userNotificationEmailJobPortionsCnt = userNotificationEmailJobPortionsCnt;
        this.userCreatedPublisher = userCreatedPublisher;
        this.userNotificationEmailJobPublisher = userNotificationEmailJobPublisher;
    }

    public void sendUserCreatedEvent(UserCreatedEvent e) {
        userCreatedPublisher.send(userCreatedTopic, e);
    }

    public void sendUserNotificationEmailJob(UserNotificationEmailJob job) {
        userNotificationEmailJobPublisher.send(new ProducerRecord<>(
                userNotificationEmailJobTopic, job.getUserID() % userNotificationEmailJobPortionsCnt,
                userNotificationEmailJobTopic, job));
    }
}
