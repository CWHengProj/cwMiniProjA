package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cwheng.playOTG.miniProj.Model.User;
import com.cwheng.playOTG.miniProj.Service.AccountCreationService;
import org.springframework.web.bind.annotation.PostMapping;
    
@Controller
@RequestMapping("")
public class gameController {
    @Autowired
    AccountCreationService acService;

    @GetMapping("/aboutGame")
    public String rulePage() {
        return "rulePage"; //either create new character or log in
    }

    @GetMapping("/accountCreation")
    public String createNewAccount() {
        return "accountCreation";
    }
    @PostMapping("/accountCreation")
    public String postMethodName(Model model) {
        //TODO: create the new users here
        //figure out how to do either the Oauth or spring security to create a user.
        //recap on sessions?
        
        User user = acService.createNewAccount();
        model.addAttribute("user", user);
        return null;//leads to the game to start?
    }
    @GetMapping("/playGame")
    public String playGame() {
        //display the stats of user based on their account
        //contain a save button that saves their session before they choose to leave the game
        //have an option to delete account as well?
        return null;
    }
    
        
}
