package com.projectfinfin.projectfinfin;

import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.widget.Toast;

import com.projectfinfin.projectfinfin.jsonFeed.JSONfunctions;
import com.projectfinfin.projectfinfin.jsonFeed.ListViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class NewsfeedActivity extends ActionBarActivity {

    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;

    public static String promo_name = "promo_name";
    public static String link_img1 = "link_img1";
    public static String promo_location = "promo_location";
    public static String promo_startdate = "promo_startdate";
    public static String promo_enddate = "promo_enddate";
    public static String promo_link = "promo_link";
    public static String promo_des = "promo_des";
    static String url = "http://snappyshop.me/AndroidQuery/checkPromo/2";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_newsfeed);
        // Execute DownloadJSON AsyncTask
        /*if(savedInstanceState == null){
            Bundle extra = getIntent().getExtras();
            if(extra != null){
                url = extra.getString(url);
            }
        }*/
        Intent lin = getIntent();
        Bundle extra = lin.getExtras();

        if (extra != null) {
            url = (String) extra.get("url");
        } else {
            url = "http://snappyshop.me/android/QueryPromotion.php";
        }

        new DownloadJSON().execute();

        //newfeed AsyncTask werntnbc
        int[] array_res = getImageArray(R.array.my_image_array, R.mipmap.ic_launcher);
        String[] array_string = getStringArray(R.array.my_string_array);

        ListView listView = (ListView) findViewById(R.id.listview);
        //error wait edit
//        listView.setAdapter(new CustomAdapter(getApplicationContext(), android.R.id.text1, array_string, array_res));


    }

    //newfeed AsyncTask for image
    public int[] getImageArray(int resId, int defResId) {
        TypedArray my_image_array = getResources().obtainTypedArray(resId);
        int[] array_res = new int[my_image_array.length()];
        for (int i = 0; i < array_res.length; i++)
            array_res[i] = my_image_array.getResourceId(i, defResId);
        my_image_array.recycle();
        return array_res;
    }
    //newfeed AsyncTask for String
    public String[] getStringArray(int resId) {
        TypedArray my_string_array = getResources().obtainTypedArray(resId);
        String[] array_string = new String[my_string_array.length()];
        for (int i = 0; i < array_string.length; i++)
            array_string[i] = my_string_array.getString(i);
        my_string_array.recycle();
        return array_string;
    }

    // DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(NewsfeedActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Snap Shop");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL(url);

            try {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("promotion");

                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    // Retrive JSON Objects
                    map.put("promo_name", jsonobject.getString("promotion_name"));
                    map.put("promo_startdate", jsonobject.getString("start_date"));
                    map.put("promo_enddate", jsonobject.getString("end_date"));
                    map.put("promo_location", jsonobject.getString("promotion_location"));
                    map.put("promo_link", jsonobject.getString("link"));
                    map.put("promo_des", jsonobject.getString("promotion_des"));
                    map.put("link_img1", jsonobject.getString("img_name"));

                    // Set the JSON Objects into the array
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(NewsfeedActivity.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}
