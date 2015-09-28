package com.projectfinfin.projectfinfin;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.TextView;


import com.projectfinfin.projectfinfin.Fragments.CameraFragment;
import com.projectfinfin.projectfinfin.Fragments.CategoryFragment;
import com.projectfinfin.projectfinfin.RegisterLogin.User;
import com.projectfinfin.projectfinfin.RegisterLogin.UserLocalStore;
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
    private SwipeRefreshLayout mSwipeRefreshLayout = null;
    //tool bar
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    android.support.v7.app.ActionBarDrawerToggle drawerToggle;
    CoordinatorLayout rootLayout;
    NavigationView navigation;
    UserLocalStore userLocalStore;


    public static String promo_name = "promo_name";
    public static String promo_location = "promo_location";
    public static String promo_storename = "promo_storename";
    public static String promo_startdate = "promo_startdate";
    public static String promo_enddate = "promo_enddate";
    public static String promo_link = "promo_link";
    public static String logo_pic = "logo_pic";
    public static String promo_des = "promo_des";
    public static String link_img1 = "link_img1";
    public static String link_img2 = "link_img2";
    public static String link_img3 = "link_img3";
    static String url = "http://snappyshop.me/android/QueryPromotion.php?";

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
        userLocalStore = new UserLocalStore(this);
        if(userLocalStore.getLoggedInUser() == null){
            Log.e("Login Status : ", "null no login");
        }else{
            User user = userLocalStore.getLoggedInUser();
            TextView username = (TextView) findViewById(R.id.userNameLoginID);
            Log.e("Login Status : ", user.username + "");
            username.setText(user.username);
        }


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        //mSwipeRefreshLayout.setColorSchemeColors(0,0,0,0);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refreshing data on server
                new DownloadJSON().execute();
            }
        });

        new DownloadJSON().execute();
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

        //navigation.getMenu().findItem(R.id.navItem5).setVisible(false); // remove setting nav setting

        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment fragment = null;
                Intent intent = null;

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navItem1:
                        intent = new Intent(getApplicationContext(), NewsfeedActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
//                        fragment = new NewsfeedFragment();
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        transaction.replace(R.id.container, fragment);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
//                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navItem2:
                        fragment = new CategoryFragment();
                        FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                        transaction1.replace(R.id.container, fragment);
                        transaction1.addToBackStack(null);
                        transaction1.commit();
//                        getString(R.string.title_category);
                        getSupportActionBar().setTitle("Category");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navItem3:
                        fragment = new CameraFragment();
                        FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                        transaction2.replace(R.id.container, fragment);
                        transaction2.addToBackStack(null);
                        transaction2.commit();
                        getSupportActionBar().setTitle("Camera Scanner");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navItem4:
                        fragment = new MapFragment();
                        FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                        transaction3.replace(R.id.container, fragment);
                        transaction3.addToBackStack(null);
                        transaction3.commit();
                        getSupportActionBar().setTitle("Map");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navItem5:
                        fragment = new SettingFragment();
                        FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                        transaction4.replace(R.id.container, fragment);
                        transaction4.addToBackStack(null);
                        transaction4.commit();
                        getSupportActionBar().setTitle("Setting");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });


    }

    // DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            if (!mSwipeRefreshLayout.isRefreshing()) {
                mProgressDialog = new ProgressDialog(NewsfeedActivity.this);
                // Set progressdialog title
                mProgressDialog.setTitle("Snap Shop");
                // Set progressdialog message
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(false);
                // Show progressdialog
                mProgressDialog.show();
            }
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
                if(jsonobject.isNull("promotion")){
                    Log.e("array", "null");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(NewsfeedActivity.this);
                            dialogBuilder.setMessage("ขออภัยร้านนี้ยังไม่มีข้อมูลโปรโมชั่น");
                            dialogBuilder.setPositiveButton("Ok", null);
                            dialogBuilder.show();

                        }
                    });
                }else {
                    jsonarray = jsonobject.getJSONArray("promotion");

                    for (int i = 0; i < jsonarray.length(); i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        jsonobject = jsonarray.getJSONObject(i);
                        // Retrive JSON Objects
                        map.put("promo_name", jsonobject.getString("promotion_name"));
                        map.put("promo_startdate", jsonobject.getString("start_date"));
                        map.put("promo_enddate", jsonobject.getString("end_date"));
                        map.put("promo_location", jsonobject.getString("promotion_location"));
                        map.put("promo_storename", jsonobject.getString("store_name"));
                        map.put("promo_link", jsonobject.getString("link"));
                        map.put("promo_des", jsonobject.getString("promotion_des"));
                        map.put("logo_pic", jsonobject.getString("member_avatar"));
                        map.put("link_img1", jsonobject.getString("img_name1"));
                        map.put("link_img2", jsonobject.getString("img_name2"));
                        map.put("link_img3", jsonobject.getString("img_name3"));
                        // Set the JSON Objects into the array
                        arraylist.add(map);
                    }
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
            if (mSwipeRefreshLayout.isRefreshing()) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
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
