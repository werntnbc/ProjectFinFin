package com.projectfinfin.projectfinfin;

/**
 * Created by TNBC's on 24/8/2558.
 */
public class User {
    String username, password;
    int age;

    public User(String username,String password,int age){
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public  User(String username,String password){
        this.username = username;
        this.password = password;
        this.age = -1;

    }

}
