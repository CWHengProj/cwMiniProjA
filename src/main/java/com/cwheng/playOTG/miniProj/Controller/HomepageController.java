package com.cwheng.playOTG.miniProj.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cwheng.playOTG.miniProj.Model.Post;
import com.cwheng.playOTG.miniProj.Service.DisplayService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("")
public class HomepageController {
    @Autowired
    DisplayService displayService;
    @GetMapping("/homepage")
    public String getMethodName(@ModelAttribute("subredditList") String [] subredditList, Model model) {
        //iterate through the user selected subreddit lists and display them.
        List<List<Post>> frontPage = new ArrayList<>();
        for (String subreddit : subredditList){
            List<Post> subredditInfo=displayService.getSubredditInfoFromDB(subreddit);
            // for(Post p: subredditInfo){
            //     System.out.println(p.getSelfText());
            //     System.out.println(p.getPostTitle());
            //     System.out.println(p.getSubredditName());
            //     System.out.println(p.getUrl());
            //     System.out.println(p.isAgeRestricted());
            // }
            if((subredditInfo)==null){
                //doesnt exist in db, call new one
                subredditInfo = displayService.getSubredditInfo(subreddit);
            }
            //add them to the model for the view to see
            frontPage.add(subredditInfo);
        }
        model.addAttribute("frontPage", frontPage);
        return "homepage";
    }
    
}
