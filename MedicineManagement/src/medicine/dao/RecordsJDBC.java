package medicine.dao;

import java.util.ArrayList;
import java.util.List;

import medicine.pojo.CustomerInfo;
import medicine.pojo.RecordDatas;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RecordsJDBC implements RecordsDAO, TableDetails {

	private final SQLiteDatabase db ;
	private final CustomerInfoDAO custDAO;
	
	public RecordsJDBC(SQLiteDatabase database, CustomerInfoDAO customerInfoDAO) {
		this.db = database;
		this.custDAO = customerInfoDAO;
	}

	@Override
	public boolean createRecords(RecordDatas record) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(RECORDS_CUSTID, record.getCustId());
		cv.put(RECORDS_CUSTNAME, record.getCustName());
		cv.put(RECORDS_LASTTIME, record.getLastTime());
		cv.put(RECORDS_NEXTTIME, record.getNextTime());
		cv.put(RECORDS_SURPLUS, record.getSurplus());
		cv.put(RECORDS_TIMES, record.getTime());
		long result = db.insert(TABLENAME_RECORDS, null, cv);
		if(result == -1) return false;
		return true;
	}

	@Override
	public List<RecordDatas> listRecordsById(int custId) {
		// TODO Auto-generated method stub
		List<RecordDatas> resords = new ArrayList<RecordDatas>();
		String[] columns = {RECORDS_CUSTID, RECORDS_LASTTIME, RECORDS_TIMES};
		Cursor cursor = db.query(TABLENAME_RECORDS, columns, "cust_id=?", new String[] {String.valueOf(custId)}, null, null, RECORDS_NEXTTIME);
		if(cursor.getCount() != 0) {
			cursor.moveToFirst();
			for(int i = 0; i < cursor.getCount(); i++) {
				CustomerInfo cust = custDAO.getCustomer(cursor.getInt(0));
				if(cust == null) return resords;
				resords.add(new RecordDatas(cust, cursor.getString(1), cursor.getInt(2)));
				cursor.moveToNext();
			}
		}
		return resords;
	}

	@Override
	public List<RecordDatas> listRecordsByTime() {
		// TODO Auto-generated method stub
		final String[] columns = new String[] {RECORDS_CUSTID, RECORDS_LASTTIME, RECORDS_TIMES};
		final String selection = "id IN (SELECT MAX(id) FROM records GROUP BY cust_id)";
		List<RecordDatas> resords = new ArrayList<RecordDatas>();
		Cursor cursorId = db.query(TABLENAME_RECORDS, columns, selection, null, null, null, RECORDS_NEXTTIME);
		if(cursorId.getCount() != 0) {
			cursorId.moveToFirst();
			for(int i = 0; i < cursorId.getCount(); i++) {
				CustomerInfo cust = custDAO.getCustomer(cursorId.getInt(0));
				System.out.println(cursorId.getInt(0));				//********************************************
				if(cust == null) continue;
				resords.add(new RecordDatas(cust, cursorId.getString(1), cursorId.getInt(2)));
				cursorId.moveToNext();
			}
		}
		return resords;
	}
	
	@Override
	public boolean deleteRecords(int id) {
		// TODO Auto-generated method stub
		int result = db.delete(TABLENAME_RECORDS, RECORDS_CUSTID+"=?", new String[] {String.valueOf(id)});
		if(result <= 0) return false;
		return true;
	}

}
