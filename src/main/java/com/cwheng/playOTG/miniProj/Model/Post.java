package com.cwheng.playOTG.miniProj.Model;

public class Post {
    //an individual post's data   
    private boolean ageRestricted;
    private String subredditName;
    private String postTitle;
    private String selfText;    
    private String url;
    // private String imageLink; //should get from the same variable from images
    public Post() {
    }
    public Post(boolean ageRestricted, String subredditName, String postTitle, String selfText, String url) {
        this.ageRestricted = ageRestricted;
        this.subredditName = subredditName;
        this.postTitle = postTitle;
        this.selfText = selfText;
        this.url = url;
        // this.imageLink = imageLink;
    }
    public boolean isAgeRestricted() {
        return ageRestricted;
    }
    public void setAgeRestricted(boolean ageRestricted) {
        this.ageRestricted = ageRestricted;
    }
    public String getSubredditName() {
        return subredditName;
    }
    public void setSubredditName(String subredditName) {
        this.subredditName = subredditName;
    }
    public String getPostTitle() {
        return postTitle;
    }
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
    public String getSelfText() {
        return selfText;
    }
    public void setSelfText(String selfText) {
        this.selfText = selfText;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    // public String getImageLink() {
    //     return imageLink;
    // }
    // public void setImageLink(String imageLink) {
    //     this.imageLink = imageLink;
    // }
    

}
