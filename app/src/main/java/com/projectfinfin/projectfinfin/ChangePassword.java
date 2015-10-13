package com.projectfinfin.projectfinfin;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.RegisterLogin.LoginEmailActivity;
import com.projectfinfin.projectfinfin.RegisterLogin.User;
import com.projectfinfin.projectfinfin.RegisterLogin.UserLocalStore;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

public class ChangePassword extends AppCompatActivity {
    String Result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        //button back on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UserLocalStore userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUser();
        final String Username = user.username;
        final EditText etOldPassword = (EditText) findViewById(R.id.etOldPassword);
        final EditText etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        final EditText etcfPassword = (EditText) findViewById(R.id.etcfPassword);

        Button buttonForgot = (Button) findViewById(R.id.bSubmit);
        buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OldPassword = etOldPassword.getText().toString();
                String NewPassword = etNewPassword.getText().toString();
                String CFPassword = etcfPassword.getText().toString();
                boolean check = true;

                if(OldPassword.length() < 4){
                    etOldPassword.setError("ต้องมีความยาวมากกว่า 4 ตัวอักษร");
                    check = false;
                }else if(NewPassword.length() < 4){
                    etNewPassword.setError("ต้องมีความยาวมากกว่า 4 ตัวอักษร");
                    check = false;
                }else if(CFPassword.length() < 4){
                    etcfPassword.setError("ต้องมีความยาวมากกว่า 4 ตัวอักษร");
                    check = false;
                }
                if(!NewPassword.equalsIgnoreCase(CFPassword)){
                    etcfPassword.setError("รหัสผ่านใหม่ของสองช่องไม่ตรงกัน");
                    etNewPassword.setError("รหัสผ่านใหม่ของสองช่องไม่ตรงกัน");
                    check = false;
                }

                if (check) {
                    new ForgotPassHttp(OldPassword,NewPassword,Username).execute();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_password, menu);
        return true;
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


    public class ForgotPassHttp extends AsyncTask<String, Void, String> {
        String OldPassword = null;
        String NewPassword = null;
        String Username = null;

        public ForgotPassHttp(String OldPassword,String NewPassword,String Username) {
            this.OldPassword = OldPassword;
            this.NewPassword = NewPassword;
            this.Username = Username;
        }

        @Override
        protected String doInBackground(String... params) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("OldPassword", OldPassword));
            dataToSend.add(new BasicNameValuePair("NewPassword", NewPassword));
            dataToSend.add(new BasicNameValuePair("Username", Username));
            //Log.e("Check before Post", Email + "");

            HttpParams httpRequestParams = getHttpRequestParams();
            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost("http://snappyshop.me/AndroidScript/ChangePassword");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                Result = EntityUtils.toString(entity);
                Log.e("Resulttttttttttttt ", Result);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return Result;
        }

        protected void onPostExecute(String result) {
            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ChangePassword.this);
            dialogBuilder.setPositiveButton("Ok", null);
            Log.e("Return ", Result);
            if(Result.equals("oldpassnotcorrect")){
                dialogBuilder.setMessage("รหัสผ่านเดิมไม่ถูกต้องกรุณาลองใหม่อีกครั้ง");
                dialogBuilder.show();
            }else if(Result.equals("updatecomplete")){
                dialogBuilder.setMessage("ทำการเปลี่ยนรหัสผ่านเรียบร้อย หน้าต่างนี้จะปิดเองใน 3 วิ");
                final AlertDialog alert = dialogBuilder.create();
                final Handler handler = new Handler();
                alert.show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(ChangePassword.this, NewsfeedActivity.class);
                        startActivity(i);
                        alert.cancel();
                    }
                }, 3000);

            }else if(Result.equals("samepass")){
                dialogBuilder.setMessage("รหัสที่ท่านเปลี่ยนเหมือนกับรหัสเดิมที่ใช้อยู่กรุณาเลือกรหัสอื่นแล้วลองใหม่อีกครั้ง");
                dialogBuilder.show();
            }
            //Log.e("RETURN", Result);
        }
        private HttpParams getHttpRequestParams() {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 15);
            HttpConnectionParams.setSoTimeout(httpRequestParams, 1000 * 15);
            return httpRequestParams;
        }
    }
}
