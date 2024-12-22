package com.cwheng.playOTG.miniProj.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class SetupController {
    @GetMapping("/setup")
    public String chooseSubReddits(Model model) {
        //TODO: path variables so that it is configurable to a users wanted size (default 8 each)
        //use a form to get user to choose the subreddits they want
        //select from a list of subreddits (top subreddits) or manually add subreddits
        List<String> subredditList = new ArrayList<>();
        model.addAttribute("subredditList",subredditList);
        return "setup";
    }
}
