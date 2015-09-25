package com.projectfinfin.projectfinfin;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.projectfinfin.projectfinfin.jsonFeed.JSONfunctions;
import com.projectfinfin.projectfinfin.jsonFeed.ListViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by TNBC's on 25/9/2558.
 */
public class NewsfeedFragment extends Fragment {

    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
   // EditText etSearch;
    View rootView;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
   // ArrayList<String> arr_list;
    private SwipeRefreshLayout mSwipeRefreshLayout = null;

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

    static String url = "http://snappyshop.me/android/QueryPromotion.php?";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        // Get the view from listview_main.xml
        Intent lin = getActivity().getIntent();
        Bundle extra = lin.getExtras();

        if (extra != null) {
            url = (String) extra.get("url");
        } else {
            url = "http://snappyshop.me/android/QueryPromotion.php";
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        //mSwipeRefreshLayout.setColorSchemeColors(0,0,0,0);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refreshing data on server
                new DownloadJSON().execute();
            }
        });

        new DownloadJSON().execute();

        return rootView;
    }



    // DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            if (!mSwipeRefreshLayout.isRefreshing()) {
                mProgressDialog = new ProgressDialog(getActivity());
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
                if(jsonobject.isNull("promotion")) {
                    Log.e("234234", "23423423423423");
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                            dialogBuilder.setMessage("ขออภัยร้านนี้ยังไม่มีข้อมูลโปรโมชั่น");
                            dialogBuilder.setPositiveButton("Ok", null);
                            dialogBuilder.show();

                        }
                    });
                }else {
                    jsonarray = jsonobject.getJSONArray("promotion");

//                if(jsonarray != null && jsonobject.getString("promotion_name").equals(JSONObject.NULL)){
//                    Log.e("234234","23423423423423");
//                }

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
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }


        protected void onPostExecute(Void args) {
            // Locate the listview in listview_main.xml
            listview = (ListView) getActivity().findViewById(R.id.listview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(getActivity(), arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            if (mSwipeRefreshLayout.isRefreshing()) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }


}
