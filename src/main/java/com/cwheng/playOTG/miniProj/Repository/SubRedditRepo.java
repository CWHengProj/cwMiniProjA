package com.cwheng.playOTG.miniProj.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cwheng.playOTG.miniProj.Constant.Constant;
import com.cwheng.playOTG.miniProj.Model.Post;

@Repository
public class SubRedditRepo {

    @Autowired
    @Qualifier(Constant.template01)
    RedisTemplate<String, Object> template;
    public void addtoDB(String subreddit, List<Post> subredditContent) {
        template.opsForHash().put("redditPosts", subreddit, subredditContent);
    }

    
    //TODO save resources, update the db if the last time it was called was more than a day ago
    public List<Post> getSubredditInfo(String subreddit) {
        List<Post> userFrontPage= (List<Post>) template.opsForHash().get("redditPosts", subreddit);
        return userFrontPage;
    }


    public boolean checkifAlreadyCached(String subreddit) {
        return template.opsForHash().hasKey("redditPosts",subreddit);
    }
    
}
