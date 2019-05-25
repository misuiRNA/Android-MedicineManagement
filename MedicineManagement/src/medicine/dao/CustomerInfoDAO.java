package medicine.dao;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import medicine.pojo.CustomerInfo;

public interface CustomerInfoDAO {
	
	public void setDataBase(SQLiteDatabase database);
	
	public boolean createCustomer(CustomerInfo customer);
	
	public List<CustomerInfo> listCustomers();
	
	public List<CustomerInfo> listCustomersByName(String name);
	
	public CustomerInfo getCustomer(int customerId);
		
	public boolean updateCustomer(CustomerInfo customer);
	
	public boolean deleteCustomer(int customerId);
	
	public int getNewCustId();
	
}
