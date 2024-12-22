package com.cwheng.playOTG.miniProj.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@Service
public class RedditAPIService {
    @Value("${clientId}")
    private String clientId;
    @Value("${clientSecret}")
    private String clientSecret;

    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    RedditAuthenticationService ras;

    public ResponseEntity<String> topPosts(String subreddit, String time, Integer limit){
        String url = String.format("https://oauth.reddit.com/r/%s/top.json?time=%s&limit=%d", subreddit, time, limit);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer"+ras.getToken(clientId,clientSecret));
        headers.set("Accept","application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,entity,String.class);

        if (response!=null){
            return response; //TODO: RAW string, to process
        }
        return response;

    }
}
