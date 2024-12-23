package com.cwheng.playOTG.miniProj.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cwheng.playOTG.miniProj.Constant.Constant;
import com.cwheng.playOTG.miniProj.Model.UserRegistration;


@Repository
public class AccountHandlingRepo {
    @Autowired
    @Qualifier(Constant.template01)
    RedisTemplate<String, Object> template;
    public boolean createNewAccount(UserRegistration user) {
        String email = user.getEmail();
        //TODO password encryptions

        if (template.opsForHash().hasKey("LoginInfo", email)){
            return false;
        }
        template.opsForHash().put("LoginInfo", email, user);
        return true;

    }

    public boolean correctCredentials(UserRegistration user) {
        String username = user.getUserName();
        String password = user.getPassword();
        String email = user.getEmail();
        System.out.printf("%s ,%s ,%s\n",username,password,email);
        if(template.opsForHash().get("LoginInfo", email)==null){
            System.out.println("hmm");
            return false;
        }
        UserRegistration dbUser = (UserRegistration) template.opsForHash().get("LoginInfo", email);
        String u = dbUser.getUserName();
        String p = dbUser.getPassword();
        if (username.equals(u) && password.equals(p)){
            return true;
        }
        return false;

    }


    
}
