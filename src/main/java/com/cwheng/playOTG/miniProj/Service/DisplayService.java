package com.cwheng.playOTG.miniProj.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cwheng.playOTG.miniProj.Constant.Constant;
import com.cwheng.playOTG.miniProj.Model.Post;
import com.cwheng.playOTG.miniProj.Repository.SubRedditRepo;

@Service
public class DisplayService {
    @Autowired
    SubRedditRepo subRedditRepo;
    RestTemplate restTemplate = new RestTemplate();
    public List<Post> getSubredditInfo(String subreddit) {
        //make the exchange with local api
        ResponseEntity<List<Post>> subredditInfo = restTemplate.exchange((Constant.localUrl+subreddit), HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {});
        return subredditInfo.getBody();

    }
    public List<Post> getSubredditInfoFromDB(String subreddit) {
        return subRedditRepo.getSubredditInfo(subreddit); 
    }
    public boolean checkifAlreadyCached(String subreddit) {
        return subRedditRepo.checkifAlreadyCached(subreddit);
    }
}
