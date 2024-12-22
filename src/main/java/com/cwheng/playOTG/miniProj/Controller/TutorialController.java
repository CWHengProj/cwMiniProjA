package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping
public class TutorialController {
    @GetMapping("/gettingStarted")
    public String tutorial() {
        return "gettingStarted";
    }
    
}
