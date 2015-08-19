package com.projectfinfin.projectfinfin.jsonFeed;

/**
 * Created by haball on 19/8/2558.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.R;

public class SingleItemView extends Activity {
    // Declare Variables
    String promo_id;
    String promo_name;
    String promo_des;
    String link_img1;
    String position;
    ImageLoader imageLoader = new ImageLoader(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);

        Intent i = getIntent();
        // Get the result of rank
        promo_id = i.getStringExtra("promo_id");
        // Get the result of country
        promo_name = i.getStringExtra("promo_name");
        // Get the result of population
        promo_des = i.getStringExtra("promo_des");
        // Get the result of flag
        link_img1 = i.getStringExtra("link_img1");

        // Locate the TextViews in singleitemview.xml
        TextView txtrank = (TextView) findViewById(R.id.promo_id);
        TextView txtcountry = (TextView) findViewById(R.id.promo_name);
        TextView txtpopulation = (TextView) findViewById(R.id.promo_des);

        // Locate the ImageView in singleitemview.xml
        ImageView imgflag = (ImageView) findViewById(R.id.link_img1);

        // Set results to the TextViews
        txtrank.setText(promo_id);
        txtcountry.setText(promo_name);
        txtpopulation.setText(promo_des);

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(link_img1, imgflag);
    }
}
