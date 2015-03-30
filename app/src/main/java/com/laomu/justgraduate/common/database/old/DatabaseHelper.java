/**
 *
 */
package com.laomu.justgraduate.common.database.old;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.laomu.justgraduate.application.JGApplication;
import com.laomu.justgraduate.utils.LogUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * @author luoyuan.myp
 *         <p/>
 *         2013-6-22
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * @param context
     * @param name
     * @param factory
     * @param version
     */

    public static final String DATABASE_NAME = "mygraduate.db";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_PATH = "/data/data/com.laomu.justgraduate/databases/";
    private String LOG_TAG  = "db_helper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
     * .SQLiteDatabase)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

//        db.execSQL("CREATE TABLE IF NOT EXISTS `userinfo` (\n" +
//                "  `id` int(11) NOT NULL ,\n" +
//                "  `uid` varchar(50) NOT NULL,\n" +
//                "  `upassword` varchar(50) NOT NULL,\n" +
//                "  `uname` varchar(50) DEFAULT NULL,\n" +
//                "  `male` varchar(50) DEFAULT NULL,\n" +
//                "  `face_logo` varchar(100) DEFAULT NULL,\n" +
//                "  `tel` varchar(50) DEFAULT NULL,\n" +
//                "  `signup_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',\n" +
//                "  `school_name` varchar(50) DEFAULT NULL,\n" +
//                "  `school_id` varchar(50) DEFAULT NULL,\n" +
//                "  `department` varchar(50) DEFAULT NULL,\n" +
//                "  `class` varchar(50) DEFAULT NULL,\n" +
//                "  `grade` varchar(50) DEFAULT NULL,\n" +
//                "  `degree` varchar(50) DEFAULT NULL,\n" +
//                "  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
//                "  PRIMARY KEY (`id`)\n" +
//                ")");
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
     * .SQLiteDatabase, int, int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE note ADD COLUMN other STRING");
    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // database does't exist yet.
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }


    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     * */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        LogUtils.d(LOG_TAG, "dbExist: " + dbExist);

        if (dbExist) {
            // do nothing - database already exist
        } else {
            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

    }



    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException {
        LogUtils.d(LOG_TAG, "copyDataBase");
        // Open your local db as the input stream
        InputStream myInput = JGApplication.appContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DATABASE_PATH + DATABASE_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }



}
