package com.example.ivan.dbexplore.data;

import android.provider.BaseColumns;

/**
 * Created by ivan on 07.11.17.
 */

public final class PersonContract {

    private PersonContract(){}

    public static final class PersonEntry {
        public final static String TABLE_NAME = "PersonTable";
        public final static String _ID = "_id";
        public final static String COLUMN_PERSON_FIRST_NAME = "firstName";
        public final static String COLUMN_PERSON_SECOND_NAME = "secondName";
        public final static String COLUMN_PERSON_AGE = "age";

    }

}
