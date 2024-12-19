package com.cwheng.playOTG.miniProj.Repository;

import org.springframework.stereotype.Repository;

import com.cwheng.playOTG.miniProj.Model.User;


@Repository
public class AccountRepo {

    public void createNewAccount(User user) {
        // TODO add to repository
    }

    public boolean userExists(User user) {
        //perform a check if user exists in the database        
    }

    //TODO simple encryption for the password before storing the password in the db.

    //TODO to ensure user login works, make sure that the user password matches the encrypted password in the database to allow access


    
}
