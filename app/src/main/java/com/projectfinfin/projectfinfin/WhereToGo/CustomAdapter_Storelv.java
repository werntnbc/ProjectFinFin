/* เอาไว้จัดการ Listview ของแต่ละ class */

package com.projectfinfin.projectfinfin.WhereToGo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.R;

import java.util.List;

public class CustomAdapter_Storelv extends BaseAdapter {
	
	private ViewHolder mViewHolder;
    private LayoutInflater mInflater;
    private List<Store> mStores;
    private Store mStore;

    public CustomAdapter_Storelv(Activity activity, List<Store> stores) {
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mStores = stores;
    }
    
    @Override
    public int getCount() {
        return mStores.size();
    }

//    @Override
//    public Object getItem(int position) {
//        return mStores.get(position).getStoreName();
//    }
    
    @Override
    public String getItem(int position) {
        return mStores.get(position).getStoreName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_store, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.storeName = (TextView) convertView.findViewById(R.id.storeName);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mStore = mStores.get(position);
        mViewHolder.storeName.setText(mStore.getStoreName());
        return convertView;
    }
 
    private static class ViewHolder {
        TextView storeName;
    }


    
    
    
    
}