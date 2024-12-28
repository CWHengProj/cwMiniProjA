package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("")
public class TestController {
    @GetMapping("/test")
    public String getMethodName() {
        return "test";
    }
    
}
