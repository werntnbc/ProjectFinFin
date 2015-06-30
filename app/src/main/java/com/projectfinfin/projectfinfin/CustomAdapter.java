package com.projectfinfin.projectfinfin;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomAdapter extends BaseAdapter {
    //Create all this class for help listview newsfeed page
    Context mContext;
    String[] strName;
    int[] resId;

    public CustomAdapter(Context context, String[] strName, int[] resId){
        this.mContext = context;
        this.strName = strName;
        this.resId = resId;
    }


    @Override
    public int getCount() {
        return strName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.listview_row, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.titleTextView);
        textView.setText(strName[position]);

        ImageView imageView = (ImageView)view.findViewById(R.id.brandImage);
        imageView.setBackgroundResource(resId[position]);

        return view;



    }
}
