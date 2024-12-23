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
        String username = user.getUserName();
        String email = user.getEmail();
        String rawPassword = user.getPassword();
        String encryptedPassword = org.springframework.data.redis.core.script.DigestUtils.sha1DigestAsHex(rawPassword);
        UserRegistration encryptedUser = new UserRegistration(email,username,encryptedPassword);

        if (template.opsForHash().hasKey("LoginInfo", email)){
            //TODO: email already in use
            return false;
        }
        template.opsForHash().put("LoginInfo", email, encryptedUser);
        //TODO: success! sending over to log in
        return true;

    }

    public boolean correctCredentials(UserRegistration user) {
        String username = user.getUserName();
        String password = user.getPassword();
        String email = user.getEmail();
        System.out.printf("%s ,%s ,%s\n",username,password,email);
        if(template.opsForHash().get("LoginInfo", email)==null){
            //TODO: display user does not exist, did you mean to sign up?
            return false;
        }
        UserRegistration dbUser = (UserRegistration) template.opsForHash().get("LoginInfo", email);
        String u = dbUser.getUserName();
        String p = dbUser.getPassword();
        String encryptedPassword = org.springframework.data.redis.core.script.DigestUtils.sha1DigestAsHex(password);
        if (username.equals(u) && encryptedPassword.equals(p)){
            //TODO: pop up success! send to Homepage
            return true;
        }
        //TODO: wrong username or password
        return false;

    }


    
}
