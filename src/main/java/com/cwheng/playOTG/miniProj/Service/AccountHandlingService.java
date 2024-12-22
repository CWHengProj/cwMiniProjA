package com.cwheng.playOTG.miniProj.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwheng.playOTG.miniProj.Model.UserRegistration;
import com.cwheng.playOTG.miniProj.Repository.AccountHandlingRepo;



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
    
    
}
