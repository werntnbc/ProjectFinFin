package com.projectfinfin.projectfinfin;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.projectfinfin.projectfinfin.RegisterLogin.User;
import com.projectfinfin.projectfinfin.RegisterLogin.UserLocalStore;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by Ha on 11/16/2015.
 */
public class ContactUs extends Fragment {
    TextView FeedbackTitle;
    EditText FeedbackDetail;
    Button FeedbackSubmit;
    RelativeLayout RL;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contactus, container, false);

        //for listview support
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Contact Us");
        String[] listSupport = {"Email:    snapshopteam@gmail.com", "Write a Feedback"};
        CustomAdapter_Setting adapterSupport = new CustomAdapter_Setting(getActivity(), listSupport);
        final ListView listview = (ListView) rootView.findViewById(R.id.listview_aboutUs);
        listview.setAdapter(adapterSupport);

        FeedbackTitle = (TextView) rootView.findViewById(R.id.FeedBackTitle);
        FeedbackDetail = (EditText) rootView.findViewById(R.id.FeedbackForm);
        FeedbackSubmit = (Button) rootView.findViewById(R.id.FeedbackSubmit);
        RL = (RelativeLayout) rootView.findViewById(R.id.relativeID);
        UserLocalStore userLocalStore = new UserLocalStore(getActivity());
        User user = userLocalStore.getLoggedInUser();
        final String username = user.username;


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        emailIntent.setType("vnd.android.cursor.item/email");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"snapshopteam@gmail.com"});
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Email Subject");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "My email content");
                        startActivity(Intent.createChooser(emailIntent, "Send mail by Snapshop"));
                        break;
                    case 1:
                        toggleView();
                        break;
                }
            }
        });

        FeedbackSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SendFeedBack(username).execute();
            }
        });

        return rootView;
    }

    public void toggleView() {
        if (FeedbackTitle.getVisibility() == View.GONE && FeedbackDetail.getVisibility() == View.GONE) {
            FeedbackTitle.setVisibility(View.VISIBLE);
            FeedbackDetail.setVisibility(View.VISIBLE);
            FeedbackSubmit.setVisibility(View.VISIBLE);
        } else if (FeedbackTitle.getVisibility() == View.VISIBLE && FeedbackDetail.getVisibility() == View.VISIBLE) {
            FeedbackTitle.setVisibility(View.GONE);
            FeedbackDetail.setVisibility(View.GONE);
            FeedbackSubmit.setVisibility(View.GONE);
        }
    }

    public class SendFeedBack extends AsyncTask<String, Void, String> {
        String username = null;
        String message = null;

        public SendFeedBack(String username) {
            this.username = username;
            this.message = FeedbackDetail.getText().toString();

        }

        protected void OnPreExecute() {
            super.onPreExecute();
            ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setTitle("Snap Shop");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            final String url = "http://www.snappyshop.me/android/sendFeedBack.php";

            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormEncodingBuilder()
                        .add("message",message)
                        .add("username", username)
                        .build();
                Request request = new Request.Builder().url(url).post(formBody).build();
                Response response = client.newCall(request).execute();
                return ""+response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Snackbar snackbar = null;
            if(result.equalsIgnoreCase("successfully")){
                snackbar = Snackbar.make(RL, "ส่งข้อมูลแล้วเรียบร้อยแล้ว ขอบพระคุณสำหรับคำแนะนำ", Snackbar.LENGTH_LONG);
            }else{
                snackbar = Snackbar.make(RL, "การส่งข้อมูลผิดพลาดกรุณาลองใหม่อีกครั้ง", Snackbar.LENGTH_LONG);
            }
            snackbar.show();
        }
    }
}
