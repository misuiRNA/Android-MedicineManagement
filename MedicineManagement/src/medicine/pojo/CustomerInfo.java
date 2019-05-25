package medicine.pojo;

public class CustomerInfo {
	
	private int id;					// 用户id，数据库自动分配，递增
	private String name;			// 用户姓名
	private String tel;				// 用户电话
	private String remark;			// 备注
	private String firstTime;		// 首次用药时间
	private int frequency;			// 用药频率
	private int total;				// 用药总数
	
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
		String str = "id: " + id +"\n姓名： " + name + "\n电话： " + tel + "\n备注： " + remark + 
				"\n首次用药时间： " + firstTime + "\n用药频率： " + frequency + "\n总帖数： " + total;
		return str;
	}
}
