package medicine.dao;

public interface TableDetails {
	/**
	 * 数据库名称
	 */
	String DATABASENAME = "medicineDB";
	/**
	 * 用户基本信息表表名
	 */
	String TABLENAME_CUSTIFO = "customer_infor";
	/**
	 * 数据表表名
	 */
	String TABLENAME_RECORDS = "records";
	/**
	 * 用户表--id
	 */
	String CUSTINFO_ID = "id";
	/**
	 * 用户表--name
	 */
	String CUSTINFO_NAME = "name";
	/**
	 * 用户表--tel
	 */
	String CUSTINFO_TEL = "tel";
	/**
	 * 用户表--remark
	 */
	String CUSTINFO_REMARK = "remark";
	/**
	 * 用户表--firstTime
	 */
	String CUSTINFO_FIRSTTIME = "firstTime";
	/**
	 * 用户表--frequency
	 */
	String CUSTINFO_FREQUENCY = "frequency";
	/**
	 * 用户表--total
	 */
	String CUSTINFO_TOTAL = "total";
	/**
	 * 用户表--最大id
	 */
	String CUSTINFO_MAXID = "MAX(id)";
	/**
	 * 数据表--cust_id
	 */
	String RECORDS_CUSTID = "cust_id";
	/**
	 * 数据表--cust_name
	 */
	String RECORDS_CUSTNAME = "cust_name";
	/**
	 * 数据表--last_time
	 */
	String RECORDS_LASTTIME = "last_time";
	/**
	 * 数据表--next_time
	 */
	String RECORDS_NEXTTIME = "next_time";
	/**
	 * 数据表--times
	 */
	String RECORDS_TIMES = "times";
	/**
	 * 数据表--surplus
	 */
	String RECORDS_SURPLUS = "surplus";
	/**
	 * 数据表--最大id
	 */
	String RECORDS_MAXID = "MAX(id)";
	
}
