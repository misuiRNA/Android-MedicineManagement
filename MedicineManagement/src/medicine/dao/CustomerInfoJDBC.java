package medicine.dao;

import java.util.ArrayList;
import java.util.List;

import medicine.pojo.CustomerInfo;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CustomerInfoJDBC implements CustomerInfoDAO, TableDetails {
	
	private SQLiteDatabase db;
	
	public CustomerInfoJDBC(SQLiteDatabase database) {
		setDataBase(database);
	}
	
	@Override
	public void setDataBase(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		this.db = database;
	}

	@Override
	public boolean createCustomer(CustomerInfo customer) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(CUSTINFO_FIRSTTIME, customer.getFirstTime());
		cv.put(CUSTINFO_FREQUENCY, customer.getFrequency());
//		cv.put(CUSTINFO_ID, customer.getId());
		cv.put(CUSTINFO_NAME, customer.getName());
		cv.put(CUSTINFO_REMARK, customer.getRemark());
		cv.put(CUSTINFO_TEL, customer.getTel());
		cv.put(CUSTINFO_TOTAL, customer.getTotal());
		long result = db.insert(TABLENAME_CUSTIFO, null, cv);
		if(result < 0) return false;
		return true;
	}

	@Override
	public List<CustomerInfo> listCustomers() {
		// TODO Auto-generated method stub
		List<CustomerInfo> customers = new ArrayList<CustomerInfo>();
		Cursor cursor = db.query(TABLENAME_CUSTIFO, null, null, null, null, null, CUSTINFO_ID);
		if(cursor.getCount() != 0) {
			cursor.moveToFirst();
			for(int i = 0; i < cursor.getCount(); i++) {
				customers.add(new CustomerInfo(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6)));
				cursor.moveToNext();
			}
		}
		return customers;
	}

	@Override
	public List<CustomerInfo> listCustomersByName(String name) {
		// TODO Auto-generated method stub
		List<CustomerInfo> customers = new ArrayList<CustomerInfo>();
		Cursor cursor = db.query(TABLENAME_CUSTIFO, null, "name=?", new String[] {name}, null, null, CUSTINFO_ID);
		if(cursor.getCount() != 0) {
			cursor.moveToFirst();
			for(int i = 0; i < cursor.getCount(); i++) {
				customers.add(new CustomerInfo(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6)));
				cursor.moveToNext();
			}
		}
		return customers;
	}

	@Override
	public CustomerInfo getCustomer(int customerId) {
		// TODO Auto-generated method stub
		CustomerInfo customer = null;
		Cursor cursor = db.query(TABLENAME_CUSTIFO, null, "id=?", new String[] {String.valueOf(customerId)}, null, null, null);
		if(cursor.getCount() != 0) {
			cursor.moveToFirst();
			customer = new CustomerInfo(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
					cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
		}
		return customer;
	}

	@Override
	public boolean updateCustomer(CustomerInfo customer) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(CUSTINFO_FIRSTTIME, customer.getFirstTime());
		cv.put(CUSTINFO_FREQUENCY, customer.getFrequency());
		cv.put(CUSTINFO_ID, customer.getId());
		cv.put(CUSTINFO_NAME, customer.getName());
		cv.put(CUSTINFO_REMARK, customer.getRemark());
		cv.put(CUSTINFO_TEL, customer.getTel());
		cv.put(CUSTINFO_TOTAL, customer.getTotal());
		int result = db.update(TABLENAME_CUSTIFO, cv, "id=?", new String[] {String.valueOf(customer.getId())});
		if(result <= 0) return false;
		return true;
	}

	@Override
	public boolean deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
		int result = db.delete(TABLENAME_CUSTIFO, "id=?", new String[] {String.valueOf(customerId)});
		if(result <= 0) return false;
		return true;
	}

	@Override
	public int getNewCustId() {
		// TODO Auto-generated method stub
		Cursor cursor = db.query(TABLENAME_CUSTIFO, null, null, null, null, null, null);
		int result = cursor.getCount();
		if(result == 0) return 1;
		cursor = db.query(TABLENAME_CUSTIFO, new String[]{CUSTINFO_MAXID}, null, null, null, null, null);
		if(cursor.getCount() == 0) return -1;
		cursor.moveToFirst();
		result = cursor.getInt(0);
		return result + 1;
	}

}
