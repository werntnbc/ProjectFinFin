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

    ImageLoader imageLoader = new ImageLoader(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);

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
        // Get the result of picture
        link_img1 = i.getStringExtra("link_img1");

        // Locate the TextViews in singleitemview.xml
        TextView txtname = (TextView) findViewById(R.id.promo_name);
        TextView txtstartdate = (TextView)findViewById(R.id.promo_startdate);
        TextView txtenddate = (TextView)findViewById(R.id.promo_enddate);
        TextView txtlocation = (TextView)findViewById(R.id.promo_location);
        TextView txtlink = (TextView)findViewById(R.id.promo_link);
        TextView txtdes = (TextView)findViewById(R.id.promo_des);

        // Locate the ImageView in singleitemview.xml
        ImageView imgflag = (ImageView) findViewById(R.id.link_img1);

        // Set results to the TextViews
        txtname.setText(promo_name);
        txtstartdate.setText(promo_startdate);
        txtenddate.setText(promo_enddate);
        txtlocation.setText(promo_location);
        txtlink.setText(promo_link);
        txtdes.setText(promo_des);


        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(link_img1, imgflag);
    }
}
