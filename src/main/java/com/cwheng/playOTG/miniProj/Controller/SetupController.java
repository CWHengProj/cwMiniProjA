package com.cwheng.playOTG.miniProj.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cwheng.playOTG.miniProj.Model.UserRegistration;
import com.cwheng.playOTG.miniProj.Service.DisplayService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("")
public class SetupController {
    @Autowired
    DisplayService displayService;

    @GetMapping("/setup")
    public String chooseSubReddits(@ModelAttribute("user") UserRegistration user, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user")==null){
            return "redirect:/login";
        }
        model.addAttribute("user",user);
        return "setup";
    }
    @PostMapping("/setup")
    public String postSetup(@ModelAttribute("user") UserRegistration user, HttpSession httpSession) {
        //TODO: - logged in user's subreddits to be loaded in, whenever update, update in db the list
        String[] subredditList = user.getUserSubreddits().split(",");
        //call the internal api (localhost) and perform an exchange
        for (String subreddit: subredditList){
            //if subreddit does not already exist, call the api
            if (!displayService.checkifAlreadyCached(subreddit)){
                displayService.getSubredditInfo(subreddit);
            }
        }
        httpSession.setAttribute("subredditList",subredditList);
        return "redirect:/homepage";
    }

}
