package medicine.activity;

import java.util.List;

import medicine.activity.R;
import medicine.dao.CustomerInfoDAO;
import medicine.dao.RecordsDAO;
import medicine.pojo.RecordDatas;
import medicine.tools.DateTools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainListViewAdapter extends ArrayAdapter {
	private final int resourceId;
	private final MainActivity mainActive;
	private final SQLiteDatabase db;
	private final CustomerInfoDAO custDAO;
	private final RecordsDAO recordDAO;
	
	public MainListViewAdapter(Context context, int resource, List<RecordDatas> objects) {
		super(context, resource, objects);
		mainActive = (MainActivity) context;
		db = mainActive.getDB();
		custDAO = mainActive.getCustDAO();
		recordDAO = mainActive.getRecordDAO();
		resourceId = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final RecordDatas record = (RecordDatas) getItem(position);							//获取数据实例
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);		//实例化一个对象
		final TextView idText = (TextView) view.findViewById(R.id.idText_record);
		final TextView nameText = (TextView) view.findViewById(R.id.nameText_record);
		final TextView timeText = (TextView) view.findViewById(R.id.nextTimeText_record);
		final TextView surplusText = (TextView) view.findViewById(R.id.surplusText_record);
		final ImageButton editBut = (ImageButton) view.findViewById(R.id.editButton_record);
		idText.setText(String.valueOf(record.getCustId()));
		nameText.setText(record.getCustName());
		timeText.setText(record.getNextTime());
		surplusText.setText(String.valueOf(record.getSurplus()));
		editBut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(record.getSurplus() <= 1) {
					resetRecord(record);
				}else {
					setRecord(record);
				}
			}
		});
		
		nameText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listRecordByName(Integer.parseInt(idText.getText().toString()));
			}});
		
		nameText.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				editCustDetails(Integer.parseInt(idText.getText().toString()));
				return true;
			}});
		
		return view;
	}
	
	/**
	 * 当用户药物足够时，正常处理方法
	 * @param record
	 */
	private void setRecord(final RecordDatas record) {
		String message = "姓名： " + record.getCustName() + "\n上次贴药时间：" + record.getLastTime() + "\n本次贴药时间：" + record.getNextTime() 
				+ "\n已贴  " + record.getTime() + " 次，剩余 " + record.getSurplus() + " 次";
		new AlertDialog.Builder(mainActive).setTitle("敷贴确认")
		 .setMessage(message)
		 .setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {}})
		  .setPositiveButton("确定敷贴", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				record.setNextTime(DateTools.getCurrentDateAsString());
				Toast.makeText(getContext(), "操作完成！", Toast.LENGTH_SHORT).show();
				createRecords(record);
				mainActive.onCreate(null);
			}})
		  .show();
	}
	/**
	 * 当用户药物不足时，要求重置用户信息
	 * @param record
	 */
	private void resetRecord(final RecordDatas record) {
		String title = record.getCustName() + "  剩余贴药次数为0\n是否继续贴药？";
		new AlertDialog.Builder(mainActive).setTitle(title)
		 .setNegativeButton("删除信息", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				deleteRecords(record);
				Toast.makeText(getContext(), "删除成功！", Toast.LENGTH_SHORT).show();
				mainActive.onCreate(null);
			}})
		  .setPositiveButton("继续敷贴", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				/**************打开用户编辑页面********************/
				Toast.makeText(getContext(), "正在操作！！", Toast.LENGTH_SHORT).show();
				resetCustInfo(record.getCustId());
			}})
		  .show();		
	}
	
	private void listRecordByName(int custId) {
		List<RecordDatas> records = recordDAO.listRecordsById(custId);
		StringBuffer resultBuf = new StringBuffer();
		for(int i = 0; i < records.size(); i++) {
			resultBuf.append(records.get(records.size() - i - 1).toString());
		}
		String message = resultBuf.toString();
		String title = "用户贴药记录";
		new AlertDialog.Builder(mainActive).setTitle(title)
		 .setMessage(message)
		 .setNegativeButton("返回", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {}})
		  .show();
	}
	
	private void editCustDetails(int custId) {
		Intent intent = new Intent();
		intent.setClass(mainActive, CustInfoEditActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("custID", custId);
		intent.putExtras(bundle);
		mainActive.startActivity(intent);
	}
	
	private boolean createRecords(RecordDatas record) {
		RecordDatas newRecord = new RecordDatas(record.getCustomerInfo(), record.getNextTime(), record.getTime()+1);
		return recordDAO.createRecords(newRecord);
	}
	
	private boolean deleteRecords(RecordDatas record) {
		boolean deR = recordDAO.deleteRecords(record.getCustId());
		boolean deC = custDAO.deleteCustomer(record.getCustId());
		return (deR && deC);
	}
	
	private void resetCustInfo (int custId) {
		/**************打开用户编辑页面********************/
		editCustDetails(custId);
	}
}
