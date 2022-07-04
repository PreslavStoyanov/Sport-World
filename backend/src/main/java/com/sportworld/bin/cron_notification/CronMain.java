package com.sportworld.bin.cron_notification;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.sportworld.bin.beans", "com.sportworld.bin.cron_notification"})
@EnableScheduling
public class CronMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CronMain.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
