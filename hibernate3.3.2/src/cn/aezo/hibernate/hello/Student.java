package cn.aezo.hibernate.hello;

//hibernate使用xml配置数据库映射的helloworld案例。
public class Student {
	private int id;
	private String name;
	//private StudentPK pk;//联合主键的例子用到
	private int age;
	
	
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
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

/*	public StudentPK getPk() {
		return pk;
	}

	public void setPk(StudentPK pk) {
		this.pk = pk;
	}*/

}
