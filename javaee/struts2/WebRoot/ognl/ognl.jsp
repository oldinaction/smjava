<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>ognl表达式</title>

  </head>
  <body>
	ognl表达式：<br />
	如&lt;s:property value="name" /&gt;中s:property是struts2标签，而value中的字符串才是ognl表达式<br />

	1、访问值栈(Value Stack)中action的普通属性value="name"：<s:property value="name" /><br />
	2、访问值栈中对象的普通属性(get set,Struts2根据url参数帮忙构建user对象)value="user.age"：<s:property value="user.age" /><br />
	2.1、访问值栈中对象的普通属性(自己在Action中构建对象)value="user.age"：<s:property value="user.age" /><br />
	3、访问值栈中对象的普通属性(get set)value="cat.friend.name"：<s:property value="cat.friend.name" /><br />
	<hr>
	4、访问值栈中对象的普通方法value="cat.miaomiao()：<s:property value="cat.miaomiao()" /><br />
	5、访问值栈中action的普通方法value="func()"：<s:property value="func()" /><br />
	<hr>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格式为：(1)@类名@属性/方法 (2)@@方法 只适用于调用Math类中的方法 (3)访问静态方法需要设置常量struts.ognl.allowStaticMethodAccess=true<br />
	6、访问静态属性value="@cn.aezo.ognl.StaticF@STR"：<s:property value="@cn.aezo.ognl.StaticF@STR" /><br />
	7、访问静态方法value="@cn.aezo.ognl.StaticF@func()"：<s:property value="@cn.aezo.ognl.StaticF@func()" /><br />
	8、访问Math类中的静态方法value="@@max(1, 2)"：<s:property value="@@max(1, 2)" /><br />
	<hr>
	9、(测试失败)访问普通类的构造方法(说明:已经在User类中重写了toString方法)value="new cn.aezo.ognl.model.User(20)"：<s:property value="new cn.aezo.ognl.model.User(20)" /><br />
	<hr>
	10、访问List(重写了toString方法，访问数组类似)value="users"：<s:property value="users" /><br />
	11、访问List中某个元素value="users[1]"：<s:property value="users[1]" /><br />
	12、访问List中元素的所有属性的集合value="users.{age}"：<s:property value="users.{age}" /><br />
	13、访问List中元素的所有属性的某个特定值value="users.{age}[1]" | value="users[1].age"：<s:property value="users.{age}[1]" /> | <s:property value="users[1].age" /><br />
	14、访问Set(重写了toString方法)value="dogs"：<s:property value="dogs" /><br />
	15、访问Set中某个元素(Set是无序的，因此这是获取不到的)：<s:property value="dogs[1]" /><br />
	16、访问Set中元素的所有属性的集合value="dogs.{name}"：<s:property value="dogs.{name}" /><br />
	17、访问Map所有元素value="userMaps"：<s:property value="userMaps" /><br />
	18、访问Map中某个元素value="userMaps.userm1" | value="userMaps['userm1']" | value="userMaps[\"userm1\"]"(转义了)：<s:property value="userMaps.userm1" /> | <s:property value="userMaps['userm1']" /> | <s:property value="userMaps[\"userm1\"]" /><br />
	19、访问Map中所有的Key,value="userMaps.keys"：<s:property value="userMaps.keys" /><br />
	20、访问Map中所有的Value,value="userMaps.values"：<s:property value="userMaps.values" /><br />
	21、访问容器大小value="users.size()" |　value="userMaps.size"：<s:property value="users.size()" /> | <s:property value="userMaps.size" /><br />
	<hr>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;相当于给users集合做循环，this指循环中当前的那个对象，?#this.age==1表示元素age=1的集合，^表示开头的元素，$表示结尾的元素<br />
	22、投影(过滤)value="users.{?#this.age==1}：<s:property value="users.{?#this.age==1}" /><br />
	23、投影value="users.{?#this.age==1}[1]：<s:property value="users.{?#this.age==1}[1]" /><br />
	24、投影value="users.{^#this.age>1}.{age}：<s:property value="users.{^#this.age>1}.{age}" /><br />
	25、投影value="users.{$#this.age>1}.{age}：<s:property value="users.{$#this.age>1}.{age}" /><br />
	26、投影(可以判断此条件下的集合是否为null)value="users.{$#this.age>1}.{age} == null"：<s:property value="users.{$#this.age>1}.{age} == null" /><br />
	<hr>
	27、使用[0]访问所有action和DefaultTextProvider组成集合的对象(只有服务器端跳转是才会有多个action)value="[0]"：<s:property value="[0]" /><br />
	27.1：value="[1]"：<s:property value="[1]" /><br />
	27.2：value="[0].name"：<s:property value="[0].name" />
	
	<s:debug></s:debug>

  </body>
</html>
