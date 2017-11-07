package com.example.ivan.dbexplore.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.ivan.dbexplore.data.PersonContract.*;

/**
 * Created by ivan on 07.11.17.
 */

public class PersonHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERION = 1;
    public static final String DATABSE_NAME = "PersonDb";

    private static final String createDb = "create table " + PersonEntry.TABLE_NAME + "( "
            + PersonEntry._ID + " INTEGER, "
            + PersonEntry.COLUMN_PERSON_FIRST_NAME + " TEXT, "
            + PersonEntry.COLUMN_PERSON_SECOND_NAME + " TEXT, "
            + PersonEntry.COLUMN_PERSON_AGE + " INTEGER);";

    public PersonHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(createDb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
