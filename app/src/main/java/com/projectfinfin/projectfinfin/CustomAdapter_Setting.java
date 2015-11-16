package com.projectfinfin.projectfinfin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by TNBC's on 23/7/2558.
 */
public class CustomAdapter_Setting extends BaseAdapter {

    //Create all this class for help listview setting page
    Context mContext;
    String[] strName;
    boolean[] check;


    public CustomAdapter_Setting (Context context, String[] strName){
        this.mContext = context;
        this.strName = strName;

    }

    public CustomAdapter_Setting (Context context, String[] strName , boolean[] check){
        this.mContext = context;
        this.strName = strName;
        this.check = check;
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
            view = mInflater.inflate(R.layout.listview_setting, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.title_setting);
        textView.setText(strName[position]);

        if(check != null){
            if (check[position]) {
                ImageView icon = (ImageView) view.findViewById(R.id.image1);
                icon.setImageResource(R.drawable.arrow);
            }
        }
        return view;
    }
}
