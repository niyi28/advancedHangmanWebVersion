package com.company.BackEndDevelopment.Hangman.GameManager;

import com.company.BackEndDevelopment.Login.DataBase.ManagingDataBase;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

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



    private static void sendRequest(URL url) throws Exception {
        URLConnection yc = url.openConnection();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
        }

    }

    /*public  void toPrintLeaderboard() {
        Map<String, List<String>> data = ManagingDataBase.getUserAndScoresFromDataBase()
        final Object[][] table = new String[4][];
        table[0] = new String[] { "foo", "bar", "baz" };
        table[1] = new String[] { "bar2", "foo2", "baz2" };
        table[2] = new String[] { "baz3", "bar3", "foo3" };
        table[3] = new String[] { "foo4", "bar4", "baz4" };

        for (final Object[] row : table) {
            System.out.format("%15s%15s%15s%n", row);
        }
    }*/
}
