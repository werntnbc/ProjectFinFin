package com.projectfinfin.projectfinfin.jsonFeed;

/**
 * Created by haball on 19/8/2558.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.NewsfeedActivity;
import com.projectfinfin.projectfinfin.R;
import com.projectfinfin.projectfinfin.TouchImageView;
import com.squareup.picasso.Picasso;

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

public class SingleItemView extends ActionBarActivity {
    // Declare Variables
    String position;
    String promo_name;
    String promo_startdate;
    String promo_enddate;
    String promo_location;
    String promo_link;
    String promo_des;
    String link_img1;
    String link_img2;
    String link_img3;
    String promo_id;

    ImageLoader imageLoader = new ImageLoader(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);
        //button back on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Intent i = getIntent();

        // Get the result of promotion name
        promo_name = i.getStringExtra("promo_name");
        // Get the result of start date
        promo_startdate = i.getStringExtra("promo_startdate");
        // Get the result of end date
        promo_enddate = i.getStringExtra("promo_enddate");
        // Get the result of location
        promo_location = i.getStringExtra("promo_location");
        // Get the result of link
        promo_link = i.getStringExtra("promo_link");
            // Get the result of description
        promo_des = i.getStringExtra("promo_des");
        // Get the result of picture1
        link_img1 = i.getStringExtra("link_img1");
        // Get the result of picture2
        link_img2 = i.getStringExtra("link_img2");
        // Get the result of picture3
        link_img3 = i.getStringExtra("link_img3");

        promo_id = i.getStringExtra("promotion_id");

        getSupportActionBar().setTitle(i.getStringExtra(("promo_storename")));

        new CountViewPromotion().execute();

        // Locate the TextViews in singleitemview.xml
        TextView txtname = (TextView) findViewById(R.id.promo_name);
        TextView txtstartdate = (TextView) findViewById(R.id.promo_startdate);
        TextView txtenddate = (TextView) findViewById(R.id.promo_enddate);
        TextView txtlocation = (TextView) findViewById(R.id.promo_location);
        TextView txtlink = (TextView) findViewById(R.id.promo_link);
        TextView txtdes = (TextView) findViewById(R.id.promo_des);
        TextView Linktitle = (TextView) findViewById(R.id.linklabel);

        // Locate the ImageView in singleitemview.xml
        ImageView imgflag1 = (ImageView) findViewById(R.id.link_img1);
        ImageView imgflag2 = (ImageView) findViewById(R.id.link_img2);
        ImageView imgflag3 = (ImageView) findViewById(R.id.link_img3);

        // Set results to the TextViews
        txtname.setText(promo_name);
        txtstartdate.setText(promo_startdate);
        txtenddate.setText(promo_enddate);
        txtlocation.setText(promo_location);
        if(promo_link.length() == 0){
            txtlink.setVisibility(View.GONE);
            Linktitle.setVisibility(View.GONE);
        }else{
            txtlink.setText(promo_link);
        }

        txtdes.setText(promo_des);

        Linkify.addLinks(txtlink,Linkify.WEB_URLS);


        // Capture position and set results to theImageView
        // Passes flag images URL into ImageLoader.class

        /*imageLoader.DisplayImage(link_img1, imgflag1);
        imageLoader.DisplayImage(link_img2, imgflag2);
        imageLoader.DisplayImage(link_img3, imgflag3);*/


        if (link_img2.equalsIgnoreCase("web/assets/images/promotion/blank_1.jpg") || link_img2.equalsIgnoreCase("null")) {
            imgflag2.setVisibility(View.GONE);
        }
        if (link_img3.equalsIgnoreCase("web/assets/images/promotion/blank_1.jpg") || link_img3.equalsIgnoreCase("null")) {
            imgflag3.setVisibility(View.GONE);
        }
        Picasso.with(getApplicationContext())
                .load("http://www.snappyshop.me/" + link_img1)
                .resize(500, 500)
                .centerInside()
               .into(imgflag1);
        Picasso.with(getApplicationContext()).
                load("http://www.snappyshop.me/" + link_img2)
                .resize(500, 500)
                .centerInside()
                .into(imgflag2);
        Picasso.with(getApplicationContext()).
                load("http://www.snappyshop.me/" + link_img3)
                .resize(500, 500)
                .centerInside()
                .into(imgflag3);

        imgflag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleItemView.this, DetailsActivity.class);
                ImageView imgflag1 = (ImageView) findViewById(R.id.link_img1);
                int[] screenLocation = new int[2];
                imgflag1.getLocationOnScreen(screenLocation);
                intent.putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", imgflag1.getWidth()).
                        putExtra("height", imgflag1.getHeight()).
                        putExtra("title", "Image 1").
                        putExtra("image", "http://www.snappyshop.me/" + link_img1);
                startActivity(intent);
            }
        });

        imgflag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleItemView.this, DetailsActivity.class);
                ImageView imgflag2 = (ImageView) findViewById(R.id.link_img2);
                int[] screenLocation = new int[2];
                imgflag2.getLocationOnScreen(screenLocation);
                intent.putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", imgflag2.getWidth()).
                        putExtra("height", imgflag2.getHeight()).
                        putExtra("title", "Images 2").
                        putExtra("image", "http://www.snappyshop.me/" + link_img2);
                startActivity(intent);
            }
        });

        imgflag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleItemView.this, DetailsActivity.class);
                ImageView imgflag3 = (ImageView) findViewById(R.id.link_img3);
                int[] screenLocation = new int[2];
                imgflag3.getLocationOnScreen(screenLocation);
                intent.putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", imgflag3.getWidth()).
                        putExtra("height", imgflag3.getHeight()).
                        putExtra("title", "Images 3").
                        putExtra("image", "http://www.snappyshop.me/" + link_img3);
                startActivity(intent);
            }
        });

    }


    public class CountViewPromotion extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("promo_id", promo_id));
            Log.e("Check before Post", promo_id + "");

            HttpParams httpRequestParams = getHttpRequestParams();
            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost("http://snappyshop.me/android/CountViewPromo.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String Result = EntityUtils.toString(entity);
                Log.e("Count View", "Complete" + Result);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private HttpParams getHttpRequestParams() {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 15);
            HttpConnectionParams.setSoTimeout(httpRequestParams, 1000 * 15);
            return httpRequestParams;
        }
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
}
