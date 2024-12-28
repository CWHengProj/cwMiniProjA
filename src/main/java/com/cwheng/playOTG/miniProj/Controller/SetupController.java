package com.cwheng.playOTG.miniProj.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cwheng.playOTG.miniProj.Model.UserRegistration;
import com.cwheng.playOTG.miniProj.Service.AccountHandlingService;
import com.cwheng.playOTG.miniProj.Service.DisplayService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("")
public class SetupController {
    @Autowired
    DisplayService displayService;
    @Autowired
    AccountHandlingService acService;

    @GetMapping("/setup")
    public String chooseSubReddits(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user")==null){
            return "redirect:/login?error="+Error.ACCESS_DENIED;
        }
        UserRegistration user = (UserRegistration) httpSession.getAttribute("user");
        model.addAttribute("user",user);
        return "setup";
    }
    @PostMapping("/setup")
    public String postSetup(@ModelAttribute("user") UserRegistration user, HttpSession httpSession) {
        String rawSubreddits = user.getRawSubreddits();
        String[] subredditList = rawSubreddits.replace("+", ",").split(",");
        Integer posts = user.getPostsToShow();
        for (String subreddit: subredditList){

            if (!displayService.checkifAlreadyCached(subreddit)){
                displayService.getSubredditInfoFromAPI(subreddit,posts);
            }
        }
        UserRegistration updatedUser = (UserRegistration) httpSession.getAttribute("user");
        updatedUser.setRawSubreddits(rawSubreddits);
        updatedUser.setUserSubreddits(subredditList);
        updatedUser.setPostsToShow(posts);
        updatedUser = acService.updateUser(updatedUser);
        httpSession.setAttribute("user", updatedUser);
        String postStr = posts.toString();
        return "redirect:/homepage/"+rawSubreddits+"?postsPerSubreddit="+postStr;
    }

}
