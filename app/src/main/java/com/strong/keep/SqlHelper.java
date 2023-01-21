package com.strong.keep;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class SqlHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TODO_STRONG";

    public SqlHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Task(Title TEXT, TaskValue TEXT)");
        db.execSQL("CREATE TABLE List(ListValue TEXT)");
    }

    /*INSERT VALUES*/
    public Boolean createTask(String Title, String TaskValue) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Title", Title);
        contentValue.put("TaskValue", TaskValue);

        long result = DB.insert("Task", null, contentValue);
        return result != -1;
    }

    /*INSERT LIST*/
    public Boolean createList(String ListValue) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ListValue", ListValue);
        long result = DB.insert("List", null, contentValues);
        return result != -1;
    }

    /*SHOW VALUE*/
    public Cursor SHOW(String TableName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("select * from " + TableName, null);
    }

    /*Search Task*/
    public String searchTask(String TableName){
        return "Select";
    }
    /*Delete Task*/
    public Boolean DeleteTask(String TableName, String TaskValue) {
        SQLiteDatabase DB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("select * from " + TableName + " where TaskValue=?", new String[]{TaskValue});
        if (cursor.getCount() > 0) {
            long result = DB.delete(TableName, "TaskValue=?", new String[]{TaskValue});
            return result == 1;
        }
        return false;
    }

    /*Delete List*/
    public Boolean DeleteList(String TableName, String ListValue) {
        SQLiteDatabase DB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("select * from " + TableName + " where ListValue=?", new String[]{ListValue});
        if (cursor.getCount() > 0) {
            long result = DB.delete(TableName, "ListValue=?", new String[]{ListValue});
            return result == 1;
        }
        return false;
    }

    /* Update Task*/
    public Boolean UpdateTask(String TableName, String PreviousValues, String NewValue){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TaskValue",NewValue);
        long result=DB.update(TableName, values,"TaskValue=?", new String[]{PreviousValues});
        return result != -1;
    }
    /*:Update List */
    public Boolean UpdateList(String TableName, String PreviousValue,String newValue){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("ListValue", newValue);
        long result=DB.update(TableName,values,"ListValue=?",new String[]{PreviousValue});
        return result != -1;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
