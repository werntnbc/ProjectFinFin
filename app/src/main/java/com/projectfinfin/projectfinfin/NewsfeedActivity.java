package com.projectfinfin.projectfinfin;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.ActionBarDrawerToggle;


public class NewsfeedActivity extends ActionBarActivity{


    //for navigation drawable
    public static final String KEY_DRAWABLE_ID = "drawableId";
    private String[] mDrawerTitle = {"News feed", "Category", "Camera", "Map", "Profile"};
    private ListView mListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //for navigation drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListView = (ListView) findViewById(R.id.drawer);

        ArrayAdapter<String> adapterDrawer = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDrawerTitle);

        mListView.setAdapter(adapterDrawer);

        // for listview newsfeed page
        int[] resId = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        String[] list = {"Aerith Gainsborough", "Barret Wallace", "Cait Sith"
                , "Cid Highwind", "Cloud Strife", "RedXIII", "Sephiroth"
                , "Tifa Lockhart", "Vincent Valentine", "Yuffie Kisaragi"
                , "ZackFair"};

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), list, resId);

        ListView listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        //newsfeed activity list view go to description activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent;
                switch (arg2) {
                    case 0:
                        intent = new Intent(getApplicationContext()
                                , DescriptionActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { // เช็คลำดับ
                    Intent i = new Intent(getApplicationContext()
                            , NewsfeedActivity.class);
                    finish(); // ไว้เคลียค่าจากหน้าทีอยู่ก่อน เริ่มหน้าใหม่ ด้านล่าง
                    startActivity(i);
                }else if(position == 1){
                    Intent i = new Intent(getApplicationContext(),CategoryActivity.class);
                    finish();
                    startActivity(i);
                }
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(
                this,   // Context
                mDrawerLayout,  // DrawerLayout
                R.drawable.ic_drawer,  // รูปภาพที่จะใช้
                R.string.drawer_open, // ค่า String ในไฟล์ strings.xml
                R.string.drawer_close // ค่า String ในไฟล์ strings.xml
        ) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
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
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //Toast.makeText(this, "On click Top bar", Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }
}
