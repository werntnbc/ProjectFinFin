package com.projectfinfin.projectfinfin;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.projectfinfin.projectfinfin.Grid.GridViewActivity;


public class TypeFashionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_fashion);

        Button buttonWomen = (Button) findViewById(R.id.button);
        Button buttonMen = (Button) findViewById(R.id.button1);
        Button buttonAccessory = (Button) findViewById(R.id.button2);
        Button Shoes = (Button) findViewById(R.id.button3);
        Button Bags = (Button) findViewById(R.id.button4);
        Button Sports = (Button) findViewById(R.id.button5);

        buttonWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=16");
                startActivity(i);
            }
        });
        buttonMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=17");
                startActivity(i);
            }
        });
        buttonAccessory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=18");
                startActivity(i);
            }
        });
        Shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=19");
                startActivity(i);
            }
        });
        Bags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=20");
                startActivity(i);
            }
        });
        Sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=21");
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_type_fashion, menu);
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
