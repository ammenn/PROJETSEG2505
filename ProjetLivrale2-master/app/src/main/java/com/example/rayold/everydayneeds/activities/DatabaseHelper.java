package com.example.rayold.everydayneeds.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {

        super(context, "A3.db", null, 1);

    }



    @Override

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table user(name text,email text primary key, password text,role text)");
        db.execSQL("Create table service(id integer  primary key,serviceName text, hourlyRate text)");

    }



    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists service");

    }




    public boolean editService(String serviceName, String hourlyRate){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + "service"
                + " WHERE "
                + "serviceName"
                + " = \""
                + serviceName
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            String idStr = cursor.getString(0);
            db.delete("service", "id" + " = " + idStr, null);
            cursor.close();
            this.insertService(serviceName,hourlyRate);
            result = true;
        }
        db.close();
        return result;




    }

    public boolean insertService(String serviceName, String hourlyRate) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("serviceName", serviceName);

        contentValues.put("hourlyRate", hourlyRate);

        long ins = db.insert("service", null, contentValues);

        if (ins == -1)

            return false;

        else

            return true;

    }

    public boolean deleteService(String serviceName, String hourlyRate){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + "service"
                + " WHERE "
                + "serviceName"
                + " = \""
                + serviceName
                + "\""
                + " AND "
                +"hourlyRate"
                + " = \""
                + hourlyRate
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete("service", "id" + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    //pour ne pas inserer plusieurs services de meme nom
    public boolean serviceNameHourlyRate(String serviceName) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from service where serviceName=?", new String[]{serviceName});

        if (cursor.getCount() > 0)

            return true;

        else

            return false;

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
        String query = "Select * FROM "
                + "user"
                + " WHERE "
                + "email"
                + " = \""
                + email
                + "\"";
        Cursor cursor = db.rawQuery(query,null);
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setName(cursor.getString(0));
            user.setEmail(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setRole(cursor.getString(3));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;


    }

    public boolean isAdministrator(String email){

        if(this.findSpecificAdmin(email)!=null){

            return true;

        }

        else

            return false ;



    }



    public User findSpecificAdmin(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from user where role=? and email=?", new String[]{"Administrateur", email});

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
