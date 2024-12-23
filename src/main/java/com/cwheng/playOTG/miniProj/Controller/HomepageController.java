package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cwheng.playOTG.miniProj.Model.UserRegistration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("")
public class HomepageController {
    @GetMapping("/homepage")
    public String getMethodName(@ModelAttribute("user") UserRegistration user, Model model) {
        model.addAttribute("user",user);
        return "homepage";
    }
    
}
