package com.projectfinfin.projectfinfin;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.projectfinfin.projectfinfin.Grid.GridViewActivity;


public class TypeBeautyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_beauty);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button Beauty = (Button) findViewById(R.id.button);
        Button Access = (Button) findViewById(R.id.button1);
        Button Cosmetic = (Button) findViewById(R.id.button2);
        Button Health = (Button) findViewById(R.id.button3);

        Beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=23");
                startActivity(i);
            }
        });
        Access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=22");
                startActivity(i);
            }
        });
        Cosmetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=24");
                startActivity(i);
            }
        });
        Health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=25");
                startActivity(i);
            }
        });

    }

    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_type_beauty, menu);
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
