package com.venus.core.util;

import org.apache.log4j.*;
import java.io.*;
import java.net.URL;

public class LogUtil {

    public static String APP_NAME = "PTSCMC";
    //public static String fileName = "log4j.properties";
    public static URL logUrl = null;

    /**Define default layout*/
    /*
    static {
    //init log4j
    SystemProperties sysProps = SystemProperties.getInstance();
    String log4jPath = sysProps.getProperty("LOG4J_PATH");
    File file = new File(log4jPath + "/log4j.properties");
    //File file = new File("log4j.properties");
    System.out.println("file =" + log4jPath + "/log4j.properties");
    if (file.exists()) {
    System.out.println("log4j  ========= Log to File");
    PropertyConfigurator.configure(file.getPath());
    } else {
    System.out.println("log4j  ========= Load Default");
    Logger root = Logger.getRootLogger();
    Layout layout = new PatternLayout("[%c{1} ]  %m%n");
    root.addAppender(new ConsoleAppender(layout,
    ConsoleAppender.SYSTEM_OUT));
    }
    }
     */
    public static void initialize(URL url) {
        //System.out.println("Log4j.initialze=================");
        logUrl = url;
        PropertyConfigurator.configure(url);
    }

    public static void loadProperties(InputStream is) {
        try {
            java.util.Properties p = new java.util.Properties();
            p.load(is);
            PropertyConfigurator.configure(p);
            is.close();
        } catch (Exception e) {
            System.out.println("Not found " + is.toString());
        }
    }

    public static void info(String sGroup, Object oMsg) {
        Logger.getLogger(sGroup).info(oMsg);
    }

    public static void info(Object oMsg) {
        Logger.getLogger(APP_NAME).info(oMsg);
    }

    public static void debug(String sGroup, Object oMsg) {
        Logger.getLogger(sGroup).debug(oMsg);
    }

    public static void debug(Object oMsg) {
        Logger.getLogger(APP_NAME).debug(oMsg);
    }

    public static void warn(String sGroup, Object oMsg) {
        Logger.getLogger(sGroup).warn(oMsg);
    }

    public static void warn(Object oMsg) {
        Logger.getLogger(APP_NAME).warn(oMsg);
    }

    public static void error(String sGroup, Object oMsg) {
        Logger.getLogger(sGroup).error(oMsg);
    }

    public static void error(Object oMsg) {
        if (oMsg instanceof Exception) {
            Logger.getLogger(APP_NAME).error(getStackTrace((Exception) oMsg));
        } else {
            Logger.getLogger(APP_NAME).error(oMsg);
        }
    }

    public static void error(Class clazz, Object oMsg) {
        if (oMsg instanceof Exception) {
            Logger.getLogger(APP_NAME).error(clazz.getName() + ": " + getStackTrace((Exception) oMsg));
        } else {
            Logger.getLogger(APP_NAME).error(clazz.getName() + ": " + oMsg);
        }
    }

//--------------------------------------------------------------------------
    /**
     * log
     * @param sGroup name message
     * @param oMsg message
     */
    public static void fatal(String sGroup, Object oMsg) {
        Logger.getLogger(sGroup).fatal(oMsg);
    }

    /**
     * log
     * @param oMsg message
     */
    public static void fatal(Object oMsg) {
        Logger.getLogger(APP_NAME).fatal(oMsg);
    }

    /**
     * get error exception
     * @param e Exception
     * @return error message
     */
    public static String getStackTrace(Exception e) {
        ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(byteArr);
        e.printStackTrace(print);
        return byteArr.toString();
    }
}
