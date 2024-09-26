package cn.aezo.logging.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2App {
    /**
     * 无 log4j2.xml 文件时无任何信息打印
     * 有 log4j2.xml 文件之后打印如下：
     * 21:52:05.789 [main] DEBUG cn.aezo.logging.log4j2.Log4j2App - log4j debug message
     * 21:52:05.794 [main] DEBUG cn.aezo.logging.log4j2.Log4j2App - log4j info message
     */
    // 和log4j1是不同的，此时Logger是log4j-api中定义的接口，而log4j1中的Logger则是类
    private static final Logger logger = LogManager.getLogger(Log4j2App.class);
    public static void main(String[] args){
        if(logger.isTraceEnabled()){
            logger.debug("log4j trace message");
        }
        if(logger.isDebugEnabled()){
            logger.debug("log4j debug message");
        }
        if(logger.isInfoEnabled()){
            logger.debug("log4j info message");
        }
    }
}
