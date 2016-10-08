package com.example.ggladko97.razdor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ggladko97 on 07.05.16.
 */
public class ProductsDatabase extends SQLiteOpenHelper {
   // byte [] data = new byte[1000];

    SQLiteDatabase database;
    Cursor cursor;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "products.db";
    private static final String TABLE_NAME
            = "products";
    private static final String COLUMN_ID= "id";
    private static final String COLUMN_TITLE= "title";
    private static final String COLUMN_VOLUME= "volume";
    private static final String COLUMN_quantity= "quantity";
    private static final String COLUMN_PRICEPERUNIT= "price";
   // private DbHelper ourHelper;

    private static final String
            TABLE_CREATE= "create table "+TABLE_NAME+" (id integer primary key not null , " +
            "title text not null , volume integer  , quantity integer not null ," +
            " price real not null);";
    public ProductsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION+1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.database = db;


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static final String ALTER_Products_TABLE_ADD_IMAGE =
            "ALTER TABLE products ADD image byte;";



    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion, Products p)
    {
        db.execSQL(ALTER_Products_TABLE_ADD_IMAGE);
        database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from products";
        Cursor cursor = database.rawQuery(query, null);
        int count = cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_TITLE,p.getTitle());
        values.put(COLUMN_VOLUME,p.getVolume());
        values.put(COLUMN_quantity,p.getQuantity());
        values.put(COLUMN_PRICEPERUNIT,p.getPrice());


        database.insert(TABLE_NAME,null,values);
        database.close();

        //db.execSQL(ALTER_USER_TABLE_ADD_USER_STREET1);
    }

/*
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryDrop = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(queryDrop);
        this.onCreate(db);

    }*/

    //public void  insertImage()
    public void insertProducts(Products p){
        database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from products";
        Cursor cursor = database.rawQuery(query, null);
        int count = cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_TITLE,p.getTitle());
        values.put(COLUMN_VOLUME,p.getVolume());
        values.put(COLUMN_quantity,p.getQuantity());
        values.put(COLUMN_PRICEPERUNIT,p.getPrice());


        database.insert(TABLE_NAME,null,values);
        database.close();
    }

    //public String getName(){
      //  database = this.getReadableDatabase();
        //String query  = "select title from products;";
        //Cursor cursor = database.rawQuery(query,null);

    //}

    public List<String> getTitle() {
        database = this.getReadableDatabase();
        List<String> List = new ArrayList<String>();
        // Select All Query
        String selectQuery = "select title from " + TABLE_NAME;
         cursor = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                List.add(cursor.getString(0));//it was 1
            } while (cursor.moveToNext());
        }
        return List;
    }
    public List<String> getPriceAndTitle() {
        database = this.getReadableDatabase();
        List<String> List = new ArrayList<String>();
        // Select All Query
        String selectQuery = "select title, price from " + TABLE_NAME;
        cursor = database.rawQuery(selectQuery, null);
       // String buffer="";

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                List.add(cursor.getString(0));//it was 1
            } while (cursor.moveToNext());
        }
        return List;
    }
    public float getPrice(String title) {
        database = this.getReadableDatabase();
       // List<Float> List = new ArrayList<Float>();

        // Select All Query
        String selectQuery = "select price from " + TABLE_NAME+" where title='"+title+"';";
        cursor = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                return cursor.getFloat(0);//it was 1
            } while (cursor.moveToNext());
        }
        return 0;

    }
    public  Boolean ifExists(String title){
        database=this.getWritableDatabase();
        database=this.getReadableDatabase();
        String selectQuery = "select quantity from " + TABLE_NAME+" where title='"+title+"';";
        cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(0)>0){
                    //ContentValues values = new ContentValues();
                    //values.put(COLUMN_quantity,((cursor.getInt(0))-1));
                    return false;
                }
                else if(cursor.getInt(0)<=0){
                    return  true;

                    // Toast.makeText(MainActivity.this,"No such a product",Toast.LENGTH_SHORT).show();
                }

            } while (cursor.moveToNext());
        }
        return false;
    }
    public String decreaseQuantity(String title){
        database=this.getWritableDatabase();
        database=this.getReadableDatabase();

        String answer;
        // List<Float> List = new ArrayList<Float>();

        // Select All Query
        String selectQuery = "select quantity from " + TABLE_NAME+" where title='"+title+"';";
        cursor = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        try{
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(0)>0){
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_quantity,((cursor.getInt(0))-1));
                    answer = "QUANTITY HAS BEEN DECREASED ON 1 UNIT ";
                    return answer;
                }
                else if(cursor.getInt(0)<=0){
                    answer="No such a product";
                    return answer;

                   // Toast.makeText(MainActivity.this,"No such a product",Toast.LENGTH_SHORT).show();
                }

            } while (cursor.moveToNext());

        }} catch (Exception e) {
            e.printStackTrace();
            System.err.println("There is not even a single unit!!! Contact manager");
        }
        return "";


    }


}
