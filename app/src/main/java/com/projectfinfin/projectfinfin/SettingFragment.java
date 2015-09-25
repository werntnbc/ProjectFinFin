package com.projectfinfin.projectfinfin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.projectfinfin.projectfinfin.RegisterLogin.LoginActivity;
import com.projectfinfin.projectfinfin.RegisterLogin.UserLocalStore;

/**
 * Created by TNBC's on 26/9/2558.
 */
public class SettingFragment extends Fragment {

    Button bLogout;
    EditText etUsername, etPassword, etAge;
    UserLocalStore userLocalStore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        // for listview setting page

        // for listview account
        String[] listAccount = {"Name", "Email", "Password", "Mobile Number", "Notification"};
        CustomAdapter_Setting adapter = new CustomAdapter_Setting(getActivity(), listAccount);
        ListView listviewAccount = (ListView) rootView.findViewById(R.id.listview_account);
        listviewAccount.setAdapter(adapter);

        //for listview social network
        String[] listSocial = {"Facebook", "Twitter", "Tumblr", "Pinterest", "Snapchat"};
        CustomAdapter_Setting adapterSocial = new CustomAdapter_Setting(getActivity(), listSocial);
        ListView listviewSocial = (ListView) rootView.findViewById(R.id.listview_social);
        listviewSocial.setAdapter(adapterSocial);

        //for listview support
        String[] listSupport = {"Feedback", "Terms of Service", "Privacy Policy", "Attribution", "Licenses"};
        CustomAdapter_Setting adapterSupport = new CustomAdapter_Setting(getActivity(), listSupport);
        ListView listviewSupport = (ListView) rootView.findViewById(R.id.listview_support);
        listviewSupport.setAdapter(adapterSupport);

        //Logout
        userLocalStore = new UserLocalStore(getActivity());
        //maybe dont use
        etUsername = (EditText) rootView.findViewById(R.id.etUsername);
        etPassword = (EditText) rootView.findViewById(R.id.etPassword);
        etAge = (EditText) rootView.findViewById(R.id.etAge);

        bLogout = (Button) rootView.findViewById(R.id.bLogout);
        bLogout.setOnClickListener(new buttonClick());


        return rootView;
    }

    public class buttonClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bLogout:
                    userLocalStore.clearUserData();
                    userLocalStore.setUserLoggedIn(false);

                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    break;
            }
        }
    }

    //maybe dont use

//    @Override
//    public void onStart() {
//        super.onStart();
//        //startActivity(new Intent(SettingActivity.this, LoginActivity.class));
//
//        if (authenticate() == true) {
//            //displayUserDetails();
//
//
//        } else {
//            startActivity(new Intent(getActivity(), LoginActivity.class));
//        }
//
//    }


    //maybe dont use

    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }





}
