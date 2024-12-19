package com.cwheng.playOTG.miniProj.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwheng.playOTG.miniProj.Model.User;
import com.cwheng.playOTG.miniProj.Repository.AccountRepo;

import jakarta.validation.Valid;

@Service
public class AccountCreationService {

    @Autowired 
    AccountRepo acRepo;
    public void createNewAccount(User user) {
        acRepo.createNewAccount(user);
    }
    public User deleteAccount(){
        //uses its email address to delete the account from the server
        return null;
    }
    public @Valid User getUserDetails() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserDetails'");
    }
    public boolean correctCredentials(User user) {
        //TODO retrieve user Info from repo, return if user exists.
        return acRepo.correctCredentials(user);
        
    }
    
    
}
