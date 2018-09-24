package com.app.assignmenttwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int database_VERSION = 1;
    private static final String database_NAME = "LoginDB";
    //Login table
    private static final String table_Login = "Login";
    private static final String login_id = "id";
    private static final String login_name = "Name";
    private static final String login_num = "Number";
    private static final String login_email = "Email";
    private static final String login_password = "Password";

    List_Login list_login = new List_Login();
    private static final String[] COLUMNS_login = {login_id, login_name, login_num, login_email, login_password};

    public DatabaseHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE Login (id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT ,Number INTEGER,Email TEXT , Password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Login");
        this.onCreate(db);
    }

    public void createLogin(List_Login login) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();
        // make values to be inserted
        ContentValues values = new ContentValues();
//        values.put(trans_id, transaction.getId());
        values.put(login_name, login.getStringName());
        values.put(login_num, login.getStringNumber());
        values.put(login_email, login.getStringEmail());
        values.put(login_password, login.getStringPassword());
        // insert book
        db.insert(table_Login, null, values);
        // close database transaction
        db.close();
    }

    public String getLoginID(String stringEmail,String stringPassword) {
        String stringId = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name from Login where (Email = ? and Password = ?) OR (Number = ? AND Password = ?)",
                new String [] {stringEmail,stringPassword});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    stringId = cursor.getString(0);
                } while (cursor.moveToNext());
            }
        }
        return stringId;
    }
}
