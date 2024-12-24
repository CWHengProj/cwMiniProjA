package com.cwheng.playOTG.miniProj.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String chooseSubReddits(@ModelAttribute("user") UserRegistration user, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user")==null){
            return "redirect:/login?error="+ErrorMessages.INVALID_CREDENTIALS;
        }
        model.addAttribute("user",user);
        return "setup";
    }
    @PostMapping("/setup")
    public String postSetup(@ModelAttribute("user") UserRegistration user, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        //TODO: - logged in user's subreddits to be loaded in, whenever update, update in db the list
        String[] subredditList = user.getRawSubreddits().split(",");
        for (String subreddit: subredditList){
            if (!displayService.checkifAlreadyCached(subreddit)){
                displayService.getSubredditInfo(subreddit);
            }
        }
        user.setUserSubreddits(subredditList);
        System.out.println("troubleshooting email: "+user.getEmail());
        System.out.println("troubleshooting rawsubs: "+user.getRawSubreddits());

        UserRegistration updatedUser = acService.updateUser(user);
        redirectAttributes.addFlashAttribute("user",updatedUser);
        return "redirect:/homepage";
    }

}
