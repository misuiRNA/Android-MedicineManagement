package medicine.activity;

import java.util.ArrayList;
import java.util.List;

import medicine.activity.R;
import medicine.dao.CustomerInfoDAO;
import medicine.dao.CustomerInfoJDBC;
import medicine.dao.InitDataBase;
import medicine.dao.RecordsDAO;
import medicine.dao.RecordsJDBC;
import medicine.dao.TableDetails;
import medicine.pojo.RecordDatas;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	@SuppressWarnings("unused")
	private InitDataBase initDataBase = null;
	private SQLiteDatabase db = null;
	private CustomerInfoDAO custDAO = null;
	private RecordsDAO recordDAO= null;
	private List<RecordDatas> records = new ArrayList<RecordDatas>();
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDB();
        
        listRecords();			//设置数据内容
        MainListViewAdapter adapter = new MainListViewAdapter(MainActivity.this, R.layout.records_item, records);
        ListView listView = (ListView) findViewById(R.id.mainListView);
        listView.setAdapter(adapter);
        
        Button btn = (Button) findViewById(R.id.btn01);
        btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CustInfoEditActivity.class);
				MainActivity.this.startActivity(intent);
			}});
    }
    /**
     * 设置数据库
     */
    private void createDB() {
    	this.db = openOrCreateDatabase(TableDetails.DATABASENAME, MODE_PRIVATE, null);
    	initDataBase = InitDataBase.initDataBase(db);
        custDAO = new CustomerInfoJDBC(db);
        recordDAO = new RecordsJDBC(db, custDAO);
    }
    
    public SQLiteDatabase getDB() {
    	return this.db;
    }
    
    public CustomerInfoDAO getCustDAO() {
    	return custDAO;
    }
    
    public RecordsDAO getRecordDAO() {
    	return recordDAO;
    }
    
    private void listRecords() {
    	records = recordDAO.listRecordsByTime();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	listRecords();
    	this.onCreate(null);
    }
}
