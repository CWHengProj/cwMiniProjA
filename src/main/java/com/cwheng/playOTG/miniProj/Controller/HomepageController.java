package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("")
public class HomepageController {
    @GetMapping("/homepage")
    public String getMethodName(@RequestParam String param) {
        return "dashboard";
    }
    
}
