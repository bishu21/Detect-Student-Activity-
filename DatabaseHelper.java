package com.example.bishwendra.note_student_activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by bishwendra on 3/30/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_STUDENT = "student";
    public static final String TABLE_TEACHER = "teacher";
    public static final String ID = "id";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "pass";
    public static final String MOBILE_NUMBER = "mobile";
    public static final String TYPE = "type";
    public static final int DATABASE_VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE__TABLE="CREATE TABLE " + TABLE_STUDENT + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERNAME + " TEXT," + PASSWORD + " TEXT," + MOBILE_NUMBER + " INTEGER," + TYPE + " TEXT)";
        db.execSQL( SQL_CREATE__TABLE);

        final String SQL_CREATE__TABLE1="CREATE TABLE " + TABLE_TEACHER + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERNAME + " TEXT," + PASSWORD + " TEXT," + MOBILE_NUMBER + " INTEGER," + TYPE + " TEXT)";
        db.execSQL( SQL_CREATE__TABLE1);
    }

    public String searchPass(String uname,String type)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query;

        if(type=="Student") {
            query = "select user, pass from " + TABLE_STUDENT;
        }
        else {
            query = "select user, pass from " + TABLE_TEACHER;
        }

        Cursor cursor = db.rawQuery(query , null);
        String a,b;
        b= "not found";
        if (cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;

    }

    public void GetTeacher(List<String> Teacher){
            Cursor cursor;
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "select user, pass, mobile from " + TABLE_TEACHER;
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            Teacher.add(cursor.getString(0));
            do {


                cursor.moveToNext();
                Teacher.add(cursor.getString(0));
            }while(!cursor.isLast());



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);
        onCreate(db);
    }

    public boolean insertData(String user, String pass, String mobile,String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, user);
        contentValues.put(PASSWORD, pass);
        contentValues.put(MOBILE_NUMBER, mobile);
        contentValues.put(TYPE, type);
        long result=0;
        if(type.equals("Student")) {
             result = db.insert(TABLE_STUDENT, null, contentValues);
        }
        else if (type=="Teacher"){
             result = db.insert(TABLE_TEACHER,null,contentValues);
        }

        if (result == -1) {
            Log.d("not inserted", String.valueOf(result));
            return false;
        } else {
            Log.d("inserted", String.valueOf(result));
            return true;
        }
    }
}
