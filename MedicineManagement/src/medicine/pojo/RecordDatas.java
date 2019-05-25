package medicine.pojo;

import medicine.tools.DateTools;

public class RecordDatas {
	
	private int custId;				// �û���id
	private String custName;		// �û�����
	private String lastTime;		// ��ǰ��ҩʱ��
	private String nextTime;		// �´���ҩʱ��
	private int times;				// ��ǰ����
	private int surplus;			// ʣ����ҩ����
	private CustomerInfo customer;	//�����û�����
	
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
