package com.booking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Printable {
    public static final Logger LOGGER = LogManager.getLogger(Printable.class);

    private Printable() {
    }

    public static void printInfo(String info) {
        LOGGER.info(info);
    }

    public static void printError(String error, Exception e) {
        LOGGER.error(error, e);
    }

    public static void printWarning(String warning, Exception e) {
        LOGGER.warn(warning, e);
    }

    public static void printFatal(String fatal, Exception e) {
        LOGGER.fatal(fatal, e);
    }
}
