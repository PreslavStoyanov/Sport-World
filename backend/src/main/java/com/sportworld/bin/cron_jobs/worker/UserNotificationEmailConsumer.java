package com.sportworld.bin.cron_jobs.worker;

import com.sportworld.core.MatchService;
import com.sportworld.core.UserService;
import com.sportworld.core.models.Match;
import com.sportworld.gateways.EmailsGateway;
import com.sportworld.lib.events.UserNotificationEmailJob;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserNotificationEmailConsumer {
    private final EmailsGateway emailsGateway;
    private final UserService userService;
    private final MatchService matchService;

    public UserNotificationEmailConsumer(
            EmailsGateway emailsGateway,
            UserService userService,
            MatchService matchService) {
        this.emailsGateway = emailsGateway;
        this.userService = userService;
        this.matchService = matchService;
    }

    @KafkaListener(
            topics = "com.sportworld.user-notification-email",
            groupId = "user-notification-email-worker",
            containerFactory = "userNotificationEmailContainerFactory"
    )

    void listener(UserNotificationEmailJob job) {
        System.out.println("Sending daily notification email to user " + job.getUserID());
        List<Match> matches = matchService.listMatches(0, 1000);
        String msg = buildEmailMessage(matches);
        emailsGateway.sendMail("Today matches", msg, "preslav0609@gmail.com");
    }

    private String buildEmailMessage(List<Match> matches) {
        StringBuilder msg = new StringBuilder("Here are matches for today: \n");
        for (Match match : matches) {
            msg.append(String.format("\t%s : %s\n", match.league().name(), match.title()));
        }
        return msg.toString();
    }
}
