package com.cwheng.playOTG.miniProj.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cwheng.playOTG.miniProj.Constant.Constant;
import com.cwheng.playOTG.miniProj.Model.User;


@Repository
public class AccountRepo {
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
        //check if the password is correct
        return true;
        //perform a check if user exists in the database
              
    }

    //TODO simple encryption for the password before storing the password in the db.

    //TODO to ensure user login works, make sure that the user password matches the encrypted password in the database to allow access


    
}
