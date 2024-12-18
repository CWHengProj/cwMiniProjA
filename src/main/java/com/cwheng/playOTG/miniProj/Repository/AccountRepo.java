package com.cwheng.playOTG.miniProj.Repository;

import org.springframework.stereotype.Repository;

import com.cwheng.playOTG.miniProj.Model.User;

@Repository
public class AccountRepo {

    public User createNewAccount() {
        // TODO Auto-generated method stub
        User user = new User();
        return user;

    }
    
}
