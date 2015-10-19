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

import com.projectfinfin.projectfinfin.FloorSearchActivity;
import com.projectfinfin.projectfinfin.Grid.GridViewActivity;
import com.projectfinfin.projectfinfin.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ListViewAdapterDepartment extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapterDepartment(Context context,
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
        TextView Floor;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listviewfloor, parent, false);
        // Get the position
        resultp = data.get(position);

        Floor = (TextView) itemView.findViewById(R.id.FloorNumber);

        Floor.setText(resultp.get(FloorSearchActivity.DepartmentName)+" Floor : "+resultp.get(FloorSearchActivity.FloorNumber));




        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                String send = resultp.get(FloorSearchActivity.Department_ID)+","+resultp.get(FloorSearchActivity.FloorNumber);
                String title = resultp.get(FloorSearchActivity.DepartmentName)+" Floor : "+resultp.get(FloorSearchActivity.FloorNumber);
                Intent i = new Intent(context, GridViewActivity.class);
                i.putExtra("ParamUrl", "?id="+send);
                i.putExtra("title",title);
                context.startActivity(i);


            }
        });
        return itemView;
    }
}
