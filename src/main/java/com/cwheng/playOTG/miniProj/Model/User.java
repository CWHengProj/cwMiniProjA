package com.cwheng.playOTG.miniProj.Model;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {
    @NotBlank(message="This is a required field.")
    @Email(message="Please enter a valid email address.")
    private String email;
    //TODO check if email already exists before adding to the database.
    @NotBlank(message="This is a required field.")
    @Size(min=5, max=128, message = "User Name must contain at least 5 characters.")
    private String userName;
    @NotBlank(message="This is a required field.")
    @Size(min=8, max=128, message = "User Name must contain at least 8 characters.")
    private String password; //TODO: password has to be hashed before we cache it
    private List<Device> wishList;

}
