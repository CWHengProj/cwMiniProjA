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

    //TODO simple encryption for the password before storing the password in the db.

    //TODO to ensure user login works, make sure that the user password matches the encrypted password in the database to allow access


    
}
