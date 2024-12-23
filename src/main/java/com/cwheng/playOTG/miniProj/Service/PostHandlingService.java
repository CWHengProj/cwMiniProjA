package com.cwheng.playOTG.miniProj.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwheng.playOTG.miniProj.Repository.SubRedditRepo;

@Service
public class PostHandlingService {
    @Autowired
    SubRedditRepo subRedditRepo;

    public void storePostsToDB() {
        // perform an exchange with the local api, process the data and store it in the db
        
    }
}
