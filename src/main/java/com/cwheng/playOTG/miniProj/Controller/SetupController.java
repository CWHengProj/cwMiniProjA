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
import com.cwheng.playOTG.miniProj.Service.PostHandlingService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("")
public class SetupController {
    @Autowired
    PostHandlingService phService;

    @GetMapping("/setup")
    public String chooseSubReddits(@ModelAttribute("user") UserRegistration user, Model model) {
        model.addAttribute("user",user);
        return "setup";
    }
    @PostMapping("/setup")
    public String postSetup(@ModelAttribute("user") UserRegistration user, Model model) {
        model.addAttribute("user",user);
        //call the internal api (localhost) and perform an exchange
        phService.storePostsToDB();

        return "redirect:/homepage";
    }
    

}
