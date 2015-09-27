package com.projectfinfin.projectfinfin.RegisterLogin;

/**
 * Created by TNBC's on 24/8/2558.
 */
public class User {
    public String username, password,email;
    public int age;

    public User(String username,String password,String email,int age){
        this.username = username;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    public User(String username,String password){
        this.username = username;
        this.password = password;
        this.email = "";
        this.age = -1;
    }

}
