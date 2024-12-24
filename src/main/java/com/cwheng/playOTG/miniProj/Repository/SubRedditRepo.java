package com.cwheng.playOTG.miniProj.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        template.opsForHash().put(subreddit, subreddit, subredditContent);
        // template.expire(subreddit,Constant.expiryTimeinSeconds,TimeUnit.SECONDS);
        template.expire(subreddit,Constant.expiryTimeinDays,TimeUnit.DAYS);
    }

    
    public List<Post> getSubredditInfo(String subreddit) {
        List<Post> userFrontPage= (List<Post>) template.opsForHash().get(subreddit, subreddit);
        return userFrontPage;
    }


    public boolean checkifAlreadyCached(String subreddit) {
        return template.opsForHash().hasKey("redditPosts",subreddit);
    }
    
}
