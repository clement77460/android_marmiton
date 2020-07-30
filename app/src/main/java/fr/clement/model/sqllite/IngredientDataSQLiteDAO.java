package fr.clement.model.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.clement.entities.Ingredient;

public class IngredientDataSQLiteDAO {

    private SQLiteScore sqLiteScore;

    public IngredientDataSQLiteDAO(SQLiteScore sqLiteScore){
        this.sqLiteScore= sqLiteScore;
    }

    public void insertScore(){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.sqLiteScore.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SQLiteScore.FeedEntry.COLUMN_NAME_LABEL, "tomatoooo");
        values.put(SQLiteScore.FeedEntry.COLUMN_NAME_KCAL, 25.0);
        values.put(SQLiteScore.FeedEntry.COLUMN_NAME_FIBRE, 2.0);
        values.put(SQLiteScore.FeedEntry.COLUMN_NAME_GLUCIDE, 0.0);
        values.put(SQLiteScore.FeedEntry.COLUMN_NAME_LIPIDE, 0.0);
        values.put(SQLiteScore.FeedEntry.COLUMN_NAME_PORTION_MOYENNE, 100.0);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(SQLiteScore.FeedEntry.TABLE_NAME, null, values);
        /*
        // Keep only last 10 rows
        String whereClause=SQLiteScore.FeedEntry._ID+" NOT IN (SELECT "+SQLiteScore.FeedEntry._ID+" FROM "+SQLiteScore.FeedEntry.TABLE_NAME+" ORDER BY "+SQLiteScore.FeedEntry._ID+" DESC LIMIT 10)";
        db.delete(SQLiteScore.FeedEntry.TABLE_NAME,whereClause,null);
        */
    }
    public List<Ingredient> getAllMatchData(){
        SQLiteDatabase db = this.sqLiteScore.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                SQLiteScore.FeedEntry.COLUMN_NAME_LABEL,
                SQLiteScore.FeedEntry.COLUMN_NAME_KCAL,
                SQLiteScore.FeedEntry.COLUMN_NAME_FIBRE,
                SQLiteScore.FeedEntry.COLUMN_NAME_GLUCIDE,
                SQLiteScore.FeedEntry.COLUMN_NAME_LIPIDE,
                SQLiteScore.FeedEntry.COLUMN_NAME_PORTION_MOYENNE,
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = SQLiteScore.FeedEntry._ID+ " DESC";

        Cursor cursor = db.query(
                SQLiteScore.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List<Ingredient> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            Log.i("test", cursor.getString(cursor.getColumnIndex(SQLiteScore.FeedEntry.COLUMN_NAME_LABEL)));
            items.add(new Ingredient(
                    cursor.getString(cursor.getColumnIndex(SQLiteScore.FeedEntry.COLUMN_NAME_LABEL)),
                    cursor.getString(cursor.getColumnIndex(SQLiteScore.FeedEntry.COLUMN_NAME_KCAL))
            ));
        }
        cursor.close();
        return items;

    }

    public void deleteEntries(String label){
        String[] selctionArg = {String.valueOf(label)};
        this.sqLiteScore.getWritableDatabase().delete(SQLiteScore.FeedEntry.TABLE_NAME,SQLiteScore.FeedEntry.COLUMN_NAME_LABEL+"=?",selctionArg);
    }

    public void close(){
        if(this.sqLiteScore !=null){
            this.sqLiteScore.close();
        }
    }
}
