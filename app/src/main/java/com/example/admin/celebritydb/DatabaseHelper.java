package com.example.admin.celebritydb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Person.db";
    public static final String TABLE_NAME = "Celebs";
    public static final String COLUMN_FIRST_NAME = "_id";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_FAVORITE = "FAVORITE";
    public static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_FIRST_NAME + " TEXT PRIMARY KEY," + COLUMN_LAST_NAME + " TEXT, Favorite TEXT)";
             sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override //called whenever we change the database version, not first creation where we originally set it, but if we change it again
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long savePerson(Celebrity person)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, person.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, person.getLastName());
        contentValues.put("Favorite", "N");
       long row =  db.insert(TABLE_NAME, null, contentValues);  //returns row number, returns -1 if failed
        return row;
    }
    public List<Celebrity> getPersonsAsList()
    {
        List<Celebrity> personList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results

        if(cursor.moveToFirst())
        {
            do {
                Celebrity person = new Celebrity(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                Log.d("DatabaseHelper", "getPersonsAsList: " + cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2));
                personList.add(person);
            }while(cursor.moveToNext());

        }
        return personList;
    }
    public Cursor getPersonsAsCursor()
    {
        List<Celebrity> personList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results
        return cursor;
    }
}
