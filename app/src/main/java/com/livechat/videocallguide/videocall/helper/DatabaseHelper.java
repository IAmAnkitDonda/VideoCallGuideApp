package com.livechat.videocallguide.videocall.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "VIDEO_CALL_DB";
    public static final String TBNAME = "CONTACT_TABLE";
    public static final String COL1 = "ID";
    public static final String COL2 = "PHOTO_PATH";
    public static final String COL3 = "VIDEO_PATH";
    public static final String COL4 = "NAME";
    public static final String COL5 = "NUMBER";
    public static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable1 = "CREATE TABLE " + TBNAME
                + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT, "
                + COL3 + " TEXT, "
                + COL4 + " TEXT, "
                + COL5 + " TEXT) ";
        db.execSQL(createTable1);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TBNAME);
    }

    /* Add Place In Fav_QUOTE_Table */
    public boolean addDataInFavTable(String PHOTO_PATH, String VIDEO_PATH, String NAME, String NUMBER) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL1, wId);
        contentValues.put(COL2, PHOTO_PATH);
        contentValues.put(COL3, VIDEO_PATH);
        contentValues.put(COL4, NAME);
        contentValues.put(COL5, NUMBER);
        Log.e(TAG, "Insert Data :" + NAME);
        long result = db.insert(TBNAME, null, contentValues);
        if (result == -1) {
            return false;
        } else return true;
    }

    /* Get All Fav_Place_Data */
    public Cursor getFavData(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + table, null);
        return data;
    }

    /* Check Data Status */
    public boolean checkFavStatus(String qId) {
        SQLiteDatabase db = getWritableDatabase();
        String checkQuery = "SELECT * FROM " + TBNAME + " WHERE " + COL1 + " ='" + qId + "'";
        Cursor data = db.rawQuery(checkQuery, null);
        if (data.getCount() != 0) {
            return true;
        } else return false;
    }

    /* Delete Quotes From Fav_Place_Table */
    public void deleteFavData(int qId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteData = "DELETE FROM " + TBNAME + " WHERE " + COL1 + " ='" + qId + "'";
        Log.e(TAG, "Delete:" + qId);
        db.execSQL(deleteData);
    }


}
