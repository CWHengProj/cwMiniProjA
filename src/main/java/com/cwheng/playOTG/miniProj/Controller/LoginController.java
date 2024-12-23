package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cwheng.playOTG.miniProj.Model.UserRegistration;
import com.cwheng.playOTG.miniProj.Service.AccountHandlingService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("")
public class LoginController {
    @Autowired
    AccountHandlingService ahService; 
    @GetMapping("/login")
    public String userLogin(@ModelAttribute("user") UserRegistration user, Model model) {
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute("user") UserRegistration user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        //Account validation
        if (result.hasErrors() || !ahService.correctCredentials(user)){
            return "login";
        }
        redirectAttributes.addFlashAttribute("user",user);
        return "redirect:/setup";
    }
}