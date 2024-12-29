package com.cwheng.playOTG.miniProj.Service;
import org.springframework.stereotype.Service;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.cwheng.playOTG.miniProj.Model.Post;
import com.cwheng.playOTG.miniProj.Repository.SubRedditRepo;

import jakarta.json.Json;
import jakarta.json.JsonReader;
import jakarta.json.JsonObject;
import jakarta.json.JsonArray;



@Service
public class RedditAPIService {
    private RestTemplate restTemplate = new RestTemplate();
 
    @Autowired
    SubRedditRepo srRepo;
    @Autowired
    ContentTypeService ctService;
    @Autowired
    MarkdownConverter markdownConverter;
    public List<Post> topPosts(String subreddit, String time, Integer limit){
        String url = String.format("https://oauth.reddit.com/r/%s/top.json?t=%s&limit=%d", subreddit, time, limit);

        List<Post> subredditContent = new ArrayList<>();
        String rawData = restTemplate.getForObject(url, String.class);
        JsonReader jReader = Json.createReader(new StringReader(rawData));  
        JsonObject jObject = jReader.readObject();
        JsonArray jArray = jObject.getJsonObject("data").getJsonArray("children");
        for (int i=0; i<jArray.size(); i++){
            boolean ageRestricted =jArray.get(i).asJsonObject().getJsonObject("data").getBoolean("over_18");
            String subredditName =jArray.get(i).asJsonObject().getJsonObject("data").getString("subreddit");
            String postTitle =jArray.get(i).asJsonObject().getJsonObject("data").getString("title");
            String selfText =jArray.get(i).asJsonObject().getJsonObject("data").getString("selftext");
            String postUrl =jArray.get(i).asJsonObject().getJsonObject("data").getString("url");
            String contentType = ctService.determineContent(postUrl);
            //parsing from markdown to html
            postTitle = markdownConverter.convertMarkdownToHtml(postTitle);
            selfText = markdownConverter.convertMarkdownToHtml(selfText);
            Post post = new Post(ageRestricted,subredditName,postTitle,selfText,postUrl,contentType);
            if (ageRestricted==false){
                subredditContent.add(post);
            }
        }
        srRepo.addtoDB(subreddit,subredditContent);
        return subredditContent;
    }
}
