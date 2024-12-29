package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("")
public class TutorialController {
    @GetMapping("/")
    public String getStarted() {

        return "gettingStarted";
    }
    @GetMapping("/howToUse")
    public String getTutorial() {
        return "howtouse";
    }
    
}
