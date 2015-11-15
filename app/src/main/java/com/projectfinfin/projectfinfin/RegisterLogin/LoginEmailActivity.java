package com.projectfinfin.projectfinfin.RegisterLogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.AlertDialogManager;
import com.projectfinfin.projectfinfin.ForgetPassword;
import com.projectfinfin.projectfinfin.NewsfeedActivity;
import com.projectfinfin.projectfinfin.NewsfeedFragment;
import com.projectfinfin.projectfinfin.R;
import com.projectfinfin.projectfinfin.TestActivity;

import junit.framework.Test;


public class LoginEmailActivity extends ActionBarActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUsername , etPassword;
    UserLocalStore userLocalStore;
    TextView tvForget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            String Check = (String) extra.get("forgotpass");
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginEmailActivity.this);
            dialogBuilder.setMessage("ทางระบบได้ส่ง รหัสผ่านใหม่ไปทาง Email ของท่านเรียบร้อยแล้วโปรดตรวจสอบรหัสได้ที่ E-mail ของท่าน");
            dialogBuilder.setPositiveButton("Ok", null);
            if (Check.equalsIgnoreCase("Success")) {
                dialogBuilder.show();
            }
        }

        //button back on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Login
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        bLogin = (Button)findViewById(R.id.bLogin);
        tvForget = (TextView)findViewById(R.id.tvForget);
        tvForget.setOnClickListener(this);

        bLogin.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogin:
                if (isNetworkOnline()) {
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    User user = new User(username , password);
                    authenticate(user);
                    break;
                } else {
                    AlertDialogManager alert = new AlertDialogManager();
                    alert.showAlertDialog(LoginEmailActivity.this,
                            "Internet Connection Error",
                            "ไม่พบการเชื่อมต่ออินเทอร์เน็ต", false);
                }
                break;
            case R.id.tvForget:
                startActivity(new Intent(this, ForgetPassword.class));
                break;
        }
    }

    private void authenticate(User user){
        ServerRequests serverRequests = new ServerRequests(this);
        //serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
        serverRequests.fetchUserDataAsyncTask(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage(){
        if(ServerRequests.Result.equalsIgnoreCase("useractivefalse")){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginEmailActivity.this);
            dialogBuilder.setMessage("ไอดีของท่านยังไม่ได้ยืนยัน ไม่สามารถเข้าสู่ระบบได้กรุณาตรวจ สอบ E-Mail ของท่านเพื่อยืนยัน");
            dialogBuilder.setPositiveButton("Ok", null);
            dialogBuilder.show();
        }else if(ServerRequests.Result.equalsIgnoreCase("wrongpass")){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginEmailActivity.this);
            dialogBuilder.setMessage("รหัสผ่านไม่ถูกต้องกรุณาใหม่ลองอีกครั้ง");
            dialogBuilder.setPositiveButton("Ok", null);
            dialogBuilder.show();
        }else{
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginEmailActivity.this);
            dialogBuilder.setMessage("ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง");
            dialogBuilder.setPositiveButton("Ok", null);
            dialogBuilder.show();
        }
    }

    private void logUserIn(User returnedUser){
        userLocalStore.storeUserDeta(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        Intent i = new Intent(getApplicationContext(), NewsfeedActivity.class);

        startActivity(i);
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

    public boolean isNetworkOnline() {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;

    }
}
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_email, menu);
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
    */



