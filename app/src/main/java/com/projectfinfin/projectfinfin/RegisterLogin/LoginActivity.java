package com.projectfinfin.projectfinfin.RegisterLogin;

import android.app.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.appevents.AppEventsLogger;
import com.google.android.gcm.GCMRegistrar;
import com.projectfinfin.projectfinfin.NewsfeedActivity;
import com.projectfinfin.projectfinfin.R;
import com.projectfinfin.projectfinfin.ServerUtilities;

import static com.projectfinfin.projectfinfin.CommonUtilities.SENDER_ID;


public class LoginActivity extends ActionBarActivity {

    TextView tvSignupNow;
    UserLocalStore userLocalStore;
    AsyncTask<Void, Void, Void> mRegisterTask;
    public static String name,email = "UnRegister";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            String logout = (String) extra.get("Logout");
            String Check = (String) extra.get("Register");
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
            dialogBuilder.setMessage("สมัครสมาชิกเรียบร้อย กรุณาเข้าไปที่ E-mail ของท่านเพื่อยืนยันตัวตนเปิดใช้งาน");
            dialogBuilder.setPositiveButton("Ok", null);

            if(Check != null){
                if (Check.equalsIgnoreCase("Success")) {
                    dialogBuilder.show();
                }
            }

            if(logout != null){
                if (logout.equalsIgnoreCase("true")) {
                    dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    dialogBuilder.setMessage("ออกจากระบบเรียบร้อยแล้ว");
                    dialogBuilder.setPositiveButton("Ok", null);
                    dialogBuilder.show();
                }
            }

        }

        // remember GCM ID
        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        final String regId = GCMRegistrar.getRegistrationId(this);
        Log.d("GCM TEST LOGIN ", regId+"");
        if (regId.equals("")) {
            // Registration is not present, register now with GCM
            GCMRegistrar.register(this, SENDER_ID);
        } else {
            // Device is already registered on GCM
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                // Skips registration.
                //Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
                Log.e("GCM","Already registered");
            } else {
                // Try to register again, but not in the UI thread.
                // It's also necessary to cancel the thread onDestroy(),
                // hence the use of AsyncTask instead of a raw thread.
                final Context context = this;
                mRegisterTask = new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        // Register on our server
                        // On server creates a new user
                        ServerUtilities.register(context, "UnRegister", "unRegister@gmail.com", regId);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        mRegisterTask = null;
                    }

                };
                mRegisterTask.execute(null, null, null);
            }
        }


        // check user login ? if yes then go newsfeeds
        userLocalStore = new UserLocalStore(this);
        if(userLocalStore.getLoggedInUser() == null){
            Log.e("Login Status : ", "null no login");
        }else{
            Intent i = new Intent(getApplicationContext(), NewsfeedActivity.class);
            startActivity(i);
            finish();
        }

        //Click button sign up
        Button buttonSignup = (Button) findViewById(R.id.button_signupnow);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });

        //Click button login in with email
        Button buttonEmail = (Button) findViewById(R.id.button_email);
        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginEmailActivity.class);
                startActivity(i);
            }
        });


        //Click button sign in with facebook
//        Button buttonFacebook = (Button) findViewById(R.id.button_facebook);
//        buttonFacebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), NewsfeedActivity.class);
//                startActivity(i);
//
//
//            }
//        });

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

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
    /*public void onConfirmClicked(View v){
        Intent i = new Intent(getApplicationContext(), NewsfeedActivity.class);
        startActivity(i);
    }*/

}
