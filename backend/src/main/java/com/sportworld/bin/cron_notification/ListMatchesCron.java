package com.sportworld.bin.cron_notification;

import com.sportworld.core.MatchService;
import com.sportworld.core.models.Match;
import com.sportworld.gateways.EmailsGateway;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListMatchesCron {
    private final EmailsGateway emailsGateway;
    private final MatchService matchService;

    public ListMatchesCron(
            EmailsGateway emailsGateway,
            MatchService matchService) {
        this.emailsGateway = emailsGateway;
        this.matchService = matchService;
    }

    @Scheduled(cron = "1 50 8 * * *")
    public void sendMatchesEmail() {
        List<Match> matches = matchService.listMatches(0, 1000);
        String msg = buildEmailMessage(matches);
        emailsGateway.sendMail("Today matches", msg, "preslav0609@gmail.com");
    }

    private String buildEmailMessage(List<Match> matches) {

        StringBuilder msg = new StringBuilder("Here are matches for today: \n");

        for (Match match : matches) {
            msg.append(String.format("\t%s : %s\n", match.league.name, match.title));
        }

        return msg.toString();
    }
}
