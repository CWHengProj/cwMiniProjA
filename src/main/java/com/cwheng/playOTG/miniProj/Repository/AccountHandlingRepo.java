package com.cwheng.playOTG.miniProj.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cwheng.playOTG.miniProj.Constant.Constant;
import com.cwheng.playOTG.miniProj.Model.UserRegistration;

import jakarta.servlet.http.HttpSession;


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
            return false;
        }
        template.opsForHash().put("LoginInfo", email, encryptedUser);
        return true;

    }

    public boolean correctCredentials(UserRegistration user) {
        String username = user.getUserName();
        String password = user.getPassword();
        String email = user.getEmail();
        if(template.opsForHash().get("LoginInfo", email)==null){
            return false;
        }
        UserRegistration dbUser = (UserRegistration) template.opsForHash().get("LoginInfo", email);
        String u = dbUser.getUserName();
        String p = dbUser.getPassword();
        String encryptedPassword = org.springframework.data.redis.core.script.DigestUtils.sha1DigestAsHex(password);
        if (username.equals(u) && encryptedPassword.equals(p)){
            return true;
        }
        return false;

    }

    public UserRegistration updateAccount(UserRegistration user) {
        String email = user.getEmail();
        String rawPassword = user.getPassword();
        String encryptedPassword = org.springframework.data.redis.core.script.DigestUtils.sha1DigestAsHex(rawPassword);
        user.setPassword(encryptedPassword);
        template.opsForHash().put("LoginInfo",email,user);
        return (UserRegistration) template.opsForHash().get("LoginInfo", email);
    }

    public UserRegistration getUser(HttpSession httpSession) {
        UserRegistration user = (UserRegistration) httpSession.getAttribute("user");
        String email = user.getEmail();
        return (UserRegistration) template.opsForHash().get("LoginInfo", email);
    }


    
}
