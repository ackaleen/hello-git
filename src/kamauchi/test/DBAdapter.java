package kamauchi.test;

import java.util.Date;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

	static final String DATABASE_NAME = "my.db";
	static final int DATABASE_VERSION = 1;

	public static final String TABLE_NAME = "step_log";
	public static final String COL_ID = "_id";
	public static final String COL_NOTE = "note";
	public static final String COL_LASTUPDATE = "lastupdate";

	public static final String LOCATION_TABLE_NAME = "location_log";
	public static final String COL_LATITUDE = "latitude"; // 緯度
	public static final String COL_LONGITUDE = "longitude"; // 軽度
	public static final String COL_ALTITUDE = "altitude"; // 標高
	public static final String COL_ACCURACY = "accuracy"; // 精度
	public static final String COL_CREATEDATE = "createdate"; // 作成日時

	protected final Context context;
	protected DatabaseHelper dbHelper;
	protected SQLiteDatabase db;

	public DBAdapter(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(this.context);
	}

	//
	// SQLiteOpenHelper
	//
	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NOTE
					+ " TEXT NOT NULL," + COL_LASTUPDATE + " TEXT NOT NULL);");
			db.execSQL("CREATE TABLE " + LOCATION_TABLE_NAME + " (" + COL_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_LATITUDE
					+ " TEXT NOT NULL," + COL_LONGITUDE + " TEXT NOT NULL,"
					+ COL_ACCURACY + " TEXT NOT NULL," + COL_CREATEDATE
					+ " TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}

	}

	//
	// Adapter Methods
	//

	public DBAdapter open() {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	//
	// App Methods
	//

	public boolean deleteAllNotes() {
		return db.delete(TABLE_NAME, null, null) > 0;
	}

	public boolean deleteNote(int id) {
		return db.delete(TABLE_NAME, COL_ID + "=" + id, null) > 0;
	}

	public Cursor getAllNotes() {
		return db.query(TABLE_NAME, null, null, null, null, null, null);
	}

	public void saveNote(String note) {
		Date dateNow = new Date();
		ContentValues values = new ContentValues();
		values.put(COL_NOTE, note);
		values.put(COL_LASTUPDATE, dateNow.toLocaleString());
		db.insertOrThrow(TABLE_NAME, null, values);
	}
}