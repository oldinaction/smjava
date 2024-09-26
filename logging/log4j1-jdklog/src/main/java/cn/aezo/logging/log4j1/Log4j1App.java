package cn.aezo.logging.log4j1;

import org.apache.log4j.Logger;

public class Log4j1App {
    /**
     * 无 log4j.properties 文件时打印如下：
     * log4j:WARN No appenders could be found for logger (cn.aezo.logging.log4j1.Log4j1App).
     * log4j:WARN Please initialize the log4j system properly.
     * log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
     *
     * 有 log4j.properties 文件之后打印如下：
     * 2021-12-10 21:34:01 log4j debug message
     * 2021-12-10 21:34:01 log4j info message
     */
    private static final Logger logger = Logger.getLogger(Log4j1App.class);
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
