module cn.aezo.javase.jdk.jdk9_module2 {
    // idea的模块dependency中需要引入jdk9_module1使编译不报错
    // 如果此不通过requires导入jdk9_module1，则jdk9_module2中使用jdk9_module1的包会编译不通过
    requires cn.aezo.javase.jdk.jdk9_module1;
}