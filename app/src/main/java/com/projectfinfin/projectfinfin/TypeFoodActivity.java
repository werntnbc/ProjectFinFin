package com.projectfinfin.projectfinfin;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.projectfinfin.projectfinfin.Grid.GridViewActivity;


public class TypeFoodActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_food);
        //button back on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Click button type food noodle go to group
        Button buttonNoodle = (Button) findViewById(R.id.button_noodle);
        Button buttonBuffet = (Button) findViewById(R.id.button_buffet);
        Button buttonPizza = (Button) findViewById(R.id.button_pizza);
        Button buttonFastfood = (Button) findViewById(R.id.button_fastfood);
        Button buttonSteak = (Button) findViewById(R.id.button_steak);
        Button buttonShabu = (Button) findViewById(R.id.button_shabu);
        Button buttonSeafood = (Button) findViewById(R.id.button_seafood);
        Button buttonGrill = (Button) findViewById(R.id.button_grill);
        Button buttonJapan = (Button) findViewById(R.id.button_japan);
        Button buttonThai = (Button) findViewById(R.id.button_thai);
        Button buttonChinese = (Button) findViewById(R.id.button_chinese);
        Button buttonBeverage = (Button) findViewById(R.id.button_beverage);
        Button buttonDessert = (Button) findViewById(R.id.button_dessert);
        Button buttonBakery = (Button) findViewById(R.id.button_bakery);
        Button buttonIce = (Button) findViewById(R.id.button_ice);


        buttonNoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=1");
                startActivity(i);
            }
        });
        buttonBuffet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=2");
                startActivity(i);
            }
        });
        buttonPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=3");
                startActivity(i);
            }
        });
        buttonFastfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=4");
                startActivity(i);
            }
        });
        buttonSteak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=5");
                startActivity(i);
            }
        });
        buttonShabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=6");
                startActivity(i);
            }
        });
        buttonSeafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=7");
                startActivity(i);
            }
        });
        buttonGrill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=8");
                startActivity(i);
            }
        });
        buttonJapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=9");
                startActivity(i);
            }
        });
        buttonThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=10");
                startActivity(i);
            }
        });
        buttonChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=11");
                startActivity(i);
            }
        });
        buttonBeverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=12");
                startActivity(i);
            }
        });
        buttonDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=13");
                startActivity(i);
            }
        });
        buttonBakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=14");
                startActivity(i);
            }
        });
        buttonIce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=15");
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_type_food, menu);
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
