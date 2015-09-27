package com.projectfinfin.projectfinfin;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.projectfinfin.projectfinfin.Grid.GridViewActivity;


public class TypeElectronicActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_electronic);


        Button buttonTV = (Button) findViewById(R.id.button);
        Button buttonCamera = (Button) findViewById(R.id.button1);
        Button buttonPc = (Button) findViewById(R.id.button2);
        Button buttonSoftware = (Button) findViewById(R.id.button3);
        Button buttonHardware = (Button) findViewById(R.id.button4);
        Button buttonAccess = (Button) findViewById(R.id.button5);

        buttonTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=37");
                startActivity(i);
            }
        });
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=38");
                startActivity(i);
            }
        });
        buttonPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=39");
                startActivity(i);
            }
        });
        buttonSoftware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=40");
                startActivity(i);
            }
        });
        buttonHardware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=41");
                startActivity(i);
            }
        });
        buttonAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.putExtra("ParamUrl", "?id=42");
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_type_electronic, menu);
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
