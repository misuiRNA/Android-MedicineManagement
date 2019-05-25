package medicine.activity;


import medicine.activity.R;
import medicine.dao.CustomerInfoDAO;
import medicine.dao.CustomerInfoJDBC;
import medicine.dao.RecordsDAO;
import medicine.dao.RecordsJDBC;
import medicine.dao.TableDetails;
import medicine.pojo.CustomerInfo;
import medicine.pojo.RecordDatas;
import medicine.tools.DateTools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class CustInfoEditActivity extends Activity {
	private SQLiteDatabase db = null;
	private CustomerInfoDAO custDAO = null;
	private RecordsDAO recordDAO= null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custinfo);
		createDB();							//设置数据库
		
		Bundle bundle = this.getIntent().getExtras();
        setCustInfo(bundle);				//
	}	
	
	/**
     * 设置数据库
     */
    private void createDB() {
    	this.db = openOrCreateDatabase(TableDetails.DATABASENAME, MODE_PRIVATE, null);
    	this.custDAO = new CustomerInfoJDBC(db);
    	this.recordDAO = new RecordsJDBC(db, custDAO);
    }
    
    private void setCustInfo(final Bundle bundle) {
    	final EditText editCustId = (EditText)findViewById(R.id.edit_custid);
		final EditText editCustName = (EditText)findViewById(R.id.edit_custname);
		final EditText editCustTel = (EditText)findViewById(R.id.edit_custtel);
		final EditText editCustFrequency = (EditText)findViewById(R.id.edit_custfrequency);
		final EditText editCustTotal = (EditText)findViewById(R.id.edit_custtotal);
		final EditText editCustRemark = (EditText)findViewById(R.id.edit_custremark);
    	final EditText editCustFirsttime = (EditText)findViewById(R.id.edit_custfirstTime);
    	Button btnSave = (Button)findViewById(R.id.cust_editbtnok);
		Button btnCancel = (Button)findViewById(R.id.cust_editbtncancle);
		editCustFirsttime.setText(DateTools.getCurrentDateAsString());
		editCustFirsttime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DatePickerDialog datePickerDialog = new DatePickerDialog(CustInfoEditActivity.this, 
						 new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    	editCustFirsttime.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                    }
                }, DateTools.getYear(editCustFirsttime.getText().toString()), 
                	DateTools.getMonth(editCustFirsttime.getText().toString()), 
                	DateTools.getDay(editCustFirsttime.getText().toString()));
		        datePickerDialog.show();
			}});
		
		final Builder dialog = new AlertDialog.Builder(CustInfoEditActivity.this)
				.setTitle("输入内容有误！")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {}});
		if(bundle != null) {
			btnCancel.setText("删除");
			int custId = bundle.getInt("custID");
			CustomerInfo customer = custDAO.getCustomer(custId);
			editCustId.setText(String.valueOf(custId));
			editCustName.setText(customer.getName());
			editCustTel.setText(customer.getTel());
			editCustFirsttime.setText(customer.getFirstTime());
			editCustFrequency.setText(String.valueOf(customer.getFrequency()));
			editCustTotal.setText(String.valueOf(customer.getTotal()));
			editCustRemark.setText(customer.getRemark());
		}else {
			editCustId.setText(String.valueOf(custDAO.getNewCustId()));
		}
		final int newCustId = Integer.parseInt(editCustId.getText().toString());
		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String custName = editCustName.getText().toString();
				if("".equals(custName)) {
					dialog.show();
					return;
				}
				String custRemark = editCustRemark.getText().toString();
				String custTel = editCustTel.getText().toString();
				String custFirstTime = editCustFirsttime.getText().toString();
				if("".equals(editCustTotal.getText().toString()) || "".equals(editCustFrequency.getText().toString())) {
					dialog.show();
					return;
				}
				int custFrequency = Integer.parseInt(editCustFrequency.getText().toString());
				int custTotal = Integer.parseInt(editCustTotal.getText().toString());
				CustomerInfo customer = new CustomerInfo(newCustId, custName, custTel, custRemark, custFirstTime, custFrequency, custTotal);
				RecordDatas record = new RecordDatas(customer, custFirstTime, 1);
				if(bundle == null) {
					if(custDAO.createCustomer(customer)) {
						if(recordDAO.createRecords(record)) {
							Toast.makeText(getApplicationContext(), "客户新建成功！", Toast.LENGTH_SHORT).show();
							CustInfoEditActivity.this.finish();
						}else {
							Toast.makeText(getApplicationContext(), "记录插入失败！", Toast.LENGTH_SHORT).show();
						}
					}else {
						Toast.makeText(getApplicationContext(), "客户新建失败！", Toast.LENGTH_SHORT).show();
					}
				}else {
					if(custDAO.updateCustomer(customer)) {
						recordDAO.deleteRecords(newCustId);
						if(recordDAO.createRecords(record)) {
							Toast.makeText(getApplicationContext(), "客户信息修改成功！", Toast.LENGTH_SHORT).show();
							CustInfoEditActivity.this.finish();
						}else {
							Toast.makeText(getApplicationContext(), "记录插入失败！", Toast.LENGTH_SHORT).show();
						}
					}else {
						Toast.makeText(getApplicationContext(), "客户新建失败！", Toast.LENGTH_SHORT).show();
					}
				}
			}});
		
		
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(bundle == null) {
					CustInfoEditActivity.this.finish();
					Toast.makeText(getApplicationContext(), "删除失败！", Toast.LENGTH_SHORT).show();
				}else {
					//*******************************************************************************
					int custId = Integer.parseInt(editCustId.getText().toString());
					if(custDAO.deleteCustomer(custId)) {
						recordDAO.deleteRecords(custId);
						CustInfoEditActivity.this.finish();
						Toast.makeText(getApplicationContext(), "删除成功！", Toast.LENGTH_SHORT).show();
					}
					
				}
			}});
    }
}
