package com.company.advancedHangman.FrontEndDevelopment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvancedHangmanController {

    @Autowired
    private AdvancedHangmanService service;

    @GetMapping("/createGame")
    public String createGame(@RequestParam(name = "playerName") String playerName) {
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

}
