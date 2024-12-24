package com.cwheng.playOTG.miniProj.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwheng.playOTG.miniProj.Model.UserRegistration;
import com.cwheng.playOTG.miniProj.Repository.AccountHandlingRepo;

import jakarta.servlet.http.HttpSession;



@Service
public class AccountHandlingService {

    @Autowired 
    AccountHandlingRepo ahRepo;
    public boolean createNewAccount(UserRegistration user) {
        return ahRepo.createNewAccount(user);
    }

    public boolean correctCredentials(UserRegistration user) {
        return ahRepo.correctCredentials(user);
    }

    public boolean sessionInUse(UserRegistration user, Set<String> activeSessions) {
        return activeSessions.remove(user.getEmail());
    }   

    public UserRegistration updateUser(UserRegistration user) {
        return ahRepo.updateAccount(user);
    }

    public UserRegistration getUser(HttpSession httpSession) {
        return ahRepo.getUser(httpSession);
    }
    
    
}
