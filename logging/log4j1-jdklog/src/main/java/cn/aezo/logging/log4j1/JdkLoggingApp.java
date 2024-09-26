package cn.aezo.logging.log4j1;

import java.util.logging.Logger;

public class JdkLoggingApp {

    private static final Logger logger = Logger.getLogger(JdkLoggingApp.class.getName());

    /**
     * 十二月 10, 2021 9:13:04 下午 cn.aezo.logging.log4j1.App main
     * 信息: jdk logging info...
     * 十二月 10, 2021 9:13:04 下午 cn.aezo.logging.log4j1.App main
     * 警告: jdk logging warning...
     * 十二月 10, 2021 9:13:04 下午 cn.aezo.logging.log4j1.App main
     * 严重: jdk logging severe...
     */
    public static void main(String[] args) {
        logger.info("jdk logging info...");
        logger.warning("jdk logging warning...");
        logger.severe("jdk logging severe...");
    }
}
