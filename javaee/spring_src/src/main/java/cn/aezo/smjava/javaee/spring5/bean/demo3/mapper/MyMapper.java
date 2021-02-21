package cn.aezo.smjava.javaee.spring5.bean.demo3.mapper;

import cn.aezo.smjava.javaee.spring5.bean.demo3.mybatis.Select;

public interface MyMapper {
    @Select("select * from test")
    void select();
}
