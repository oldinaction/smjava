package cn.aezo.oa.model;

/**
 * @hibernate.class
 * 		table="T_Person"
 */
public class Person {
	/**
	 * @hibernate.id 
	 * 		generator-class="native" 
	 */
	private int id;
	/**
	 * @hibernate.property
	 */
	private String name;
	/**
	 * @hibernate.property
	 */
	private String sex;
	/**
	 * @hibernate.property
	 */
	private String address;
	/**
	 * @hibernate.property
	 */
	private int age;
	/**
	 * @hibernate.property
	 */
	private String duty;
	/**
	 * @hibernate.property
	 */
	private String phone;
	/**
	 * @hibernate.many-to-one
	 */
	private Organization org;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
	

}
