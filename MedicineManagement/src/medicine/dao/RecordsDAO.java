package medicine.dao;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import medicine.pojo.RecordDatas;

public interface RecordsDAO {
		
	public boolean createRecords(RecordDatas record);
	
	public List<RecordDatas> listRecordsById(int custId);
	
	public List<RecordDatas> listRecordsByTime();
	
	public boolean deleteRecords(int id);
		
}
