package com.sportworld.gateways;

import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EmailsGateway {
    private static final String DOMAIN = "sandbox4db94ae521554f1ebbde171d382b13be.mailgun.org";
    private static final String FROM_USER = "Sport-World <admin@sport-world.com>";
    private static final String CREDENTIALS_USER = "api";
    private static final String CREDENTIALS_PASSWORD = "e6fb667a639564e7b1b1be0066f7ca3f-50f43e91-42712953";

    public void sendMail(String subject, String body, String recipients) {
        try {
            List<NameValuePair> form = new ArrayList<>();
            form.add(new BasicNameValuePair("from", FROM_USER));
            form.add(new BasicNameValuePair("to", recipients));
            form.add(new BasicNameValuePair("subject", subject));
            form.add(new BasicNameValuePair("text", body));
            postMessage(String.format("https://api.mailgun.net/v3/%s/messages", DOMAIN), form);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String postMessage(String URL, List<NameValuePair> form) throws Exception {
        if (form == null || URL == null || URL.trim().length() == 0) return "";
        StringBuilder stringBuilder = new StringBuilder();

        org.apache.http.client.HttpClient client = buildHttpClient();
        HttpPost httpPost = new HttpPost(URL);
        HttpResponse response = null;
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            response = client.execute(httpPost);
            stringBuilder.append(getBody(response.getEntity().getContent()));
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                System.out.println("post mailgun messages ok:" + URL);
            } else {
                System.out.println("Failed to post mailgun,status=" + statusCode);
                throw new RuntimeException("post failed:" + URL);
            }
        } finally {
            if (response != null && response.getEntity() != null) {
                response.getEntity().consumeContent();
            }
        }
        return stringBuilder.toString();
    }

    private static String getBody(InputStream inputStream) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    inputStream));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
                result.append("\n");
            }
            in.close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return result.toString();
    }

    private static HttpClient buildHttpClient() {
        return HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(getAuthProvider())
                .build();
    }

    private static CredentialsProvider getAuthProvider() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials(CREDENTIALS_USER, CREDENTIALS_PASSWORD);
        provider.setCredentials(AuthScope.ANY, credentials);
        return provider;
    }
}
