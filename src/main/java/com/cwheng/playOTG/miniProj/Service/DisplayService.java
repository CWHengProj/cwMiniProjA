package com.cwheng.playOTG.miniProj.Service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cwheng.playOTG.miniProj.Constant.Constant;
import com.cwheng.playOTG.miniProj.Model.Post;

@Service
public class DisplayService {
    RestTemplate restTemplate = new RestTemplate();
    public List<Post> getSubredditInfo(String subreddit) {
        //make the exchange with local api
        ResponseEntity<List<Post>> subredditInfo = restTemplate.exchange((Constant.localUrl+subreddit), HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {});
        return subredditInfo.getBody();

    }
}
