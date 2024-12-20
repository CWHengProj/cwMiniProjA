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
public class phoneController {
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
        //TODO check if valid username and password
        return "userHome";
    }
    @GetMapping("/userHome")
    public String playGame() {
        //TODO ensure that user is logged in before they can access this page


        return "userHome";
    }
    
        
}
