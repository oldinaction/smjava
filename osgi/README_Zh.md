## OSGi

- 参考：https://www.baeldung.com/osgi
- 常见的OSGi容器Apache Felix和Eclipse's Equinox。而Eclipse's Equinox很久没有更新，因此基于Felix容器测试
- 下载Felix容器，或者直接下载Apache Karaf容器(推荐，Karaf是基于Felix的OSGi管理平台，包含命令界面)
- 测试

```bash
# 启动Karaf可测试是否可正常运行
bin\karaf.bat start

# 设置 KARAF_HOME 环境变量，变把相应bin目录加入到Path下

# 在本项目目录启动
karaf

## 简单使用
# 打包项目
mvn clean install

# 安装组件. 显示如 Bundle ID: 59，说明Karaf从本地Maven存储库加载到组件
bundle:install mvn:cn.aezo/osgi-intro-sample-activator/1.0-SNAPSHOT

# 启动上述组件. 显示 Hello World.
bundle:start 59

# 停止上述组件. 显示 Goodbye World.
bundle:stop 59

# 卸载上述组件
bundle:uninstall 59

## 基于服务调用
# 安装服务端和客户端
install mvn:cn.aezo/osgi-intro-sample-service/1.0-SNAPSHOT  # Bundle ID: 60
install mvn:cn.aezo/osgi-intro-sample-client/1.0-SNAPSHOT   # Bundle ID: 61

# 启动客户端(什么都不会发生，因为客户端启动后正在等待服务)
start 61

# 启动服务端. 返回如下信息
# Registering service.
# Notification of service registered.
# Hello John
start 60
```



