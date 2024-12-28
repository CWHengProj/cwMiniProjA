package com.cwheng.playOTG.miniProj.Service;

import org.springframework.stereotype.Service;

@Service
public class ContentTypeService {
    //sorts the url based on content type - image, video, link
    public String determineContent(String url){
        String imagePattern = "(https?://.*\\.(jpeg|jpg|png|gif|bmp|webp))";
        String videoPattern = "(https?://(www\\.)?(youtube\\.com/watch\\?v=[^&\\s]+|youtu\\.be/[^&\\s]+))|(https?://v\\.redd\\.it/[^\\s]+)";

        if (url.matches(videoPattern)){
            return "video";
        }
        if (url.matches(imagePattern)){
            return "image";
        }
        return null;
    }
}
