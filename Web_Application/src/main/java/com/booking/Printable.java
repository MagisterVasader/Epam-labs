package com.booking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Printable {
    public static final Logger LOGGER = LogManager.getLogger(Printable.class);

    private Printable(){}

    public static void printInfo(String info){
        LOGGER.info(info);
    }

    public static void printError(String error){
        LOGGER.error(error);
    }

    public static void printWarning(String warning){
        LOGGER.warn(warning);
    }
}
