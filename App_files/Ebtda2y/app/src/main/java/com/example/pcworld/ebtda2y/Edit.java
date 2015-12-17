package com.example.pcworld.ebtda2y;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends AppCompatActivity {

    // Variables.
    String old_name;
    Intent main;
    // Views REFs.
    TextView edit_title;
    EditText edit_name, edit_add, edit_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ActionBar.
        this.setTitle("");
        edit_title = (TextView) findViewById(R.id.edit_title);
        edit_title.setText("تعديل البيانات");

        // Get Extras from previous activity.
        Bundle data = getIntent().getExtras();
        old_name = data.getString("name");

        // Views REFs.
        edit_name  = (EditText) findViewById(R.id.edit_name);
        edit_add   = (EditText) findViewById(R.id.edit_add);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        // Set Views initial values.
        edit_name.setText(data.getString("name"));
        edit_add.setText(data.getString("add"));
        edit_phone.setText(data.getString("phone"));

        // Intent MAIN initialization.
        main = new Intent(this, YearList.class);
    }

    // Saving.
    public void save_click(View view) {
        MainActivity.dbHandler.update(old_name, edit_name.getText().toString(),
                edit_add.getText().toString(), edit_phone.getText().toString());
        main.putExtra("year",getIntent().getExtras().getString("year"));
        startActivity(main);
        this.finish();
    }

    // Canceling.
    public void cancel_click(View view) {
        main.putExtra("year",getIntent().getExtras().getString("year"));
        startActivity(main);
        this.finish();
    }

}
