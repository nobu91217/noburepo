package jp;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;

public class Test {
	static Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        logger.info("Hello!!");
        logger.error("aaaaa");
        logger.fatal("aaaa");
        logger.warn("aaaa");
    }
}
