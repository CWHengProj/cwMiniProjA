package com.cwheng.playOTG.miniProj.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cwheng.playOTG.miniProj.Model.Post;
import com.cwheng.playOTG.miniProj.Model.UserRegistration;
import com.cwheng.playOTG.miniProj.Service.AccountHandlingService;
import com.cwheng.playOTG.miniProj.Service.DisplayService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("")
public class HomepageController {
    @Autowired
    DisplayService displayService;
    @Autowired
    AccountHandlingService acService;
    @GetMapping("/homepage")
    public String getMethodName(Model model,HttpSession httpSession, @ModelAttribute("user") UserRegistration user, RedirectAttributes redirectAttributes) {
        if (httpSession.getAttribute("user")==null){
            return "redirect:/login?error="+ErrorMessages.ACCESS_DENIED;
        }
        UserRegistration userDb = acService.getUser(httpSession);
        //TODO user has to be refreshed from db 
        String [] subredditList =userDb.getUserSubreddits();
        if (subredditList==null){
            redirectAttributes.addFlashAttribute("user",user);
            return "redirect:/setup";
        }
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
