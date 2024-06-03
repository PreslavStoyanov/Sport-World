package com.sportworld.bin.cron_jobs.scheduler;

import com.sportworld.core.UserService;
import com.sportworld.core.models.User;
import com.sportworld.gateways.KafkaGateway;
import com.sportworld.lib.events.UserNotificationEmailJob;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationCron {
    private final KafkaGateway kafkaGateway;
    private final UserService userService;

    public NotificationCron(KafkaGateway kafkaGateway, UserService userService) {
        this.kafkaGateway = kafkaGateway;
        this.userService = userService;
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void sendGradesEmails() {
        System.out.println("Executing scheduled job...");
        int resultsCnt = 1;
        int page = 0;
        while (resultsCnt > 0) {
            List<User> users = userService.listUsers(page, 1000);
            for (User u : users) {
                System.out.println("-------------------------------------------\n\nSending notification for user " + u.getId());
                kafkaGateway.sendUserNotificationEmailJob(new UserNotificationEmailJob(u.getId()));
            }

            page++;
            resultsCnt = users.size();
        }
    }
}
