package com.projectfinfin.projectfinfin;

import android.content.Context;
import android.view.LayoutInflater;
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
    //AsysncTask
    LayoutInflater INFLATER;


    public CustomAdapter(Context context, String[] strName, int[] resId){
        this.mContext = context;
        this.strName = strName;
        this.resId = resId;
        INFLATER = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
       /* LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.listview_newsfeed, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.titleTextView);
        textView.setText(strName[position]);

        ImageView imageView = (ImageView)view.findViewById(R.id.brandImage);
        imageView.setBackgroundResource(resId[position]);

        return view;
        */

        //Test AsyncTask
        ImageView imageView = null;
        if(view == null) {
            view = INFLATER.inflate(R.layout.listview_newsfeed, parent, false);
            imageView = (ImageView)view.findViewById(R.id.brandImage);
        } else {
            imageView = (ImageView)view.findViewById(R.id.brandImage);
            DecodeTask dt1 = (DecodeTask)imageView.getTag(R.id.brandImage);
            if(dt1 != null)
                dt1.cancel(true);
        }

        imageView.setImageBitmap(null);
//        DecodeTask dt2 = new DecodeTask(getContext(), imageView, resId[position]);
//        dt2.execute();
//        imageView.setTag(R.id.brandImage, dt2);

        TextView textView = (TextView)view.findViewById(R.id.titleTextView);
        textView.setText(strName[position]);

        return view;

    }
}
