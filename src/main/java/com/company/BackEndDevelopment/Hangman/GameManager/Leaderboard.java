package com.company.BackEndDevelopment.Hangman.GameManager;

import com.company.BackEndDevelopment.Hangman.SupplementaryClasses.TableFormatCreator;
import com.company.BackEndDevelopment.Login.DataBase.ManagingDataBase;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;


public class Leaderboard {
    private String smileyResponse;
    /*Telegram API Check: https://core.telegram.org/bots/*/

    public String publishNewScore(String username, int playerScore) throws Exception {
        String url =  "https://api.telegram.org/bot5040566670:AAHuKtZz4820-khAL3AiAwzKGpM0dlny8Rg/sendMessage";
        String msg = "New Score by " + Hangman.toChangeUsernameFirstLetter(username) + ": " + playerScore;
        String leaderboard = "```" + "\n" + msg + "\n" +"smiley response: " + smileyResponse
                + "\n" + toPrintLeaderboard() + "\n" + "```";

        /*"bot5040566670:AAHuKtZz4820-khAL3AiAwzKGpM0dlny8Rg"
        * https://api.telegram.org/bot5040566670:AAHuKtZz4820-khAL3AiAwzKGpM0dlny8Rg/getUpdates*/

        final URL myUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("chat_id", "-1001657687608")
                .queryParam("text", leaderboard)
                .queryParam("parse_mode", "MarkdownV2")
                .build()
                .toUri()
                .toURL();
        sendRequest(myUrl);
        return leaderboard;
    }

    public String publishPhoto() throws Exception {
        String url =  "https://api.telegram.org/bot5040566670:AAHuKtZz4820-khAL3AiAwzKGpM0dlny8Rg/sendPhoto";
        String path = "https://pbs.twimg.com/media/FGl6w60WYAMeSJK?format=jpg&name=small";

        final URL myUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("chat_id", "-1001657687608")
                .queryParam("photo", path )
                .queryParam("caption", "The Formidable Team" )
                .build()
                .toUri()
                .toURL();
        sendRequest(myUrl);
        return path;
    }
    public String publishLocation() throws Exception {
        String url =  "https://api.telegram.org/bot5040566670:AAHuKtZz4820-khAL3AiAwzKGpM0dlny8Rg/sendLocation";
        String sucess = "sucessful";

        final URL myUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("chat_id", "-1001657687608")
                .queryParam("latitude", 52.496887555563994 )
                .queryParam("longitude", 13.36158265808375 )
                .build()
                .toUri()
                .toURL();
        sendRequest(myUrl);
        return sucess;
    }
    public String getTelegramUserMessage() throws Exception {
        String url =  "https://api.telegram.org/bot5040566670:AAHuKtZz4820-khAL3AiAwzKGpM0dlny8Rg/getUpdates";

        final URL myUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .build()
                .toUri()
                .toURL();
        return sendRequest(myUrl);
    }
    public void clearMessage() throws Exception {
        String url =  "https://api.telegram.org/bot5040566670:AAHuKtZz4820-khAL3AiAwzKGpM0dlny8Rg/deleteMessage";
        String success = "successful";
        final URL myUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("chat_id", "-1001657687608")
                .queryParam("message_id", 32)
                .build()
                .toUri()
                .toURL();
                sendRequest(myUrl);
    }

    /*private static String fixGermanUmlauts(String s){
        String result = s;
        String[][] UMLAUT_REPLACEMENTS = { { "Ä", "Ae" }, { "Ü", "Ue" }, { "Ö", "Oe" },
                { "ä", "ae" }, { "ü", "ue" }, { "ö", "oe" }, { "ß", "ss" } };

        for (int i = 0; i < UMLAUT_REPLACEMENTS.length; i++) {
            result = result.replaceAll(UMLAUT_REPLACEMENTS[i][0], UMLAUT_REPLACEMENTS[i][1]);
        }
        return result;
    }*/

    public void setSmileyResponse(int smileyResponse) {
        String y = Character.toString(smileyResponse);
        this.smileyResponse = y;
    }

    private static String sendRequest(URL url) throws Exception {
        URLConnection yc = url.openConnection();
        String lastIndexString = "";
        try(BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()))) {
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                lastIndexString = inputLine;
        }

        return getRequiredStringExcept(lastIndexString);
    }

    private static String getRequiredStringExcept(String lastIndexString){
        List<String> stringArray = List.of(lastIndexString.split(":"));
        int lastIndex = stringArray.size() - 1;
        String lastMessage = stringArray.get(lastIndex);
        String getOnlyStringMessage = List.of(lastMessage.split("}")).get(0);
        String finalString = "";
        for (int letterNum = 1; letterNum < getOnlyStringMessage.length() -1; letterNum ++){
            finalString = finalString + getOnlyStringMessage.charAt(letterNum);
        }
        return finalString;
    }

    public String toPrintLeaderboard() throws IOException {
        return TableFormatCreator.formatAsTable(ManagingDataBase.makingDatabaseAvailableToTelegram());
    }
}
