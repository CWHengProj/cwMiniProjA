package com.cwheng.playOTG.miniProj.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwheng.playOTG.miniProj.Model.User;
import com.cwheng.playOTG.miniProj.Repository.AccountRepo;

@Service
public class AccountCreationService {

    @Autowired 
    AccountRepo acRepo;
    public User createNewAccount() {
        // TODO:
        User user = acRepo.createNewAccount();
        return user;
    }
    public User deleteAccount(){
        //uses its UUID to delete the account from the server
        return null;
    }
    
    
}
