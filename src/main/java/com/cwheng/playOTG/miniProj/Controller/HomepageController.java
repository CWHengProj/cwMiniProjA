package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("")
public class HomepageController {
    @GetMapping("/homepage")
    public String getMethodName(@ModelAttribute("subredditList") String [] subredditList, Model model) {
        model.addAttribute("subredditList", subredditList);
        return "homepage";
    }
    
}
