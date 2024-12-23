package com.cwheng.playOTG.miniProj.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cwheng.playOTG.miniProj.Model.Post;
import com.cwheng.playOTG.miniProj.Model.UserRegistration;
import com.cwheng.playOTG.miniProj.Service.DisplayService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("")
public class SetupController {
    @Autowired
    DisplayService displayService;

    @GetMapping("/setup")
    public String chooseSubReddits(@ModelAttribute("user") UserRegistration user, Model model) {
        model.addAttribute("user",user);
        return "setup";
    }
    @PostMapping("/setup")
    public String postSetup(@ModelAttribute("user") UserRegistration user, RedirectAttributes redirectAttributes) {
        String[] subredditList = user.getUserSubreddits().split(",");
        //call the internal api (localhost) and perform an exchange
        for (String subreddit: subredditList){
            //if subreddit does not already exist, call the api
            if (!displayService.checkifAlreadyCached(subreddit)){
                System.out.println("doesnt exist in db... calling api now");
                List<Post> subredditInfo = displayService.getSubredditInfo(subreddit);
            }
        }
        redirectAttributes.addFlashAttribute("subredditList",subredditList);
        return "redirect:/homepage";
    }

}
