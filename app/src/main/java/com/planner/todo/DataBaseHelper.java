package com.planner.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TODO_TABLE = "TODO_TABLE";
    public static final String COLUMN_USER_ID = "ID";
    public static final String COLUMN_USER_TASK = "USER_TASK";
    public static final String COLUMN_IMP_TAG = "IMP_TAG";
    public static final String COLUMN_URG_TAG = "URG_TAG";

    public DataBaseHelper(Context context) {
        super(context, "Planner.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TODO_TABLE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_TASK + " TEXT, " + COLUMN_IMP_TAG + " BOOL, " + COLUMN_URG_TAG + " BOOL )";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
    }

    public boolean addOne(TaskModel taskModel)
    {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_TASK,taskModel.getTask());
        cv.put(COLUMN_IMP_TAG,taskModel.imp_tag);
        cv.put(COLUMN_URG_TAG,taskModel.urg_tag);

        long insert = db.insert(TODO_TABLE, null, cv);
        return insert != -1;
    }
    

    public List<String> getDo() {

        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TODO_TABLE + " WHERE " + COLUMN_IMP_TAG + " IS 1 AND " + COLUMN_URG_TAG + " IS 1 ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            do {
                String do_task = cursor.getString(1);
                returnList.add(do_task);

            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;

    }

    public boolean deleteDO() {

        String queryString = "DELETE FROM " + TODO_TABLE + " WHERE " + COLUMN_IMP_TAG + " IS 1 AND " + COLUMN_URG_TAG + " IS 1 ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        }
        else{
            return false;
        }


    }

    public List<String> getPlan() {

        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TODO_TABLE + " WHERE " + COLUMN_IMP_TAG + " IS 1 AND  " + COLUMN_URG_TAG + " IS 0 ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            do {
                String do_task = cursor.getString(1);
                returnList.add(do_task);

            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;

    }

    public boolean deletePlan() {

        String queryString = "DELETE FROM " + TODO_TABLE + " WHERE " + COLUMN_IMP_TAG + " IS 1 AND  " + COLUMN_URG_TAG + " IS 0 ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        }
        else{
            return false;
        }


    }

    public List<String> getDelegate() {

        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TODO_TABLE + " WHERE " + COLUMN_IMP_TAG + " IS 0 AND  " + COLUMN_URG_TAG + " IS 1 ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            do {
                String do_task = cursor.getString(1);
                returnList.add(do_task);

            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;

    }

    public boolean deleteDelegate() {

        String queryString = "DELETE FROM " + TODO_TABLE + " WHERE " + COLUMN_IMP_TAG + " IS 0 AND " + COLUMN_URG_TAG + " IS 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        }
        else{
            return false;
        }


    }



    public List<String> getDelete() {

        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TODO_TABLE + " WHERE " + COLUMN_IMP_TAG + " IS 0 AND  " + COLUMN_URG_TAG + " IS 0 ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            do {
                String do_task = cursor.getString(1);
                returnList.add(do_task);

            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;

    }

    public boolean deleteDelete() {

        String queryString = "DELETE FROM " + TODO_TABLE + " WHERE " + COLUMN_IMP_TAG + " IS 0 AND " + COLUMN_URG_TAG + " IS 0 ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        }
        else{
            return false;
        }


    }
}
