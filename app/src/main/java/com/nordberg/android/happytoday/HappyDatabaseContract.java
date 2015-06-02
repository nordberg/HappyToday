package com.nordberg.android.happytoday;

import android.provider.BaseColumns;

/**
 * Created by Marcus on 2015-06-02.
 */
public final class HappyDatabaseContract {
    public HappyDatabaseContract() {}

    public static abstract class HappyEntry implements BaseColumns {
        public static final String TABLE_NAME = "happymoments";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DATE = "date";
    }
}

