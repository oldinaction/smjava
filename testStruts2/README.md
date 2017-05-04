# struts2

## 介绍

1. struts2是 struts1和WebWork的结合
2. **struts2的本质就是将请求与视图分开** (struts2原理：视频09)
3. 官网：[http://struts.apache.org/](http://struts.apache.org/), 下文基于版本2.3.24(当前更新到2.5.10)
4. 所需jar包：struts2/lib下的jar包

    ```html
    commons-fileupload-1.3.1.jar
    commons-io-2.2.jar
    commons-lang3-3.2.jar
    freemarker-2.3.22.jar
    javassist-3.11.0.GA.jar
    ognl-3.0.6.jar
    struts2-core-2.3.24.1.jar
    xwork-core-2.3.24.1.jar
    ```
5. struts知识点
    - Action
    	- a)namespace（掌握）
    	- b)path（掌握）
    	- c)DMI（掌握）
    	- d)wildcard（掌握）
    	- e)接收参数（掌握前两种）
    	- f)访问request等（掌握Map IOC方式）
    	- g)简单数据验证（掌握addFieldError和<s:fieldError）
    - Result
    	- a)结果类型（掌握四种，重点两种）
    	- b)全局结果（掌握）
    	- c)动态结果（了解）
    - OGNL表达式（精通）
    	- a)# % $
    - Struts标签
    	- a)掌握常用的
    - 声明式异常处理（了解）
    - I18N（了解）
    - CRUD的过程（最重要是设计与规划）（精通）
    - Interceptor的原理（掌握）***视频中分析了Struts2源码***
    - 类型转换（掌握默认，了解自定义）

## Hello World

- web.xml中加入

    ```xml
    <!-- struts2的核心拦截器 -->
    <filter>
        <filter-name>struts2</filter-name>
        <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>struts2</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    ```
- 在src目录新建struts.xml(注意路径为src, 名称为struts.xml)
    
    ```xml
    <struts>
        <constant name="struts.devMode" value="true" />
        
        <package name="default" namespace="/" extends="struts-default">
            <action name="index"><!-- 省略class, 则自动调用xwork的一个ActionSupport类 -->
                <result>/index.jsp</result>
            </action>
        </package>
    
    </struts>
    ```
- 扩展
    - 给jar包导入源码和doc文档

        > - （1）给jar包导入源码(给struts2-core-2.3.24.1.jar导入源码)：右键相应jar包->properties->Java Source Attachment->External location->External Folder->D:/Java/struts-2.3.24.1/src/core/src/main/java
        > - （2）给此jar包导入doc文档：右键相应jar包->properties->Javadoc Location->javadoc URL->D:/Java/struts-2.3.24.1/docs/struts2-core/apidocs(定位到相应类，按F1，点击javadoc查看相应文档)
    
    - 添加XML文件自动提示功能
    
        > - （1）解压struts-2.3.24.1\lib\struts2-core-2.3.24.1.jar
        > - （2）记录struts.xml内DOCTYPE的一个值http://struts.apache.org/dtds/struts-2.3.dtd
        > - （3）Windows->搜索catalog->XML catalog->add->{Location:struts-2.3.24.1\lib\struts2-core-2.3.24.1\struts-2.3.dtd, Key type:URL, Key:http://struts.apache.org/dtds/struts-2.3.dtd}
    
    - 本地拷贝项目
        
        > - 需要修改Web Context-root(项目右键->properties->MyEclipse->Project Facets->Web)


## struts.xml

1. package
- `package`(是为了区分重名的action，类似于java中的包)
    - name:包名
    - namespace:命名空间，此namespace和action中name的值的组合不能重复
        - （1）namespace决定了action的访问路径，默认为""，可以接受所有路径的action
        - （2）namespace可以写为/，或者/xxx，或者/xxx/yyy，对应的action访问路径如/index.action，/xxx/index.action，或者/xxx/yyy/index.action(其中index为action的属性name值，后面的.action可省略)
        - （3）package和namespace最好用模块来进行命名
    - extends:继承了那个包，所有的包都继承了`struts-default`，来自struts2-core-2.3.24.1.jar->struts-default.xml
- `package`>`action`
    - name:此action名称(在浏览器的url中要访问此action就要输入此名称)
    - class:当访问此action时，就会调用相应的java类(如果没有就默认访问ActionSupport，ActionSupport是xwork的一个类，他实现了Action接口；实际中一般使用类继承ActionSupport)
        - **每一个访问请求都会重新new一个对象**
    - method:当访问此action时，要调用相应class类的相应的方法。默认调用execute()方法
    - 注：除了用method属性指定相应的action调用方法(缺点是产生太多action)；还可以在url地址中动态指定(动态方法调用DMI，使用!，视频13还没测试成功)；实际中多使用通配符
- `package`>`action`>`result`
    - name:此result名称
    - 原理：访问时，先获取实现了Action接口的类或者其子类的execute()方法的返回值，然后匹配name属性为此返回值的result，再显示此result标签中的页面
    - 注：属性为successs时可省略此name属性，因为Action接口execute()方法默认返回的是success字符串
    - 注：默认有`SUCCESS`/`ERROR`/`INPUT`/`LOGIN`等常量，有时候使用SUCCESS可以解决，但仍用INPUT是为了作区分
    
2. **struts2中的路径问题**（jsp文件中的href路径）
    - struts2是根据action的路径而不是jsp路径来确定，所有尽量不要使用相对路径，虽然可以用redirect方式解决，但redirect方式并非必要
    - 解决办法
        - 统一使用绝对路径(JSP页面的绝对路径中第一个"/"指的是服务器的根路径，而不是项目的根路径)
        - 在jsp中用request.getContextPath()方式来拿到webapp的路径,或者使用myeclipse常用的指定basePath
        - 参考源码：`WebRoot/others/testPath.jsp`
        
3. 通配符，可以将配置量降到最低
    
    ```xml
    <!-- 如果namespace="/" -->
    <action name="*_*" class="cn.aezo.wildcard.{1}" method="{2}">
    	<result>/wildcard/{1}_{2}.jsp</result>
    </action>
    ```
    - 注释：{1}表示第一个*，{2}表示第二个*；如果访问http://localhost:8080/Student_add，则{1}为Student，{2}为add；匹配是以最佳匹配优先
    - 建议使用到*_*，如果*太多程序可读性降低
    
    