package com.example.pcworld.ebtda2y;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add extends AppCompatActivity {

    // Variables.
    Intent main;
    // Views REFs.
    TextView add_title;
    EditText add_name, add_add, add_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ActionBar.
        this.setTitle("");
        add_title = (TextView) findViewById(R.id.add_title);
        add_title.setText("إضافة جديد");

        // Intent MAIN initialization.
        main = new Intent(this, YearList.class);
        main.putExtra("year", getIntent().getExtras().getString("year"));

        // Get Views REFs.
        add_name  = (EditText) findViewById(R.id.add_name);
        add_add   = (EditText) findViewById(R.id.add_add);
        add_phone = (EditText) findViewById(R.id.add_phone);
    }

    // Saving.
    public void save_click(View view) {
        if(add_name.getText().toString().equals("")) {
            Toast.makeText(this, "Invalid name!", Toast.LENGTH_LONG).show();
            return;
        }
        else if (add_add.getText().toString().equals("")) {
            Toast.makeText(this, "Invalid address!", Toast.LENGTH_LONG).show();
            return;
        }
        else if (add_phone.getText().toString().equals("")) {
            Toast.makeText(this, "Invalid phone!", Toast.LENGTH_LONG).show();
            return;
        }
        MainActivity.dbHandler.add(add_name.getText().toString(),
                add_add.getText().toString(), add_phone.getText().toString(),
                getIntent().getExtras().getString("year"),
                getIntent().getExtras().getString("gender"));
        startActivity(main);
        this.finish();
    }

    // Canceling.
    public void cancel_click(View view) {
        startActivity(main);
        this.finish();
    }

}
