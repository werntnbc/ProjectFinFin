package com.projectfinfin.projectfinfin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.RegisterLogin.LoginActivity;
import com.projectfinfin.projectfinfin.RegisterLogin.User;
import com.projectfinfin.projectfinfin.RegisterLogin.UserLocalStore;

/**
 * Created by TNBC's on 26/9/2558.
 */
public class SettingFragment extends Fragment {

    Button bLogout , bChangePass;
    EditText etUsername, etPassword, etAge;
    UserLocalStore userLocalStore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        // for listview setting page



        userLocalStore = new UserLocalStore(getActivity());
        String[] listAccount = null;
        if(userLocalStore.getLoggedInUser() == null){
            listAccount = new String[]{"Name : Guest", "Email : Snap-Shop@gmail.com"};
        }else {
            User user = userLocalStore.getLoggedInUser();
            listAccount = new String[]{"Name : "+user.username, "Email : "+user.email};
        }

        // for listview account
        CustomAdapter_Setting adapter = new CustomAdapter_Setting(getActivity(), listAccount);
        ListView listviewAccount = (ListView) rootView.findViewById(R.id.listview_account);
        listviewAccount.setAdapter(adapter);

        //for listview social network
        String[] listSocial = {"Facebook", "Twitter"};
        CustomAdapter_Setting adapterSocial = new CustomAdapter_Setting(getActivity(), listSocial);
        ListView listviewSocial = (ListView) rootView.findViewById(R.id.listview_social);
        listviewSocial.setAdapter(adapterSocial);

        //for listview support
        String[] listSupport = {"Feedback", "Terms of Service", "Privacy Policy"};
        CustomAdapter_Setting adapterSupport = new CustomAdapter_Setting(getActivity(), listSupport);
        ListView listviewSupport = (ListView) rootView.findViewById(R.id.listview_support);
        listviewSupport.setAdapter(adapterSupport);

        bLogout = (Button) rootView.findViewById(R.id.bLogout);
        bChangePass = (Button) rootView.findViewById(R.id.bChangepass);


        if(userLocalStore.getLoggedInUser() == null){
            Log.e("Login Status : ", "null no login");
            bLogout.setVisibility(View.GONE);
            bChangePass.setVisibility(View.GONE);
        }else {
            User user = userLocalStore.getLoggedInUser();
            Log.e("Login Status : ", user.username + "");
        }

        bChangePass.setOnClickListener(new buttonClick());
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
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    i.putExtra("Logout", "true");
                    startActivity(i);
                    getActivity().finish();
                    break;
                case R.id.bChangepass:
                    startActivity(new Intent(getActivity(), ChangePassword.class));
                    break;
            }
        }
    }

    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }





}
