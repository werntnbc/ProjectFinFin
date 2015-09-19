package com.projectfinfin.projectfinfin;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;


import com.projectfinfin.projectfinfin.jsonFeed.ImageLoader;
import com.projectfinfin.projectfinfin.jsonFeed.JSONfunctions;
import com.projectfinfin.projectfinfin.jsonFeed.ListViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class NewsfeedActivity extends AppCompatActivity {

    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    EditText etSearch;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    ArrayList<String> arr_list;
    //tool bar
    Toolbar toolbar;
    DrawerLayout drawerLayout;
   android.support.v7.app.ActionBarDrawerToggle drawerToggle;
    CoordinatorLayout rootLayout;


    public static String promo_name = "promo_name";
    public static String promo_location = "promo_location";
    public static String promo_startdate = "promo_startdate";
    public static String promo_enddate = "promo_enddate";
    public static String promo_link = "promo_link";
    public static String logo_pic = "logo_pic";
    public static String promo_des = "promo_des";
    public static String link_img1 = "link_img1";
    public static String link_img2 = "link_img2";
    public static String link_img3 = "link_img3";
    static String url = "http://snappyshop.me/AndroidQuery/checkPromo/2";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_newsfeed);
        Intent lin = getIntent();
        Bundle extra = lin.getExtras();

        if (extra != null) {
            url = (String) extra.get("url");
        } else {
            url = "http://snappyshop.me/android/QueryPromotion.php";
        }

        new DownloadJSON().execute();

        //newfeed AsyncTask werntnbc
        final int[] array_res = getImageArray(R.array.my_image_array, R.mipmap.ic_launcher);
        final String[] array_string = getStringArray(R.array.my_string_array);

        ListView listView = (ListView) findViewById(R.id.listview);
        //error wait edit
//        listView.setAdapter(new CustomAdapter(getApplicationContext(), android.R.id.text1, array_string, array_res));

//search
        etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //ArrayList<String> src_list = new ArrayList<String>();
                ArrayList<HashMap<String, String>> src_list = new ArrayList<HashMap<String, String>>();
                int textlength = etSearch.getText().length();
                for (int i = 0; i < arraylist.size(); i++) {
                    try {
                        String storeName = arraylist.get(i).get("promo_name").toString();
                        if (etSearch.getText().toString().equalsIgnoreCase(storeName.substring(0, textlength))) {
                            // .equalsIgnoreCase(arraylist.get(i).subSequence(0, textlength) .toString())) {
                            //.subSequence(0, textlength).toString())) {
                            // src_list.add(arraylist.get(i));
                            src_list.add(arraylist.get(i));
                        }
                    } catch (Exception e) {
                    }
                }
                listview.setAdapter(new ArrayAdapter<>(NewsfeedActivity.this, android.R.layout.simple_list_item_1, src_list));
                //  adapter.notifyDataSetChanged();
            }

            public void beforeTextChanged(CharSequence s, int start
                    , int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
        //tool bar
        initToolbar();
        initInstances();
    }
    //tool bar
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initInstances() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);

        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
                    map.put("logo_pic", jsonobject.getString("member_avatar"));
                    map.put("link_img1", jsonobject.getString("img_name1"));
                    map.put("link_img2", jsonobject.getString("img_name2"));
                    map.put("link_img3", jsonobject.getString("img_name3"));
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

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

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
