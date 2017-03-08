package calculotprototype.g14.cmpt276.calculot_prototype.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ephronax on 3/7/2017.
 */

public class UserDatabaseHelper extends SQLiteOpenHelper {

    // DATABASE META INFORMATION
    private static final String DATABASE_NAME="user.db";
    private static final int DATABASE_VERSION = 1;

    // TABLE META INFORMATION
    private static final String TABLE_NAME = "User-Information";
    private static final String TABLE_ID = "_id";
    private static final String TABLE_USERNAME = "Username";
    private static final String TABLE_FIRSTNAME = "First Name";
    private static final String TABLE_PASSWORD = "Password";
    private static final String TABLE_TOTALXP = "Total XP";
    private static final String TABLE_LEARNINGXP = "Learning XP";
    private static final String TABLE_PRACTICEXP = "Practice XP";

    // onCreate statement
    private static final String STATEMENT = "CREATE TABLE "  + TABLE_NAME + "( " +
            TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TABLE_USERNAME + " TEXT NOT NULL, " +
            TABLE_FIRSTNAME + " TEXT NOT NULL, " +
            TABLE_PASSWORD + " TEXT NOT NULL, " +
            TABLE_TOTALXP + " INTEGER NOT NULL, " +
            TABLE_LEARNINGXP + " INTEGER NOT NULL, " +
            TABLE_PRACTICEXP + " INTEGER NOT NULL " +
            ")";




    public UserDatabaseHelper(Context context) {
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


}
