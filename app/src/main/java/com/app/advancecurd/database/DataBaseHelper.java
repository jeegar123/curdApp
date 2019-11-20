package com.app.advancecurd.database;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.advancecurd.adapters.Item;

import java.util.ArrayList;

import static com.app.advancecurd.database.MyUtil.DBTABLE;

public class DataBaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;

    public DataBaseHelper(Context context) {
        super(context, MyUtil.DBNAME, null, 1);
        sqLiteDatabase = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MyUtil.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insert(String fullname, String username, String password, String gender, String city, String status, String branch) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("gender", gender);
        contentValues.put("city", city);
        contentValues.put("status", status);
        contentValues.put("branch", branch);
        long inserted_row = 0;
        try {
            inserted_row = sqLiteDatabase.insert(DBTABLE, null, contentValues);
        } catch (Exception e) {

        }
        return inserted_row;
    }

    public boolean checkAuthentication(String username, String password) {
        String s = "select username,password from '" + DBTABLE + "' where username ='" + username + "' and password = '" + password + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(s, null);
        return cursor.moveToFirst();
    }

    public ArrayList getSelectedUserData(String username) {
        ArrayList<String> arrayList = new ArrayList<String>();
        String sql = "select * from '" + DBTABLE + "' where username = '" + username + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor == null)
            return null;

        else {
            cursor.moveToFirst();
            arrayList.add(cursor.getString(1));
            arrayList.add(cursor.getString(2));
            arrayList.add(cursor.getString(3));
            arrayList.add(cursor.getString(4));
            arrayList.add(cursor.getString(5));
            arrayList.add(cursor.getString(6));
            arrayList.add(cursor.getString(7));
        }
        return arrayList;
    }


    public long update(String fullname, String username, String password, String gender, String city, String status, String branch) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("gender", gender);
        contentValues.put("city", city);
        contentValues.put("status", status);
        contentValues.put("branch", branch);
        long update_row = 0;
        try {
            update_row = sqLiteDatabase.update(DBTABLE, contentValues, "username= ? ", new String[]{username});
        } catch (Exception e) {
        }
        return update_row;
    }

    public boolean delete(String username) {
        return sqLiteDatabase.delete(DBTABLE, "username=?", new String[]{username}) != -1;
    }

    public ArrayList<Item> getRecordsInList(){
        String sql = "select fullname,username,gender,city,status,branch from "+DBTABLE;
        ArrayList<Item> items=new ArrayList<Item>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                items.add(new Item(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        return items;
    }
}
