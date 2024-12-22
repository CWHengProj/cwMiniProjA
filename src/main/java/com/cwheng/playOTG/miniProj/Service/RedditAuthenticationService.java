package com.cwheng.playOTG.miniProj.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;

@Service
public class RedditAuthenticationService {
    private RestTemplate restTemplate = new RestTemplate();
    private String token;
    public String getToken(String clientId, String clientSecret){
        if (token!=null){
            return token; 
        }
        String url = "https://wwww.reddit.com/api/v1/access_token";
        String myCredentials = clientId + ":" + clientSecret;

        //encode the string for reddit to accept
        String encodedCredentials = Base64.getEncoder().encodeToString(myCredentials.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Basic" +encodedCredentials);
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        token = response.getBody();
        return token;
        
    }
}
