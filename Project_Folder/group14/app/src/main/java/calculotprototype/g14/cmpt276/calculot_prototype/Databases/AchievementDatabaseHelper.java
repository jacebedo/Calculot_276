package calculotprototype.g14.cmpt276.calculot_prototype.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AchievementDatabaseHelper extends SQLiteOpenHelper {

    // DATABASE INFOMRATION
    private static final String DATABASE_NAME = "Achievement.db";
    private static final int DATABASE_VERSION = 1;

    // TABLE INFOMRATION
    private static final String TABLE_NAME = "achievements";
    private static final String TABLE_ID = "_id";
    private static final String TABLE_USERNAME = "username";
    private static final String TABLE_ACH1 = "ach_1";
    private static final String TABLE_ACH2 = "ach_2";
    private static final String TABLE_ACH3 = "ach_3";
    private static final String TABLE_ACH4 = "ach_4";
    private static final String TABLE_ACH5 = "ach_5";
    private static final String TABLE_ACH6 = "ach_6";
    private static final String TABLE_ACH7 = "ach_7";
    private static final String TABLE_ACH8 = "ach_8";

    private static final String STATEMENT = "CREATE TABLE " + TABLE_NAME + "(" +
            TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TABLE_USERNAME + " TEXT NOT NULL," +
            TABLE_ACH1 + " INTEGER NOT NULL," +
            TABLE_ACH2 + " INTEGER NOT NULL," +
            TABLE_ACH3 + " INTEGER NOT NULL," +
            TABLE_ACH4 + " INTEGER NOT NULL," +
            TABLE_ACH5 + " INTEGER NOT NULL," +
            TABLE_ACH6 + " INTEGER NOT NULL," +
            TABLE_ACH7 + " INTEGER NOT NULL," +
            TABLE_ACH8 + " INTEGER NOT NULL" +
            ");";



    public AchievementDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STATEMENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // New user input ( PUT WHEN THE USER IS FIRST REGISTERED.)
    public long addUser(String _username){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TABLE_USERNAME, _username);
        cv.put(TABLE_ACH1, 1);
        cv.put(TABLE_ACH2, 0);
        cv.put(TABLE_ACH3, 0);
        cv.put(TABLE_ACH4, 0);
        cv.put(TABLE_ACH5, 0);
        cv.put(TABLE_ACH6, 0);
        cv.put(TABLE_ACH7, 0);
        cv.put(TABLE_ACH8, 0);

        long retval = db.insert(TABLE_NAME, null, cv);
        db.close();
        return retval;

    }

}
