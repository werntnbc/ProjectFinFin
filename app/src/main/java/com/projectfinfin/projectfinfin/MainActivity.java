package com.projectfinfin.projectfinfin;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;


public class MainActivity extends Activity {

    //for navigation drawable
    public static final String KEY_DRAWABLE_ID = "drawableId";
    private String[] mDrawerTitle = {"News feed", "Category", "Camera", "Map", "Profile"};
    private ListView mListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListView = (ListView) findViewById(R.id.drawer);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mDrawerTitle);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new CameraFragment();
                        break;
                    default:
                        break;
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment).commit();
                } else {
                    // error in creating fragment
                    Log.e("MainActivity", "Error in creating fragment");
                }
            }
        });


      getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

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


        //selectFragment(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }



   /* public void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new CameraFragment();
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    private void selectFragment(int position) {
        //Fragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        //fragment.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //transaction.replace(R.id.container, fragment);
        transaction.commit();

        mListView.setItemChecked(position, true);
        setTitle(mDrawerTitle[position]);
        mDrawerLayout.closeDrawer(mListView);
    } */
}

