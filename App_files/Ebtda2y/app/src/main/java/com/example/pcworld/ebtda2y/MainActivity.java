package com.example.pcworld.ebtda2y;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /////////////////////////// VARIABLES. ///////////////////////////
    // Variables.
    String year , year_title;
    Intent year_list;
    public static MyDBHandler dbHandler;
    // Views REFs.
    TextView main_title;
    TextView y1, y2, y3, y4, y5, y6;

    /////////////////////////// OnCreate ///////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ActionBar.
        this.setTitle("");
        main_title = (TextView) findViewById(R.id.main_title);
        main_title.setText("مدرستى");


        // DB HANDLING.
        Thread DB_handle = new Thread(new Runnable() {
            @Override
            public void run() {
                dbHandler = new MyDBHandler(MainActivity.this, null, null, 0);
            }
        });
        DB_handle.setDaemon(true);
        DB_handle.start();

        // Get TextViews REFs.
        y1 = (TextView) findViewById(R.id.y1);
        y2 = (TextView) findViewById(R.id.y2);
        y3 = (TextView) findViewById(R.id.y3);
        y4 = (TextView) findViewById(R.id.y4);
        y5 = (TextView) findViewById(R.id.y5);
        y6 = (TextView) findViewById(R.id.y6);

        // Intent Initialization.
        year_list = new Intent(this, YearList.class);

    }

    /////////////////////////// YEAR CLICK ///////////////////////////
    // When any year is clicked.
    public void year_click(View view) {
        // -1- Identify the clicked year.
        switch(view.getId()) {
            case R.id.y1:
                year_title = "أولى إبتدائى" ;
                year = "y1";
                break;
            case R.id.y2:
                year_title = "ثانية إبتدائى" ;
                year = "y2";
                break;
            case R.id.y3:
                year_title = "ثالثة إبتدائى" ;
                year = "y3";
                break;
            case R.id.y4:
                year_title = "رابعة إبتدائى" ;
                year = "y4";
                break;
            case R.id.y5:
                year_title = "خامسة إبتدائى" ;
                year = "y5";
                break;
            case R.id.y6:
                year_title = "سادسة إبتدائى" ;
                year = "y6";
                break;
            case R.id.y0:
                year_title = "حضانة" ;
                year = "y0";
                break;
            default:
                year_title = "أولى إبتدائى";
                year = "y1";
        }

        // -2- Start new Activity with specific data.
        year_list.putExtra("year_title", year_title);
        year_list.putExtra("year", year);
        startActivity(year_list);
    }

}
