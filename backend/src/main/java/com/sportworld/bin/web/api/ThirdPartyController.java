package com.sportworld.bin.web.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/matches")
public class ThirdPartyController {

    @GetMapping(value = "/live")
    public String getLiveMatches() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.sofascore.com/api/v1/sport/football/events/live")
                .get()
                .addHeader("authority", "api.sofascore.com")
                .addHeader("accept", "*/*")
                .addHeader("accept-language", "en-US,en;q=0.9")
                .addHeader("cache-control", "max-age=0")
                .addHeader("if-none-match", "W/^\\^b4e1eda016^^")
                .addHeader("origin", "https://www.sofascore.com")
                .addHeader("referer", "https://www.sofascore.com/")
                .addHeader("sec-ch-ua", "^\\^")
                .build();

        try {
            return Objects.requireNonNull(client.newCall(request).execute().body()).string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/featured")
    public String getFeaturedMatches() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.sofascore.com/api/v1/odds/1/featured-events/football")
                .get()
                .addHeader("authority", "api.sofascore.com")
                .addHeader("accept", "*/*")
                .addHeader("cache-control", "max-age=0")
                .addHeader("if-none-match", "W/^\\^288e4ca4a2^^")
                .addHeader("sec-ch-ua", "^\\^")
                .build();

        try {
            return Objects.requireNonNull(client.newCall(request).execute().body()).string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
