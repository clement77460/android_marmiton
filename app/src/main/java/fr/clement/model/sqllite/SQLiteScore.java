package fr.clement.model.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SQLiteScore extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MarmitonLike.db";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_LABEL + " TEXT," +
                    FeedEntry.COLUMN_NAME_KCAL + " INTEGER," +
                    FeedEntry.COLUMN_NAME_FIBRE + " REAL," +
                    FeedEntry.COLUMN_NAME_GLUCIDE + " REAL," +
                    FeedEntry.COLUMN_NAME_LIPIDE + " REAL," +
                    FeedEntry.COLUMN_NAME_PORTION_MOYENNE+ " REAL)";

    public SQLiteScore(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }





    //Package scope
    static class FeedEntry implements BaseColumns {
        static final String TABLE_NAME = "ingredient";
        static final String COLUMN_NAME_LABEL = "label";
        static final String COLUMN_NAME_KCAL = "kcal";
        static final String COLUMN_NAME_FIBRE = "fibre";
        static final String COLUMN_NAME_GLUCIDE = "glucide";
        static final String COLUMN_NAME_LIPIDE = "lipide";
        static final String COLUMN_NAME_PORTION_MOYENNE = "portion_moyenne";
    }

}

