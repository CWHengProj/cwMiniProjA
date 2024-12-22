package com.cwheng.playOTG.miniProj.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cwheng.playOTG.miniProj.Constant.Constant;
import com.cwheng.playOTG.miniProj.Model.User;


@Repository
public class userRepo {
    @Autowired
    @Qualifier(Constant.template01)
    RedisTemplate<String, Object> template;
    public void createNewAccount(User user) {
        String email = user.getEmail();
        template.opsForHash().put("emails", email, user);

    }

    public boolean correctCredentials(User user) {
        String email = user.getEmail();
        user = (User) template.opsForHash().get("emails", email);
        if (user==null){
            return false;
        }
        //TODO: check if the password is correct
        return true;
        //TODO: perform a check if user exists in the database
              
    }


    
}
