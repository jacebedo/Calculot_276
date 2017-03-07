package calculotprototype.g14.cmpt276.calculot_prototype.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ephronax on 3/7/2017.
 */

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="user.db";
    private static final int DATABASE_VERSION = 1;

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(null/*insert statmeent here*/);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
