package com.cwheng.playOTG.miniProj.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cwheng.playOTG.miniProj.Model.Post;
import com.cwheng.playOTG.miniProj.Service.DisplayService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("")
public class HomepageController {
    @Autowired
    DisplayService displayService;
    @GetMapping("/homepage")
    public String getMethodName(Model model,HttpSession httpSession) {
        if (httpSession.getAttribute("user")==null){
            return "redirect:/login";
        }
        String [] subredditList =(String []) httpSession.getAttribute("subredditList");
        List<List<Post>> frontPage = new ArrayList<>();
        for (String subreddit : subredditList){
            List<Post> subredditInfo=displayService.getSubredditInfoFromDB(subreddit);
            if((subredditInfo)==null){
                subredditInfo = displayService.getSubredditInfo(subreddit);
            }
            frontPage.add(subredditInfo);
        }
        model.addAttribute("frontPage", frontPage);
        return "homepage";
    }
    
}
