package com.projectfinfin.projectfinfin.RegisterLogin;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.NewsfeedActivity;
import com.projectfinfin.projectfinfin.R;
import com.projectfinfin.projectfinfin.TestActivity;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener{

    TextView tvSignupNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            String Check = (String) extra.get("Register");
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
            dialogBuilder.setMessage("Register Successful");
            dialogBuilder.setPositiveButton("Ok", null);
            if(Check.equalsIgnoreCase("Success")){
                dialogBuilder.show();
            }
        }

        //Click button sign in with email
        Button buttonEmail = (Button)findViewById(R.id.button_email);
        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginEmailActivity.class);
                startActivity(i);
            }
        });
        //Click button sign in with facebook
        Button buttonFacebook = (Button)findViewById(R.id.button_facebook);
        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TestActivity.class);
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
        tvSignupNow = (TextView)findViewById(R.id.tvSignupNow);
        tvSignupNow.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSignupNow:
                startActivity(new Intent(this, SignupActivity.class));
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
