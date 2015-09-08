package com.projectfinfin.projectfinfin.jsonFeed;

/**
 * Created by haball on 19/8/2558.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.NewsfeedActivity;
import com.projectfinfin.projectfinfin.R;

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
        TextView location;
    //    TextView prolink;
    //    TextView prodes;
        ImageView propic;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        proname = (TextView) itemView.findViewById(R.id.promo_name);
        startdate = (TextView)itemView.findViewById(R.id.promo_startdate);
        enddate = (TextView)itemView.findViewById(R.id.promo_enddate);
        location = (TextView)itemView.findViewById(R.id.promo_location);
    //    prolink = (TextView)itemView.findViewById(R.id.promo_link);
    //    prodes = (TextView)itemView.findViewById(R.id.promo_des);

        // Locate the ImageView in listview_item.xml
        propic = (ImageView) itemView.findViewById(R.id.link_img1);

        // Capture position and set results to the TextViews

        proname.setText(resultp.get(NewsfeedActivity.promo_name));
        location.setText(resultp.get(NewsfeedActivity.promo_location));
        startdate.setText(resultp.get(NewsfeedActivity.promo_startdate));
        enddate.setText(resultp.get(NewsfeedActivity.promo_enddate));
    //    prolink.setText(resultp.get(NewsfeedActivity.promo_link));
    //    prodes.setText(resultp.get(NewsfeedActivity.promo_des));

        // Capture position and set results to the ImageView
        // Passes propic images URL into ImageLoader.class
        imageLoader.DisplayImage(resultp.get(NewsfeedActivity.link_img1), propic);
        // Capture ListView item click
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
                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });
        return itemView;
    }
}
