package com.projectfinfin.projectfinfin.jsonFeed;

/**
 * Created by haball on 19/8/2558.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.R;

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



        // Locate the TextViews in singleitemview.xml
        TextView txtname = (TextView) findViewById(R.id.promo_name);
        TextView txtstartdate = (TextView)findViewById(R.id.promo_startdate);
        TextView txtenddate = (TextView)findViewById(R.id.promo_enddate);
        TextView txtlocation = (TextView)findViewById(R.id.promo_location);
        TextView txtlink = (TextView)findViewById(R.id.promo_link);
        TextView txtdes = (TextView)findViewById(R.id.promo_des);

        // Locate the ImageView in singleitemview.xml
        ImageView imgflag1 = (ImageView) findViewById(R.id.link_img1);
        ImageView imgflag2 = (ImageView) findViewById(R.id.link_img2);
        ImageView imgflag3 = (ImageView) findViewById(R.id.link_img3);

        // Set results to the TextViews
        txtname.setText(promo_name);
        txtstartdate.setText(promo_startdate);
        txtenddate.setText(promo_enddate);
        txtlocation.setText(promo_location);
        txtlink.setText(promo_link);
        txtdes.setText(promo_des);


        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(link_img1, imgflag1);
        imageLoader.DisplayImage(link_img2, imgflag2);
        imageLoader.DisplayImage(link_img3, imgflag3);


    }
}
