package com.company.BackEndDevelopment.Hangman.GameManager;

import org.springframework.web.util.UriComponentsBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Leaderboard {
    public void publishNewScore(String username, int playerScore) throws Exception {
        String url =  "https://api.telegram.org/bot5067225993:AAFA0p-uucy_zXBbp23XQPJL-xDHKvPqLoU/sendMessage";
        String msg = "New Score By " + username + ": " + playerScore;
        final URL myUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("chat_id", "-1001270088408")
                .queryParam("text", msg)
                .build()
                .toUri()
                .toURL();
        sendRequest(myUrl);
    }

    public static void sendRequest(URL url) throws Exception {
        URLConnection yc = url.openConnection();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
        }

    }
}
