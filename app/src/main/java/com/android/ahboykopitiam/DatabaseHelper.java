package com.android.ahboykopitiam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {

        super(context, "coffee.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE customer (fullName varchar, email varchar, phone varchar, password varchar)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS customer");
        onCreate(db);
    }

    public boolean insertData(String fullName, String email, String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullName", fullName);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("password", password);
        long result = db.insert("customer", null, contentValues);
        return result != -1;
    }

    public boolean checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"email"};
        String selection = "email = ? AND password = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query("customer", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        return count > 0;
    }

    public boolean checkEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"email"};
        String selection = "email = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query("customer", columns, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("customer", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int fullNameIndex = cursor.getColumnIndex("fullName");
            int emailIndex = cursor.getColumnIndex("email");
            int phoneIndex = cursor.getColumnIndex("phone");

            do {
                String fullName = cursor.getString(fullNameIndex);
                String email = cursor.getString(emailIndex);
                String phone = cursor.getString(phoneIndex);

                User user = new User(fullName, email, phone);
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }
}