package com.cwheng.playOTG.miniProj.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistration {
    @NotBlank(message="This is a required field.")
    @Email(message="Please enter a valid email address.")
    private String email;
    @NotBlank(message="This is a required field.")
    @Size(min=5, max=128, message = "User Name must contain at least 5 characters.")
    private String userName;
    @NotBlank(message="This is a required field.")
    @Size(min=8, max=128, message = "Password must contain at least 8 characters.")
    private String password;
    private String userSubreddits;

    public UserRegistration() {
    }
    

    public UserRegistration(
            @NotBlank(message = "This is a required field.") @Email(message = "Please enter a valid email address.") String email,
            @NotBlank(message = "This is a required field.") @Size(min = 5, max = 128, message = "User Name must contain at least 5 characters.") String userName,
            @NotBlank(message = "This is a required field.") @Size(min = 8, max = 128, message = "Password must contain at least 8 characters.") String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserSubreddits() {
        return userSubreddits;
    }


    public void setUserSubreddits(String userSubreddits) {
        this.userSubreddits = userSubreddits;
    }
    

}
