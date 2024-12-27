package com.cwheng.playOTG.miniProj.Service;

import java.util.ArrayList;
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
    public List<Post> getSubredditInfoFromAPI(String subreddit, Integer posts) {
        ResponseEntity<List<Post>> subredditInfo = restTemplate.exchange((Constant.localUrl+subreddit), HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {});
        List<Post> fullSubreddit = subredditInfo.getBody();
        List<Post> userCustomizedSubreddit = new ArrayList<>();
        for (int i=0; i<posts; i++){
            userCustomizedSubreddit.add(fullSubreddit.get(i));
        }
        return userCustomizedSubreddit;
    }
    public List<Post>getSubredditInfoFromDB(String subreddit,Integer posts) {
        List<Post> fullSubreddit = subRedditRepo.getSubredditInfo(subreddit);
        if (fullSubreddit==null){
            return null;
        }
        posts = Math.min(posts, fullSubreddit.size()); //edge case handling in case the subreddit has less posts than requested
        List<Post> userCustomizedSubreddit = new ArrayList<>();
        for (int i=0; i<posts; i++){
            userCustomizedSubreddit.add(fullSubreddit.get(i));
        }
        return userCustomizedSubreddit;

    }
    public boolean checkifAlreadyCached(String subreddit) {
        return subRedditRepo.checkifAlreadyCached(subreddit);
    }
}
