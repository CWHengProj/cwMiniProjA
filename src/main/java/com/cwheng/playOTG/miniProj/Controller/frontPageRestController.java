package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwheng.playOTG.miniProj.Constant.Constant;
import com.cwheng.playOTG.miniProj.Service.RedditAPIService;
import com.cwheng.playOTG.miniProj.Service.RedditAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("")
public class frontPageRestController {
    @Autowired
    RedditAPIService redditApiService;
    @Autowired
    RedditAuthenticationService ras;
    @GetMapping("/api/{sub}")
    public ResponseEntity<String> getTopPostsFromReddit(@PathVariable("sub") String sub) {

        return redditApiService.topPosts(sub,Constant.timeframe,Constant.numberOfPosts);
    }
    
}
