package com.projectfinfin.projectfinfin;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.projectfinfin.projectfinfin.RegisterLogin.LoginActivity;
import com.projectfinfin.projectfinfin.RegisterLogin.User;
import com.projectfinfin.projectfinfin.RegisterLogin.UserLocalStore;


public class SettingActivity extends ActionBarActivity implements View.OnClickListener {

    Button bLogout;
    EditText etUsername ,etPassword, etAge;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //button back on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // for listview setting page

        // for listview account
        String[] listAccount = {"Name", "Email", "Password", "Mobile Number", "Notification"};
        CustomAdapter_Setting adapter = new CustomAdapter_Setting(getApplicationContext(), listAccount);
        ListView listviewAccount = (ListView) findViewById(R.id.listview_account);
        listviewAccount.setAdapter(adapter);

        //for listview social network
        String[] listSocial = {"Facebook", "Twitter", "Tumblr", "Pinterest", "Snapchat"};
        CustomAdapter_Setting adapterSocial = new CustomAdapter_Setting(getApplicationContext(), listSocial);
        ListView listviewSocial = (ListView) findViewById(R.id.listview_social);
        listviewSocial.setAdapter(adapterSocial);

        //for listview support
        String[] listSupport = {"Feedback", "Terms of Service", "Privacy Policy", "Attribution", "Licenses"};
        CustomAdapter_Setting adapterSupport = new CustomAdapter_Setting(getApplicationContext(), listSupport);
        ListView listviewSupport = (ListView) findViewById(R.id.listview_support);
        listviewSupport.setAdapter(adapterSupport);

        //Logout
        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
        //maybe dont use
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etAge = (EditText)findViewById(R.id.etAge);
    }

    //maybe dont use
    @Override
    protected void onStart() {
        super.onStart();

        if(authenticate() == true){
            displayUserDetails();
        }else{
            startActivity(new Intent(SettingActivity.this, LoginActivity.class));
        }
    }

    //maybe dont use
    private boolean authenticate(){
        return  userLocalStore.getUserLoggedIn();
    }
    //maybe dont use
    private void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();
        etUsername.setText(user.username);
        etPassword.setText(user.password);
        etAge.setText(user.age + "");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
