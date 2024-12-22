package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cwheng.playOTG.miniProj.Model.User;
import com.cwheng.playOTG.miniProj.Service.AccountCreationService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

    
@Controller
@RequestMapping("")
public class frontPageController {
    @Autowired
    AccountCreationService acService;
    @GetMapping("/homePage")
    public String rulePage() {
        return "homePage";
    }
    @GetMapping("/accountCreation")
    public String createAccount(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "signUp";
    }
    @PostMapping("/accountCreation")
    public String saveAccount(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()){
            return "signUp";
        }
        acService.createNewAccount(user);
        return "login";
    }

    @GetMapping("/login")
    public String userLogin(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping("/login")
    public String saveLogin(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        //Account validation
        if (result.hasErrors() || !acService.correctCredentials(user)){
            return "login";
        }
        return "homePage";
    }
    @GetMapping("/about")
    public String tutorial() {
        //TODO: general overview / marketing the application, leads user to sign up or log in

        return "homePage";
    }
    @GetMapping("/search")
    public String chooseSubReddits(Model model) {
        //call the API, display the data in a table
        //TODO: path variables so that it is configurable to a users wanted size (default 5 each)
        //use a form to get the information
        
        // model.addAttribute("userSubreddits",userSubreddits);
        return "dashBoard";
    }
    
    
        
}
