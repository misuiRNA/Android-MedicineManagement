package medicine.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class InitDataBase implements TableDetails{
	
	private static InitDataBase initDatabase = null;
	private SQLiteDatabase db = null;
	
	private InitDataBase(SQLiteDatabase dataBase) {
		this.db = dataBase;
		init();
	}
	
	public static InitDataBase initDataBase(SQLiteDatabase datbase) {
		if(initDatabase == null) 	initDatabase = new InitDataBase(datbase);
		return initDatabase;
	}
	
	private void init() {
    	createTable();
    }
	
	
	private void createTable() {
    	if(db == null) {
    		System.out.println("<--------数据库不存在!-------->");
    		return;
    	}
    	String customerInfoSQL = "CREATE TABLE IF NOT EXISTS " + TABLENAME_CUSTIFO +
    			" (id INTEGER PRIMARY KEY, " +
    			"name VARCHAR(50), tel NVARCHAR(50), remark VARCHAR(50), " +
    			"firstTime DATE, frequency INTEGER, total INTEGER);";
    	db.execSQL(customerInfoSQL);
    	String recordsSQL = "CREATE TABLE IF NOT EXISTS " + TABLENAME_RECORDS +
    			 " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
    			 "cust_id INTEGER, cust_name VARCHAR(50), last_time DATE, next_time DATE, " +
    			 "times INTEGER, surplus INTEGER);";
    	db.execSQL(recordsSQL);
    }
		
	public String listTables() {
    	Cursor cursor = db.query("sqlite_master", new String[] {"name"}, "type=?", new String[] {"table"}, null, null, null);
    	String tables = "";
    	if(cursor.getCount() != 0) {
    		cursor.moveToFirst();
    		for(int i = 0; i < cursor.getCount(); i++) {
    			tables = tables + cursor.getString(0) + "\n";
    			cursor.moveToNext();
    		}
    	}
    	return tables;
    }
	
}
