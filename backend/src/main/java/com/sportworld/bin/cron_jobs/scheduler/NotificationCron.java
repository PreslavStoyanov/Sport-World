package com.sportworld.bin.cron_jobs.scheduler;

import com.sportworld.core.UserService;
import com.sportworld.core.models.User;
import com.sportworld.gateways.KafkaGateway;
import com.sportworld.lib.events.UserNotificationEmailJob;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class NotificationCron {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final KafkaGateway kafkaGateway;
    private final UserService userService;

    public NotificationCron(KafkaGateway kafkaGateway, UserService userService) {
        this.kafkaGateway = kafkaGateway;
        this.userService = userService;
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void sendGradesEmails() {
        logger.info("Executing scheduled job...");
        int resultsCnt = 1;
        int page = 0;
        while (resultsCnt > 0) {
            List<User> users = userService.listUsers(page, 1000);
            for (User u : users) {
                logger.info("-------------------------------------------\n\nSending notification for user " + u.getId());
                kafkaGateway.sendUserNotificationEmailJob(new UserNotificationEmailJob(u.getId()));
            }

            page++;
            resultsCnt = users.size();
        }
    }
}
