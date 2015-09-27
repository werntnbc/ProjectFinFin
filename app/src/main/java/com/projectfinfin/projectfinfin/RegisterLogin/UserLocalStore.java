package com.projectfinfin.projectfinfin.RegisterLogin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TNBC's on 24/8/2558.
 */
public class UserLocalStore {

    public  static  final  String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void  storeUserDeta(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.putString("email", user.email);
        spEditor.putInt("age", user.age);
        spEditor.commit();
    }

    public User getLoggedInUser(){
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password" , "");
        String email = userLocalDatabase.getString("email" , "");
        int age = userLocalDatabase.getInt("age", -1);

        User storedUser = new User(username, password, email, age);
        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("loggedIn", false) == true){
            return true;
        }else {
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
