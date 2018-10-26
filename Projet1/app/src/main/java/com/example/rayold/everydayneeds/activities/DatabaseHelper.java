package com.example.rayold.everydayneeds.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {

        super(context, "Z.db", null, 1);

    }



    @Override

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table user(name text,email text primary key, password text,role text)");

    }



    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists user");

    }



    public boolean insert(String name, String email, String password, String role) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);

        contentValues.put("email", email);

        contentValues.put("password", password);

        contentValues.put("role", role);

        long ins = db.insert("user", null, contentValues);

        if (ins == -1)

            return false;

        else

            return true;

    }



    public boolean checkmail(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});

        if (cursor.getCount() > 0)

            return false;

        else

            return true;

    }



    //checking the emeil and password

    public boolean emailpasword(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from user where email=? and password=?", new String[]{email, password});

        if (cursor.getCount() > 0)

            return true;

        else

            return false;

    }

    public boolean countAdmin(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorAdmin =db.rawQuery("Select * from user where role=?", new String[]{"Administrateur"});
        if (cursorAdmin.getCount()>=1)
            return false;
        else
            return true;
    }
    public User findUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * FROM user WHERE email=?",new String[]{email});
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setName(cursor.getString(0));
            user.setRole(cursor.getString(2));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;


    }
}
