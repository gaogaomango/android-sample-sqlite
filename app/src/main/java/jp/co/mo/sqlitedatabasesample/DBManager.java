package jp.co.mo.sqlitedatabasesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class DBManager {
    public static final String DB_NAME = "SampleDatabase";
    public static final String TABLE_NAME_USER = "users";
    public static final String COL_ID = "id";
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_USER_PASSWOED = "user_password";

    private static final int DB_VERSION = 1;

    private SQLiteDatabase sQLiteDatabase;

    private static final String CREATE_TABLE = "Create table IF NOT EXISTS " + TABLE_NAME_USER +
            "(id integer PRIMARY KEY AUTOINCREMENT, " + COL_USER_NAME +
            " text, " + COL_USER_PASSWOED + " text);";
    private static final String DROP_TABLE = "DROP table IF EXISTS " + TABLE_NAME_USER + ";";

    public DBManager(Context context) {
        DatabaseHelperUser dbhelperUser = new DatabaseHelperUser(context);
        sQLiteDatabase = dbhelperUser.getWritableDatabase();
    }

    /**
     * insert data to SQLite
     * @param values
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insertData(ContentValues values) {
        return sQLiteDatabase.insert(TABLE_NAME_USER, "", values);
    }

    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(TABLE_NAME_USER);

        Cursor cursor = builder.query(sQLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    public int delete(String selection, String[] selectionArgs) {
        return sQLiteDatabase.delete(TABLE_NAME_USER, selection, selectionArgs);
    }


    static class DatabaseHelperUser extends SQLiteOpenHelper {

        public DatabaseHelperUser(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }


}
