package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cwheng.playOTG.miniProj.Model.UserRegistration;
import com.cwheng.playOTG.miniProj.Service.AccountHandlingService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("")
public class LoginController {
    @Autowired
    AccountHandlingService ahService; 
    @Autowired
    MessageSource messageSource;

    @GetMapping("/login")
    public String userLogin(@RequestParam(value="error",required=false) ErrorMessages error,@ModelAttribute("user") UserRegistration user, Model model) {
        if (error!=null){
            String errorMessage = messageSource.getMessage("error."+error,null, null);
            model.addAttribute("error",errorMessage);
        }
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute("user") UserRegistration user, BindingResult result, Model model, HttpSession httpSession) {
        //Account validation
        if (result.hasErrors() || !ahService.correctCredentials(user)){
            return "redirect:/login?error="+ErrorMessages.INVALID_CREDENTIALS;
        }
        httpSession.setAttribute("user", user);
        return "redirect:/homepage";
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";    
    }
    
}
