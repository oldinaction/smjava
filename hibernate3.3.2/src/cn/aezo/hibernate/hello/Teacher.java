package cn.aezo.hibernate.hello;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//hibernate使用annotation(批注)的helloworld案例.此时不需要配置类似Teacher.hbm.xml的配置文件。实际开发中多使用annotation而不用个xml

//(1)annotation字段映射相关批注：
//@Entity:实体类
//@Id:主键;@Basic:其他属性,可省略
//@Table(name="_teacher"),当实体类的类名和对应的表名不一致时批注,此时对应表的实际名为_teacher。
//@Column(name="_title"),当实际的字段名和类的属性名不一致时才需批注,此时表示对应的表中的字段实际名为_title。最好一直
//@Transient:透明的.表示此字段在更新时不保存到数据库中,即不参加持久化.这是annotation的写法,在xml中则不写此属性即可
//@Temporal(value=TemporalType.DATE),表示相应日期类型只记录日期,最终表的字段类型是DATE。不写的话默认是记录日期和时间,字段类型是TIMESTAMP。此处可以省略"value="。不常用
//@Enumerated(EnumType.STRING):声明枚举类型。EnumType.STRING表示在表中生成的字段类型是varchar;EnumType.ORDINAL表示表中生成的字段类型是int，并且拿枚举的下表存储
//@GeneratedValue:批注后主键会自动生成值，默认使用id生成策略是AUTO。@GeneratedValue(strategy=GenerationType.AUTO)，其中(strategy=GenerationType.AUTO)可以省略，会自动转换，相当于xml方式中的native
//(2)注意批注引入的包是JPA的标准(javax.persistence.*)
//(3)annotation字段映射位置：可以在field上或者get方法上(建议)，如果写在field则破坏了面向对象的机制，写在get方法是public的，所有一般写在get方法上

@Entity
@IdClass(TeacherPK.class)//测试联合主键用到的代码，测试之前删除原来的表，否则表信息注入错误
@Table(name="_teacher")
public class Teacher {
	private int id;//私有属性，不适合在此处做批注
	private String name;	
	private String title;
	private Date birthDate;
	private ZhiChen zhiChen;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Id//定义id、name的联合主键类
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="_title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Transient
	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Enumerated(value=EnumType.STRING)
	public ZhiChen getZhiChen() {
		return zhiChen;
	}

	public void setZhiChen(ZhiChen zhiChen) {
		this.zhiChen = zhiChen;
	}

}
