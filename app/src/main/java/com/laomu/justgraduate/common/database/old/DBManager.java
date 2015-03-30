/**
 * 
 */
package com.laomu.justgraduate.common.database.old;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import com.laomu.justgraduate.utils.LogUtils;

import java.io.IOException;
import java.sql.SQLException;

import static com.laomu.justgraduate.common.database.old.DatabaseHelper.DATABASE_PATH;


/**
 * @author luoyuan.myp
 * 代码已废弃
 * 全部使用OrmDbManeger.getInstance()
 *         2013-6-22
 */
@Deprecated
public class DBManager {
	private DatabaseHelper myDbHelper;
	private SQLiteDatabase db;
	private static DBManager instance ;
    private String LOG_TAG  = "DBManager";

    /**
	 * construct
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private DBManager(Context c) {

        myDbHelper = new DatabaseHelper(c);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            openDataBase();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        db = myDbHelper.getWritableDatabase();
    }

	public static DBManager instance(Context c){
		if(instance == null){
			instance = new DBManager(c);
		}
		return instance;
	}


	/**
	 * query all notebeans, return cursor
	 *
	 * @return Cursor
	 */
	public Cursor queryTheCursor() {
//        db.execSQL();
		Cursor c = db.rawQuery("SELECT * FROM userinfo", null);
		return c;
	}


    /**
     * query all notebeans, return cursor
     *
     * @return Cursor
     */
    public Cursor execsql(String sql) {
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public void openDataBase() throws SQLException {
        LogUtils.d(LOG_TAG, "openDataBase");
        // Open the database
        String myPath = DATABASE_PATH +  DatabaseHelper.DATABASE_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);
    }
}
