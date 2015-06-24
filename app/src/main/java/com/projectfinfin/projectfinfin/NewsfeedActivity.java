package com.projectfinfin.projectfinfin;

import android.app.ListActivity;
import android.content.Context;
//import android.support.v7.app.ActionBarActivity;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class NewsfeedActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
        setupCustomListView();
    }

    private void setupCustomListView() {
        setListAdapter(new EfficientAdapter(getApplicationContext()));
    }

    public class EfficientAdapter extends BaseAdapter{

        public Context mContext;
        public LayoutInflater mInflater;

        public EfficientAdapter(Context ctx){
            mContext = ctx;
            mInflater = LayoutInflater.from(mContext);
        }
        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public View getView(int position, View convertedView, ViewGroup viewGroup) {

            ViewHolder viewHolder = null;
            if(convertedView == null){
                //create convertedView and bind Widgets in convertedView
                convertedView = mInflater.inflate(R.layout.listview_row , null);
                viewHolder = new ViewHolder();
                viewHolder.brandImage = (ImageView)convertedView.findViewById(R.id.brandImage);
                viewHolder.titleTextView = (TextView)convertedView.findViewById(R.id.titleTextView);
                convertedView.setTag(viewHolder);
            }else{
                // re-bind widgets in convertedView
                viewHolder = (ViewHolder) convertedView.getTag();
            }
                //update content in convertedView
         //   viewHolder.titleTextView.setText(String.valueOf(position));
            return convertedView;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }

        public class ViewHolder{
            public ImageView brandImage;
            public TextView titleTextView;
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_newsfeed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
