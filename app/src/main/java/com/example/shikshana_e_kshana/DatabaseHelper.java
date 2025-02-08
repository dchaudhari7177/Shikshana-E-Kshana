package com.example.shikshana_e_kshana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ProfileDB";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_PROFILES = "profiles";
    static final String COLUMN_ID = "id";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_CLASS = "class";
    static final String COLUMN_DESCRIPTION = "description";
    static final String COLUMN_IMAGE = "image";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_PROFILES + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_CLASS + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_IMAGE + " BLOB);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
        onCreate(db);
    }

    public long insertProfile(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, profile.getName());
        values.put(COLUMN_CLASS, profile.getClassName());
        values.put(COLUMN_DESCRIPTION, profile.getDescription());
        values.put(COLUMN_IMAGE, profile.getImageBytes());
        return db.insert(TABLE_PROFILES, null, values);
    }

    public Profile getProfile() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROFILES,
                new String[]{COLUMN_NAME, COLUMN_CLASS, COLUMN_DESCRIPTION, COLUMN_IMAGE},
                null, null, null, null,
                COLUMN_ID + " DESC",
                "1");

        if (cursor != null && cursor.moveToFirst()) {
            Profile profile = new Profile(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLASS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                    cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_IMAGE))
            );
            cursor.close();
            return profile;
        }
        return null;
    }
}