package medicine.dao;

public interface TableDetails {
	/**
	 * ���ݿ�����
	 */
	String DATABASENAME = "medicineDB";
	/**
	 * �û�������Ϣ�����
	 */
	String TABLENAME_CUSTIFO = "customer_infor";
	/**
	 * ���ݱ����
	 */
	String TABLENAME_RECORDS = "records";
	/**
	 * �û���--id
	 */
	String CUSTINFO_ID = "id";
	/**
	 * �û���--name
	 */
	String CUSTINFO_NAME = "name";
	/**
	 * �û���--tel
	 */
	String CUSTINFO_TEL = "tel";
	/**
	 * �û���--remark
	 */
	String CUSTINFO_REMARK = "remark";
	/**
	 * �û���--firstTime
	 */
	String CUSTINFO_FIRSTTIME = "firstTime";
	/**
	 * �û���--frequency
	 */
	String CUSTINFO_FREQUENCY = "frequency";
	/**
	 * �û���--total
	 */
	String CUSTINFO_TOTAL = "total";
	/**
	 * �û���--���id
	 */
	String CUSTINFO_MAXID = "MAX(id)";
	/**
	 * ���ݱ�--cust_id
	 */
	String RECORDS_CUSTID = "cust_id";
	/**
	 * ���ݱ�--cust_name
	 */
	String RECORDS_CUSTNAME = "cust_name";
	/**
	 * ���ݱ�--last_time
	 */
	String RECORDS_LASTTIME = "last_time";
	/**
	 * ���ݱ�--next_time
	 */
	String RECORDS_NEXTTIME = "next_time";
	/**
	 * ���ݱ�--times
	 */
	String RECORDS_TIMES = "times";
	/**
	 * ���ݱ�--surplus
	 */
	String RECORDS_SURPLUS = "surplus";
	/**
	 * ���ݱ�--���id
	 */
	String RECORDS_MAXID = "MAX(id)";
	
}
