package com.example.mihai.quarto;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "profilesManager";
    private static final String TABLE_PROFILES = "profiles";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_PROFILE = "profile";
    private static final String KEY_PASS = "pass";
    int setID  ;

    public DatabaseHandler(Context context){
         super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROFILES_TABLE = "CREATE TABLE " + TABLE_PROFILES +"("
                + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SURNAME + " TEXT," + KEY_PROFILE + " TEXT, "
                + KEY_PASS + " TEXT" +")";
        db.execSQL(CREATE_PROFILES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);

        onCreate(db);
    }

    void addProfile(Profile profile){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, profile.getName());
        values.put(KEY_SURNAME, profile.getSurname());
        values.put(KEY_PROFILE, profile.getProfile());
        values.put(KEY_PASS, profile.getPass());

        db.insert(TABLE_PROFILES, null, values);
        db.close();
    }

    Profile getProfile(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROFILES, new String[] {KEY_ID, KEY_NAME, KEY_SURNAME, KEY_PROFILE, KEY_PASS}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);



          if (cursor != null){
            cursor.moveToFirst();
        }
        Profile profile = new Profile(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return profile;

    }

    public List<Profile> getAllProfiles(){
      List<Profile> profileList = new ArrayList<>();

      String selectQuery = "SELECT * FROM " + TABLE_PROFILES;

      SQLiteDatabase  db = this.getWritableDatabase();
      Cursor cursor = db.rawQuery(selectQuery,null);

      if (cursor.moveToFirst()){
          do {
              Profile profile = new Profile();
                 if (cursor.getString(0) != null){
                  try{
                      int setID = Integer.parseInt(cursor.getString(0));

                  } catch(NumberFormatException e){
                   profile.setId(Integer.parseInt(String.valueOf(setID)));
                  }

              }

              // profile.setId(Integer.parseInt(cursor.getString(0)));
              profile.setName(cursor.getString(1));
              profile.setSurname(cursor.getString(2));
              profile.setProfile(cursor.getString(3));
              profile.setPass(cursor.getString(4));

              profileList.add(profile);
          } while (cursor.moveToNext());
      }
        return profileList;
    }


    public int updateProfile(Profile profile){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, profile.getName());
        values.put(KEY_SURNAME, profile.getSurname());
        values.put(KEY_PROFILE, profile.getProfile());
        values.put(KEY_PASS, profile.getPass());

        return db.update(TABLE_PROFILES, values, KEY_ID + "=?",
                                new String[]{String.valueOf(profile.getId())});
    }

     public void deleteProfile(Profile profile){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PROFILES, KEY_ID  + "=?",
                new String[]{String.valueOf(profile.getId())});
        db.close();
     }

     public int getProfileCount(){
        String countQuery = "SELECT * FROM " + TABLE_PROFILES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return  cursor.getCount();
     }
}
