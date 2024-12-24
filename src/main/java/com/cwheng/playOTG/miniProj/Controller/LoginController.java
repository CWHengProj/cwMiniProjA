package com.cwheng.playOTG.miniProj.Controller;

import java.util.HashSet;
import java.util.Set;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cwheng.playOTG.miniProj.Model.UserRegistration;
import com.cwheng.playOTG.miniProj.Service.AccountHandlingService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("")
public class LoginController {
    Set<String> activeSessions= new HashSet<>();
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
    public String postLogin(@Valid @ModelAttribute("user") UserRegistration user, BindingResult result, Model model, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        //Account validation
        if (result.hasErrors() || !ahService.correctCredentials(user)){
            return "redirect:/login?error="+ErrorMessages.INVALID_CREDENTIALS;
        }
        //TODO: why is it currently null?
        if((httpSession.getAttribute("user")!=null)&&ahService.sessionInUse(user,activeSessions)){
            //invalidate the old session
            activeSessions.remove(user.getEmail());
            System.out.println("duplicate found, removing"+activeSessions.toString());
            httpSession.invalidate();
            
        }
        httpSession.setAttribute("user", user);
        activeSessions.add(user.getEmail());
        System.out.println(activeSessions.toString());
        redirectAttributes.addFlashAttribute("user",user);
        return "redirect:/homepage";
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        UserRegistration user =(UserRegistration) httpSession.getAttribute("user");
        String email =user.getEmail();
        activeSessions.remove(email);
        httpSession.invalidate();
        return "redirect:/";    
    }
    
}
