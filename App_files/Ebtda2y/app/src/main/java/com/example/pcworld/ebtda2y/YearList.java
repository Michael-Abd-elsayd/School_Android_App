package com.example.pcworld.ebtda2y;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class YearList extends AppCompatActivity implements MSG.MSGListener{

    // Variables.
    String table_name, year, gender, deleted_member;
    String from[] = new String[] {MyDBHandler.COLUMN_NAME, MyDBHandler.COLUMN_ADDRESS, MyDBHandler.COLUMN_PHONE};
    int to[] = new int[] {R.id.name, R.id.add, R.id.phone};
    Intent call = new Intent(Intent.ACTION_CALL);
    SimpleCursorAdapter SCA_b, SCA_g;
    public static Context context;
    // Views REFs.
    TextView year_title, gender_switch;
    ListView list_boys, list_girls;
    ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        // ActionBar.
        this.setTitle("");
        year_title = (TextView) findViewById(R.id.year_title);
        table_name = (getIntent().getExtras()).getString("year_title");
        year_title.setText(table_name);

        // ListView & DB tasks.
        // BOYS
        list_boys = (ListView) findViewById(R.id.list_boys);
        year = (getIntent().getExtras()).getString("year");
        SCA_b = new SimpleCursorAdapter(this, R.layout.custom_row,
                MainActivity.dbHandler.getData(year,"b"), from, to, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        list_boys.setAdapter(SCA_b);
        // GIRLS
        list_girls = (ListView) findViewById(R.id.list_girls);
                year = (getIntent().getExtras()).getString("year");
        SCA_g = new SimpleCursorAdapter(this, R.layout.custom_row,
                        MainActivity.dbHandler.getData(year, "g"), from, to, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        list_girls.setAdapter(SCA_g);

        // View Flipper.
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        gender_switch = (TextView) findViewById(R.id.gender_switch);
        gender_switch.setText("بنات");
    }

    // Switching gender.
    public void gender_switch(View view) {
         if(gender_switch.getText().toString().equals("بنات")) {
             gender_switch.setText("ولاد");
         }
         else { gender_switch.setText("بنات"); }
         // Flip view.
         flipper.showNext();
    }

    // Calling.
    public void call_click(View view) {
        TextView phone = (TextView) ((View)view.getParent()).findViewById(R.id.phone);
        call.setData(Uri.parse("tel:" + phone.getText()));
        startActivity(call);
    }

    // Editing.
    public void edit_click(View view) {
        // Get Views REFs.
        View v = (View) (view.getParent()).getParent();
        TextView name  = (TextView) v.findViewById(R.id.name);
        TextView add   = (TextView) v.findViewById(R.id.add);
        TextView phone = (TextView) v.findViewById(R.id.phone);
        // Send Extras to Edit activity and Start it.
        Intent edit = new Intent(this, Edit.class);
        edit.putExtra("name", name.getText());
        edit.putExtra("add", add.getText());
        edit.putExtra("phone", phone.getText());
        edit.putExtra("year", year);
        startActivity(edit);
        this.finish();
    }

    // Add new member.
    public void add_click(View view) {
        if(gender_switch.getText().toString().equals("ولاد")) {
            gender = "g";
        }
        else  { gender = "b"; }
        // Create Intent and start it.
        Intent add = new Intent(this, Add.class);
        add.putExtra("year", year);
        add.putExtra("gender",gender);
        startActivity(add);
        this.finish();
    }

    // Delete a member.
    public void delete_click(View view) {
        deleted_member = ((TextView) ((View) view.getParent()).findViewById(R.id.name)).getText().toString();
        DialogFragment msg = new MSG();
        msg.show(getSupportFragmentManager(), "missiles");
    }

    //////////////////////////// DIALOG /////////////////////////////////
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        MainActivity.dbHandler.delete(deleted_member, year);
        Intent list = new Intent(this, YearList.class);
        list.putExtra("year", year);
        startActivity(list);
        this.finish();
    }
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }
}
