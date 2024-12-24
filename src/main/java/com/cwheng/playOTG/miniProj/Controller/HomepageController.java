package com.cwheng.playOTG.miniProj.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cwheng.playOTG.miniProj.Model.Post;
import com.cwheng.playOTG.miniProj.Model.UserRegistration;
import com.cwheng.playOTG.miniProj.Service.AccountHandlingService;
import com.cwheng.playOTG.miniProj.Service.DisplayService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("")
public class HomepageController {
    @Autowired
    DisplayService displayService;
    @Autowired
    AccountHandlingService acService;
    @GetMapping("/homepage")
    public String getMethodName(@RequestParam(value="postsPerSubreddit",required =false) Integer posts,Model model,HttpSession httpSession) {
        //by default, use the value in their setup. user can also change the number here in the address bar
        if (httpSession.getAttribute("user")==null){
            return "redirect:/login?error="+ErrorMessages.ACCESS_DENIED;
        }
        UserRegistration userDb = acService.getUser(httpSession);
        if (posts==null){
            posts = userDb.getPostsToShow();
        }
        String [] subredditList =userDb.getUserSubreddits();
        if (subredditList==null){
            return "redirect:/setup";
        }
        List<List<Post>> frontPage = new ArrayList<>();
        for (String subreddit : subredditList){
            List<Post> subredditInfo=displayService.getSubredditInfoFromDB(subreddit,posts);
            if((subredditInfo)==null){
                subredditInfo = displayService.getSubredditInfo(subreddit);
            }
            frontPage.add(subredditInfo);
        }
        model.addAttribute("frontPage", frontPage);
        return "homepage";
    }
    
}
