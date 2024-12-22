package com.cwheng.playOTG.miniProj.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwheng.playOTG.miniProj.Model.User;
import com.cwheng.playOTG.miniProj.Repository.userRepo;


@Service
public class AccountCreationService {

    @Autowired 
    userRepo acRepo;
    public void createNewAccount(User user) {
        acRepo.createNewAccount(user);
    }

    public boolean correctCredentials(User user) {
        return acRepo.correctCredentials(user);
    }   
    
    
}
