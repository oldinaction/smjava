package cn.aezo.logging.log4j_1_2_api;

import org.apache.log4j.Logger;

public class Log4j12ApiApp {
    /**
     * 配置文件还是必须为log4j2.xml，而不能是log4j.properties/log4j.xml。打印结果如下：
     *
     * log4j debug message
     * 2021-12-10 22:13:07.831 DEBUG [main] cn.aezo.logging.log4j_1_2_api.Log4j12ApiApp.main(16) | log4j debug message
     * log4j info message
     * 2021-12-10 22:13:07.833 DEBUG [main] cn.aezo.logging.log4j_1_2_api.Log4j12ApiApp.main(19) | log4j info message
     */
    private static final Logger logger = Logger.getLogger(Log4j12ApiApp.class);
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
