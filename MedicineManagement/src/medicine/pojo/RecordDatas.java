package medicine.pojo;

import medicine.tools.DateTools;

public class RecordDatas {
	
	private int custId;				// 用户的id
	private String custName;		// 用户姓名
	private String lastTime;		// 当前用药时间
	private String nextTime;		// 下次用药时间
	private int times;				// 当前次数
	private int surplus;			// 剩余用药次数
	private CustomerInfo customer;	//设置用户对象
	
	public RecordDatas(CustomerInfo customer, String lastTime, int times) {
		this.setCustomerInfo(customer);
		this.setCustId(customer.getId());
		this.setCustName(customer.getName());
		this.setLastTime(lastTime);
		this.setNextTime(calculateNextTime());
		this.setTime(times);
		this.setSurplus(calculateSurplus());
	}
	
//	public RecordDatas(int custId, String custName, String lastTime, String nextTime, int times, int surplus){
//		this.setCustId(custId);
//		this.setCustName(custName);
//		this.setLastTime(lastTime);
//		this.setNextTime(nextTime);
//		this.setSurplus(surplus);
//		this.setTime(times);
//	}
	
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public void setTime(int times) {
		this.times = times;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public void setNextTime(String nextTime) {
		this.nextTime = nextTime;
	}
	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}
	public void setCustomerInfo(CustomerInfo customer) {
		this.customer = customer;
	}
	
	public int getCustId() {
		return custId;
	}
	public String getCustName() {
		return custName;
	}
	public int getTime() {
		return times;
	}
	public String getLastTime() {
		return lastTime;
	}
	public String getNextTime() {
		return nextTime;
	}
	public int getSurplus() {
		return surplus;
	}
	public CustomerInfo getCustomerInfo() {
		return customer;
	}
	
	private String calculateNextTime() {
		if(customer == null) {
			return this.lastTime; 
		}
		String newTime = DateTools.getDateAfter(this.lastTime, this.getCustomerInfo().getFrequency());
		return newTime;
	}
	private int calculateSurplus() {
		if(customer == null) {
			return 10;
		}
		return customer.getTotal() - times;
	}
	public String toString() {
		String str = custId + "    " + custName + "    " + lastTime + "    " + surplus + "\n";
		return str;
	}
	
}
