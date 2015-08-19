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
        TextView rank;
        TextView country;
        TextView population;
        ImageView flag;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        rank = (TextView) itemView.findViewById(R.id.promo_id);
        country = (TextView) itemView.findViewById(R.id.promo_name);
        population = (TextView) itemView.findViewById(R.id.promo_des);

        // Locate the ImageView in listview_item.xml
        flag = (ImageView) itemView.findViewById(R.id.link_img1);

        // Capture position and set results to the TextViews
        rank.setText(resultp.get(NewsfeedActivity.promo_id));
        country.setText(resultp.get(NewsfeedActivity.promo_name));
        population.setText(resultp.get(NewsfeedActivity.promo_des));
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(resultp.get(NewsfeedActivity.link_img1), flag);
        // Capture ListView item click
        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("promo_id", resultp.get(NewsfeedActivity.promo_id));
                // Pass all data country
                intent.putExtra("promo_name", resultp.get(NewsfeedActivity.promo_name));
                // Pass all data population
                intent.putExtra("promo_des",resultp.get(NewsfeedActivity.promo_des));
                // Pass all data flag
                intent.putExtra("link_img1", resultp.get(NewsfeedActivity.link_img1));
                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });
        return itemView;
    }
}
