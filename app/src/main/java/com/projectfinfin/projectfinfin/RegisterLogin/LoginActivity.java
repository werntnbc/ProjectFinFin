package com.projectfinfin.projectfinfin.RegisterLogin;

import android.app.AlertDialog;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.NewsfeedActivity;
import com.projectfinfin.projectfinfin.R;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    TextView tvSignupNow;
    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            String Check = (String) extra.get("Register");
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
            dialogBuilder.setMessage("สมัครสมาชิกเรียบร้อย กรุณาเข้าไปที่ E-mail ของท่านเพื่อยืนยันตัวตนเปิดใช้งาน");
            dialogBuilder.setPositiveButton("Ok", null);
            if (Check.equalsIgnoreCase("Success")) {
                dialogBuilder.show();
            }
        }

        // check user login ? if yes then go newsfeeds
        userLocalStore = new UserLocalStore(this);
        if(userLocalStore.getLoggedInUser() == null){
            Log.e("Login Status : ", "null no login");
        }else{
            Intent i = new Intent(getApplicationContext(), NewsfeedActivity.class);
            finish();
            startActivity(i);
        }

        //Click button sign in with email
        Button buttonEmail = (Button) findViewById(R.id.button_email);
        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });
        //Click button sign in with facebook
        Button buttonFacebook = (Button) findViewById(R.id.button_facebook);
        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NewsfeedActivity.class);
                startActivity(i);


            }
        });

        //Click text sign up now!
        /*
        TextView textviewSignup = (TextView)findViewById(R.id.signup_now);
        textviewSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });
        */
        tvSignupNow = (TextView) findViewById(R.id.tvSignInNow);
        tvSignupNow.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSignInNow:
                startActivity(new Intent(this, LoginEmailActivity.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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



    /*public void onConfirmClicked(View v){
        Intent i = new Intent(getApplicationContext(), NewsfeedActivity.class);
        startActivity(i);
    }*/

}
