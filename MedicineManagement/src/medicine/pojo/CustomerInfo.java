package medicine.pojo;

public class CustomerInfo {
	
	private int id;					// �û�id�����ݿ��Զ����䣬����
	private String name;			// �û�����
	private String tel;				// �û��绰
	private String remark;			// ��ע
	private String firstTime;		// �״���ҩʱ��
	private int frequency;			// ��ҩƵ��
	private int total;				// ��ҩ����
	
	public CustomerInfo(int id, String name, String tel, String remark, String firstTime, int frequency, int total) {
		this.setFirstTime(firstTime);
		this.setFrequency(frequency);
		this.setId(id);
		this.setName(name);
		this.setRemark(remark);
		this.setTel(tel);
		this.setTotal(total);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getTel() {
		return tel;
	}
	public String getRemark() {
		return remark;
	}
	public String getFirstTime() {
		return firstTime;
	}
	public int getFrequency() {
		return frequency;
	}
	public int getTotal() {
		return total;
	}
	
	public String toString() {
		String str = "id: " + id +"\n������ " + name + "\n�绰�� " + tel + "\n��ע�� " + remark + 
				"\n�״���ҩʱ�䣺 " + firstTime + "\n��ҩƵ�ʣ� " + frequency + "\n�������� " + total;
		return str;
	}
}
