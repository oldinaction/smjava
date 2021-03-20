package cn.aezo.smjava.javaee.spring5.c01_ioc_flow.annotation;

import org.springframework.stereotype.Service;

/**
 * @author smalle
 * @date 2020-08-14 17:59
 */
@Service
public class MyService {
    public void doService() {
        System.out.println("do service...");
    }
}
