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
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("")
public class HomepageController {
    @Autowired
    DisplayService displayService;
    @Autowired
    AccountHandlingService acService;
    @GetMapping("/homepage/{categories}")
    public String getMethodName(@PathVariable(name= "categories", required=false) String categories,@RequestParam(value="postsPerSubreddit",required =false) Integer posts,Model model,HttpSession httpSession) {
        if (httpSession.getAttribute("user")==null){
            return "redirect:/login?error="+ErrorMessages.ACCESS_DENIED;
        }
        //gets the info from db using the session email address
        UserRegistration userDb = acService.getUser(httpSession);
        //checks the users subreddit list from their profile
        System.out.println(categories);
        if (categories.equals("null")){
            return "redirect:/setup";
        }
        if (posts==null){
            posts = userDb.getPostsToShow();
        }
        String [] subredditList = categories.replace("+", ",").split(",");
        List<List<Post>> frontPage = new ArrayList<>();
        //iterate through their string array to get each subreddit
        for (String subreddit : subredditList){            
            List<Post> subredditInfo=displayService.getSubredditInfoFromDB(subreddit,posts);
            if((subredditInfo)==null){
                System.out.println("aw snap, no cache! calling api now..");
                subredditInfo = displayService.getSubredditInfoFromAPI(subreddit,posts);
            }
            frontPage.add(subredditInfo);
        }
        model.addAttribute("frontPage", frontPage);
        return "homepage";
    }
    
}
