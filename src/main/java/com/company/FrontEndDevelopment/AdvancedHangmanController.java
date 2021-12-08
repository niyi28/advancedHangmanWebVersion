package com.company.FrontEndDevelopment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvancedHangmanController {

    @Autowired
    private AdvancedHangmanService service;

    @GetMapping("/createGame")
    public String createGame(
            @RequestParam(name = "playerName") String playerName,
            @RequestParam(name = "language") int language,
            @RequestParam(name = "difficulty") int difficulty) {
        service.createGame(playerName);
        return "OK";
    }

    @GetMapping("/getGameStatus")
    public String getGameStatus() {
        String response = "";
        response += service.getPlayerName();
        response += "," + service.getWordSize();
        return response;
    }

    @GetMapping("/submitLetter")
    public String submitLetter(@RequestParam(name = "letter") String letter) {
        // do something with the letter
        // return gameStatus
        return "gameStatus";
    }
}
