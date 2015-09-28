package com.projectfinfin.projectfinfin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projectfinfin.projectfinfin.RegisterLogin.LoginActivity;
import com.projectfinfin.projectfinfin.RegisterLogin.LoginEmailActivity;

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

public class ForgetPassword extends AppCompatActivity {
    String Result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        final EditText EmailEdit = (EditText) findViewById(R.id.etEmail);
        //button back on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Button buttonForgot = (Button) findViewById(R.id.bSubmit);
        Log.e("GGGGGGGGGGGGGGGGGG", " / ");

        //Click button sign in with facebook
        Button buttonForgot = (Button) findViewById(R.id.bSubmit);
        buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = EmailEdit.getText().toString();

                if (Email.length() > 0) {
                    new ForgotPassHttp(Email).execute();
                } else {
                    Log.e("ELSE", "ELSEELSEELSEELSEELSE");
                }
            }
        });
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
        String Email = null;

        public ForgotPassHttp(String email) {
            this.Email = email;
        }

        @Override
        protected String doInBackground(String... params) {
            //Log.e("ELSE",  "IN" );

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("email", Email));
            //Log.e("Check before Post", Email + "");

            HttpParams httpRequestParams = getHttpRequestParams();
            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost("http://snappyshop.me/AndroidScript");

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
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ForgetPassword.this);
            dialogBuilder.setPositiveButton("Ok", null);

            if(Result.equals("emailwrong")){
                dialogBuilder.setMessage("Email นี้ไม่มีอยู่ในระบบกรุณาลองใหมอีกครั้ง่");
                dialogBuilder.show();
            }else if(Result.equals("sentcomplete")){
                Intent i = new Intent(ForgetPassword.this, LoginEmailActivity.class);
                i.putExtra("forgotpass","Success");
                finish();
                startActivity(i);
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
