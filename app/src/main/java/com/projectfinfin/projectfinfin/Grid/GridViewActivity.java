package com.projectfinfin.projectfinfin.Grid;

/**
 * Created by haball on 18/9/2558.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.projectfinfin.projectfinfin.NewsfeedActivity;
import com.projectfinfin.projectfinfin.NewsfeedFragment;
import com.projectfinfin.projectfinfin.R;

public class GridViewActivity extends ActionBarActivity {
    private static final String TAG = GridViewActivity.class.getSimpleName();

    private GridView mGridView;
    private ProgressBar mProgressBar;

    private GridViewAdapter mGridAdapter;
    private ArrayList<GridItem> mGridData;
    private String FEED_URL = "http://snappyshop.me/android/QueryType.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        //button back on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            FEED_URL = FEED_URL + extra.get("ParamUrl");
        } else {
            FEED_URL = "http://snappyshop.me/android/QueryType.php";
        }

        mGridView = (GridView) findViewById(R.id.gridView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Initialize with empty data
        mGridData = new ArrayList<>();
        mGridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, mGridData);
        mGridView.setAdapter(mGridAdapter);

        //Grid view click event
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Get item at position
                GridItem item = (GridItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(GridViewActivity.this, NewsfeedActivity.class);
                intent.putExtra("url", "http://snappyshop.me/android/QueryPromotion.php?id=" + item.getStore_id());
                intent.putExtra("title",item.getTitle());

                startActivity(intent);
                finish();
            }
        });

        //Start download
        new AsyncHttpTask().execute(FEED_URL);
        mProgressBar.setVisibility(View.VISIBLE);
    }




    //Downloading data asynchronously
    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            try {
                // Create Apache HttpClient
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse httpResponse = httpclient.execute(new HttpGet(params[0]));
                int statusCode = httpResponse.getStatusLine().getStatusCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    String response = streamToString(httpResponse.getEntity().getContent());
                    parseResult(response);
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Lets update UI

            if (result == 1) {
                mGridAdapter.setGridData(mGridData);
            } else {
                Toast.makeText(GridViewActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }

            //Hide progressbar
            mProgressBar.setVisibility(View.GONE);
        }
    }


    String streamToString(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        // Close stream
        if (null != stream) {
            stream.close();
        }
        return result;
    }

    /**
     * Parsing the feed results and get the list
     *
     * @param result
     */
    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            if(response.isNull("promotion")){
                Log.e("sdf","erorr");
                runOnUiThread(new Runnable() {
                    public void run() {
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(GridViewActivity.this);
                        dialogBuilder.setMessage("ขออภัยหมวดหมู่นี้ยังไม่มีข้อมูล");
                        dialogBuilder.setPositiveButton("Ok", null);
                        final Handler handler = new Handler();
                        final AlertDialog alert = dialogBuilder.create();
                        alert.show();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alert.cancel();
                                finish();
                            }
                        }, 1000);

                    }
                });
            }else {
                JSONArray posts = response.optJSONArray("promotion");
                GridItem item;
                for (int i = 0; i < posts.length(); i++) {
                    JSONObject post = posts.optJSONObject(i);
                    String title = post.optString("store_name");
                    String store_id = post.optString("store_id");
                    String member_id = post.optString("member_id");
                    item = new GridItem();
                    item.setTitle(title);
                    item.setStore_id(store_id);
                    item.setMember_id(member_id);
                    String image = post.optString("member_avatar");
                    item.setImage("http://www.snappyshop.me/web/assets/images/avatars/" + image);
                    Log.v("image", "www.snappyshop.me/web/assets/images/avatars/" + image);
                    /*JSONArray attachments = post.getJSONArray("img_name");
                    if (null != attachments && attachments.length() > 0) {
                        JSONObject attachment = attachments.getJSONObject(0);
                        if (attachment != null)
                            item.setImage(attachment.getString("url"));
                    }*/
                    mGridData.add(item);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
