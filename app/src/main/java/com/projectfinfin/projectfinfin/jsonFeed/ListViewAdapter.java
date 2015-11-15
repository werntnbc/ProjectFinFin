package com.projectfinfin.projectfinfin.jsonFeed;

/**
 * Created by haball on 19/8/2558.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.NewsfeedActivity;
import com.projectfinfin.projectfinfin.NewsfeedFragment;
import com.projectfinfin.projectfinfin.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
        imageLoader.clearCache();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView proname;
        TextView startdate;
        TextView enddate;
        TextView prostorename;
        ImageView propic;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        proname = (TextView) itemView.findViewById(R.id.promo_name);
        startdate = (TextView) itemView.findViewById(R.id.promo_startdate);
        enddate = (TextView) itemView.findViewById(R.id.promo_enddate);
        prostorename = (TextView) itemView.findViewById(R.id.promo_storename);
        //    location = (TextView)itemView.findViewById(R.id.promo_location);
        //    prolink = (TextView)itemView.findViewById(R.id.promo_link);
        //    prodes = (TextView)itemView.findViewById(R.id.promo_des);

        // Locate the ImageView in listview_item.xml
        propic = (ImageView) itemView.findViewById(R.id.logo_pic);

        // Capture position and set results to the TextViews

        proname.setText(resultp.get(NewsfeedActivity.promo_name));
        //    location.setText(resultp.get(NewsfeedActivity.promo_location));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = format.parse(resultp.get(NewsfeedActivity.promo_startdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("d MMM yyyy");
        startdate.setText(format.format(newDate));

        format = new SimpleDateFormat("yyyy-MM-dd");
        newDate = null;
        try {
            newDate = format.parse(resultp.get(NewsfeedActivity.promo_enddate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("d MMM yyyy");
        enddate.setText(format.format(newDate));


        //tartdate.setText(resultp.get(NewsfeedActivity.promo_startdate));
        //enddate.setText(resultp.get(NewsfeedActivity.promo_enddate));
        prostorename.setText(resultp.get(NewsfeedActivity.promo_storename));
        //    prolink.setText(resultp.get(NewsfeedActivity.promo_link));
        //    prodes.setText(resultp.get(NewsfeedActivity.promo_des));

        String path = "http://www.snappyshop.me/web/assets/images/avatars/" + resultp.get(NewsfeedActivity.logo_pic);
        //Log.v("Link Image", "" + path);
        //Log.v("Promotion ID", "" + resultp.get(NewsfeedActivity.promo_id));
        //imageLoader.DisplayImage("web/assets/images/avatars/" + resultp.get(NewsfeedActivity.logo_pic), propic);
//        Picasso.with(context).invalidate(path); // cache
        Picasso.with(context)
                .load(path)
                .resize(400,400)
                .centerInside()
//                .memoryPolicy(MemoryPolicy.NO_CACHE)
//                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(propic);


        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data proname
                intent.putExtra("promo_name", resultp.get(NewsfeedActivity.promo_name));
                // Pass all data start date
                intent.putExtra("promo_startdate", resultp.get(NewsfeedActivity.promo_startdate));
                // Pass all data end date
                intent.putExtra("promo_enddate", resultp.get(NewsfeedActivity.promo_enddate));
                // Pass all data location
                intent.putExtra("promo_location", resultp.get(NewsfeedActivity.promo_location));
                // Pass all data store name
                intent.putExtra("promo_storename", resultp.get(NewsfeedActivity.promo_storename));
                // Pass all data link
                intent.putExtra("promo_link", resultp.get(NewsfeedActivity.promo_link));
                // Pass all data description
                intent.putExtra("promo_des", resultp.get(NewsfeedActivity.promo_des));
                // Pass all data propic
                intent.putExtra("link_img1", resultp.get(NewsfeedActivity.link_img1));
                // Pass all data propic
                intent.putExtra("link_img2", resultp.get(NewsfeedActivity.link_img2));
                // Pass all data propic
                intent.putExtra("link_img3", resultp.get(NewsfeedActivity.link_img3));

                intent.putExtra("promotion_id", resultp.get(NewsfeedActivity.promo_id));
                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });
        return itemView;
    }
}
