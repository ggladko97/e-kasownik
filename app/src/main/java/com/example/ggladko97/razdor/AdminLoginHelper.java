package com.example.ggladko97.razdor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ggladko97 on 06.05.16.
 */
public class AdminLoginHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "contacts.db";
    private static final String TABLE_NAME
            = "contacts";
    private static final String COLUMN_ID= "id";
    private static final String COLUMN_NAME= "name";
    //private static final String COLUMN_EMAIL= "email";
    private static final String COLUMN_SURNAME= "surname";
    private static final String COLUMN_PASS= "password";
    private static final String COLUMN_USERNAME= "username";

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null " +
            ", name text not null , surname text not null ," +
            " username text not null , password text not null);";

    public AdminLoginHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryDrop = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(queryDrop);
        this.onCreate(db);

    }

    public void insertContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_SURNAME,c.getSurname());
        values.put(COLUMN_USERNAME,c.getUsername());
        values.put(COLUMN_PASS,c.getPassword());
        cursor.close();


        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    public String searchPass(String stringLogin) {
        db = this.getReadableDatabase();
        String query = "select username , password from "+TABLE_NAME+";";
        Cursor cursor = db.rawQuery(query,null);
        String uname;
        String passw = "not found";
        if(cursor.moveToFirst())
        {
            do {
                uname = cursor.getString(0);
                if(uname.equals(stringLogin)){
                    passw = cursor.getString(1);
                break;}


            }while(cursor.moveToNext());



        }
        return passw;

        }
       public boolean checkPasswordConst(String compareString){
        String query = "select password from contacts where password='79nisivi' AND username='ggladko97' ;";
        Cursor cursor = db.rawQuery(query, null);
           if(cursor.getCount() <= 0){
               if(cursor.getString(0)==compareString){
                   return true;
               }
               cursor.close();
               return false;
           }
        return false;
    }
    }

