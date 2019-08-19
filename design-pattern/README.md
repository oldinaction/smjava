## 设计模式

- Observer：基于事件实现小孩醒了爸爸喂奶
- iterator：基于数组和链表实现一个集合
- Chain_of_Responsibility 责任链模式
    - Tomcat中的Filter就是使用了责任链模式，创建一个Filter除了要在web.xml文件中做相应配置外，还需要实现javax.servlet.Filter接口
    - 参与者
        - `Handler`(抽象处理者)：定义出一个处理请求的接口。可选实现后继链(可返回下一个责任对象的)
        - `ConcreteHandler`(具体处理者)：处理他所负责的请求；可访问他的后继者；如果可处理该请求，就处理之，否则将该请求转发给它的后继者
    - 可插拔编程/插件开发常用


