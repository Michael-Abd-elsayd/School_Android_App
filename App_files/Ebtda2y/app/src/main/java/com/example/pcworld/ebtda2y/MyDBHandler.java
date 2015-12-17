package com.example.pcworld.ebtda2y;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper implements KidsData{

    /////////////////////////// Variables ///////////////////////////
    // DB Configurations.
    final static String DB_NAME = "ebtda2y.db";
    final static int DB_VERSION = 1;
    final static String TABLE_NAME = "kids";
    final static String COLUMN_ID = "_id";
    final static String COLUMN_NAME = "name";
    final static String COLUMN_PHONE = "phone";
    final static String COLUMN_ADDRESS = "address";
    final static String COLUMN_YEAR = "year";
    final static String COLUMN_GENDER = "gender";
    // Variables.
    String table_query, data_query;
    Cursor data;
    /////////////////////////// Constructor ///////////////////////////
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    /////////////////////////// OnCreate Method ///////////////////////////
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table.
        table_query = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( "+
            COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMN_NAME+" TEXT ,"+
            COLUMN_ADDRESS+" TEXT ,"+
            COLUMN_PHONE+" TEXT ,"+
            COLUMN_YEAR+" TEXT ,"+
            COLUMN_GENDER+" TEXT );";
        db.execSQL(table_query);

        // DATA INSERTION.
        // Check if data Exists.
        data_query = "SELECT _id FROM "+TABLE_NAME+" WHERE 1; ";
        Cursor c = db.rawQuery(data_query, null);
        if( !(c.moveToFirst()) ) {
            data_query = "INSERT INTO "+TABLE_NAME+" ( "+COLUMN_NAME+","+COLUMN_ADDRESS+","+COLUMN_PHONE+"," +
                            COLUMN_YEAR+","+COLUMN_GENDER+" ) VALUES " + kids_data;
            db.execSQL(data_query);
        }
        c.close();
    }

    /////////////////////////// OnUpgrade Method ///////////////////////////
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /////////////////////////// Get Data ///////////////////////////
    public Cursor getData(String year, String gender) {
        SQLiteDatabase db = getReadableDatabase();
        data_query = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_YEAR+" = \""+year+"\" AND "+
                COLUMN_GENDER+" = \""+gender+"\" ;";
        data = db.rawQuery(data_query, null);
        data.moveToFirst();
        db.close();
        return data;
    }

    /////////////////////////// UPDATE ///////////////////////////
    public void update(String old_name, String name, String add, String phone) {
        data_query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_NAME+" = \""+name+"\","
        +COLUMN_ADDRESS+" = \""+add+"\","
        +COLUMN_PHONE+" = \""+phone+"\" WHERE "+COLUMN_NAME+" = \""+old_name+"\";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(data_query);
        db.close();
    }

    /////////////////////////// ADD ///////////////////////////
    public void add(String name, String add, String phone, String year, String gender) {
        // Query.
        data_query = "INSERT INTO "+TABLE_NAME+" ( "+COLUMN_NAME+","+COLUMN_ADDRESS+","+COLUMN_PHONE+"," +
                COLUMN_YEAR+","+COLUMN_GENDER+" ) VALUES " +
               " ( \""+name+"\", \""+add+"\", \""+phone+"\", \""+year+"\" , \""+gender+"\");";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(data_query);
        db.close();
    }

    /////////////////////////// DELETE ///////////////////////////
    public void delete(String name, String year) {
        data_query = "DELETE FROM "+TABLE_NAME+" " +
                " WHERE "+COLUMN_NAME+" = \""+name+"\" AND "+COLUMN_YEAR+" = \""+year+"\";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(data_query);
        db.close();
    }
}
