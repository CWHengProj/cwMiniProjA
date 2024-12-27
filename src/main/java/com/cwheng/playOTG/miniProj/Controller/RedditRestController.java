package com.cwheng.playOTG.miniProj.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwheng.playOTG.miniProj.Constant.Constant;
import com.cwheng.playOTG.miniProj.Model.Post;
import com.cwheng.playOTG.miniProj.Service.RedditAPIService;
import com.cwheng.playOTG.miniProj.Service.RedditAuthenticationService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("")
public class RedditRestController {
    @Autowired
    RedditAPIService redditApiService;
    @Autowired
    RedditAuthenticationService ras;
    @GetMapping("/api/{sub}")
    public ResponseEntity<List<Post>> getTopPostsFromReddit(@PathVariable("sub") String sub) {

        //subreddit choices to be appended here
        List<Post> subredditContent = redditApiService.topPosts(sub,Constant.timeframe,Constant.numberOfPosts);
        return ResponseEntity.ok().body(subredditContent);
    }
    
}
