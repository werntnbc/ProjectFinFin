package com.projectfinfin.projectfinfin.RegisterLogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projectfinfin.projectfinfin.R;


public class SignupActivity extends ActionBarActivity implements View.OnClickListener {

    Button buttonRegister;
    EditText UserNameEditText, PasswordEditText, ConfirmPasswordEditText, AgeEditText,emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //button back on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Register
        UserNameEditText = (EditText) findViewById(R.id.etUsername);
        PasswordEditText = (EditText) findViewById(R.id.etPassword);
        ConfirmPasswordEditText = (EditText) findViewById(R.id.etcfPassword);
        AgeEditText = (EditText) findViewById(R.id.etAge);
        emailEditText = (EditText) findViewById(R.id.etEmail);
        buttonRegister = (Button) findViewById(R.id.bRegister);

        buttonRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        final String Username = UserNameEditText.getText().toString();
        final String Password = PasswordEditText.getText().toString();
        final String ConfirmPassword = ConfirmPasswordEditText.getText().toString();
        int age = 1;
        if(AgeEditText.getText().toString().length() > 0){
            age = Integer.parseInt(AgeEditText.getText().toString());
        }

        Log.e("GGGGGGGGGGGGGGGGGG", Password + " / " + ConfirmPassword);

            switch (v.getId()) {
                case R.id.bRegister:

                    if(!isValidUsername(Username)){
                        UserNameEditText.setError("Username must have 6 character or more.");
                        break;
                    }
                    if(!isValidPassword(Password, ConfirmPassword)){
                        ConfirmPasswordEditText.setError("Password doesn't match.");
                        break;
                    }
                    if(AgeEditText.getText().toString().length() == 0){
                        AgeEditText.setError("Cannot empty");
                        break;
                    }
                    if(age > 80){
                        AgeEditText.setError("Age cannot more than 80");
                        break;
                    }
                    if(age < 0){
                        AgeEditText.setError("Age cannot be null");
                        break;
                    }

                    String username = UserNameEditText.getText().toString();
                    String password = PasswordEditText.getText().toString();
                    String email = emailEditText.getText().toString();


                    User user = new User(username, password, email,age );
                    registerUser(user);
                    break;
            }
    }

    private boolean isValidUsername(String UserName){
        if(UserName != null && UserName.length() > 5){
            return true;
        }
        return false;
    }

    private boolean isValidPassword(String Password,String ConfirmPassword){
        if(Password != null && ConfirmPassword != null){
            if(Password.equalsIgnoreCase(ConfirmPassword)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    private void registerUser(User user){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                //startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                if(ServerRequests.Result.equalsIgnoreCase("successfully")){
                    Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                    i.putExtra("Register","Success");
                    startActivity(i);
                }else if(ServerRequests.Result.equalsIgnoreCase("userduplicate")){
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignupActivity.this);
                    dialogBuilder.setMessage("Username ถูกใช้งานไปแล้วกรุณาเปลี่ยนชื่ออื่น");
                    dialogBuilder.setPositiveButton("Ok", null);
                    UserNameEditText.setText("");
                    dialogBuilder.show();
                }else if(ServerRequests.Result.equalsIgnoreCase("emailduplicate")){
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignupActivity.this);
                    dialogBuilder.setMessage("Email ถูกใช้งานไปแล้วกรุณาใช้อีเมลอื่นหรือหากท่านจำรหัสไม่ได้โปรดเข้าเมนูลืมรหัสผ่าน");
                    dialogBuilder.setPositiveButton("Ok", null);
                    emailEditText.setText("");
                    dialogBuilder.show();
                }else{
                    PasswordEditText.setText("");
                    ConfirmPasswordEditText.setText("");
                    AgeEditText.setText("");
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
