package com.projectfinfin.projectfinfin;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.projectfinfin.projectfinfin.Floor.FloorSpinner;
import com.projectfinfin.projectfinfin.Fragments.CameraFragment;
import com.projectfinfin.projectfinfin.Fragments.CategoryFragment;
import com.projectfinfin.projectfinfin.WhereToGo.InNavFragment;

public class TestActivity extends AppCompatActivity {

    //tool bar
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    android.support.v7.app.ActionBarDrawerToggle drawerToggle;
    CoordinatorLayout rootLayout;
    NavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //tool bar
        initToolbar();
        initInstances();
    }
    //tool bar
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initInstances() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);

        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment fragment = null;
                Intent intent = null;
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navItem1:
                        intent = new Intent(getApplicationContext(), NewsfeedActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navItem2:
                        fragment = new CategoryFragment();
                        FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                        transaction1.replace(R.id.container, fragment);
                        transaction1.addToBackStack(null);
                        transaction1.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navItem3:
                        fragment = new CameraFragment();
                        FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                        transaction2.replace(R.id.container, fragment);
                        transaction2.addToBackStack(null);
                        transaction2.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navItem4:
                        fragment = new FloorSpinner();
                        FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                        transaction4.replace(R.id.container, fragment);
                        transaction4.addToBackStack(null);
                        transaction4.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navItem5:
                        fragment = new InNavFragment();
                        FragmentTransaction transaction5 = getFragmentManager().beginTransaction();
                        transaction5.replace(R.id.container, fragment);
                        transaction5.addToBackStack(null);
                        transaction5.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navItem6:
                        fragment = new ProfileFragment();
                        FragmentTransaction transaction6 = getFragmentManager().beginTransaction();
                        transaction6.replace(R.id.container, fragment);
                        transaction6.addToBackStack(null);
                        transaction6.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });


    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

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
