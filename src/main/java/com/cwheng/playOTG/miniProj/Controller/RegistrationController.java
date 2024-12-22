package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cwheng.playOTG.miniProj.Model.UserRegistration;
import com.cwheng.playOTG.miniProj.Service.AccountHandlingService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


    
@Controller
@RequestMapping("")
public class RegistrationController{
    @Autowired
    AccountHandlingService acService;
    @GetMapping("/accountCreation")
    public String createNewAccount(Model model) {
        UserRegistration user = new UserRegistration();
        model.addAttribute("user",user);
        return "signUp";
    }
    @PostMapping("/accountCreation")
    public String postNewAccount(@Valid @ModelAttribute("user") UserRegistration user, BindingResult result) {
        if (result.hasErrors()){
            return "signUp";
        }
        if(acService.createNewAccount(user)){
            return "login";
        }
        //TODO add error message saying that user already   
        return "signUp";
    }

    
        
}
