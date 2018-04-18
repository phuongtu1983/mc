/*
 * Encoder.java
 *
 * Created on March 24, 2007, 10:19 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.venus.core.sercurity;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import com.venus.core.misc.Base64;
import com.venus.core.util.MailUtil;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Administrator
 */
public class Encoder {
    
    /** Creates a new instance of Encoder */
    public Encoder() {
    }
    public static synchronized String getMD5_Base64(String input) {
        if(!isInited) {
            isInited = true;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        if(digest == null)
            return input;
        try {
            digest.update(input.getBytes("UTF-8"));
        } catch(UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        byte rawData[] = digest.digest();
        byte encoded[] = Base64.encode(rawData);
        String retValue = new String(encoded);
        return retValue;
    }
    
    public static String encodeURL(String input) {
        if(encodeMethod1_4 != null) {
            Object methodArgsName[] = new Object[2];
            methodArgsName[0] = input;
            methodArgsName[1] = "UTF-8";
            try {
                return (String)encodeMethod1_4.invoke(null, methodArgsName);
            } catch(Exception ex) {
                throw new RuntimeException("System error invoking URLEncoder.encode() by reflection.");
            }
        } else {
            return URLEncoder.encode(input);
        }
    }
    
    public static String decodeURL(String input) {
        if(decodeMethod1_4 != null) {
            Object methodArgsName[] = new Object[2];
            methodArgsName[0] = input;
            methodArgsName[1] = "UTF-8";
            try {
                return (String)decodeMethod1_4.invoke(null, methodArgsName);
            } catch(Exception ex) {
                throw new RuntimeException("System error invoking URLDecoder.decode() by reflection.");
            }
        } else {
            return URLDecoder.decode(input);
        }
    }
    
    public static String filterUrl(String url) {
        String lowerUrl = url.toLowerCase();
        if(lowerUrl.indexOf("javascript:") >= 0 || lowerUrl.indexOf("file:") >= 0)
            return "";
        String protocol = "http://";
        String name = null;
        if(url.startsWith("http://")) {
            protocol = "http://";
            name = url.substring(protocol.length());
        } else
            if(url.startsWith("https://")) {
            protocol = "https://";
            name = url.substring(protocol.length());
            } else
                if(url.startsWith("ftp://")) {
            protocol = "ftp://";
            name = url.substring(protocol.length());
                } else
                    if(url.startsWith("mailto:")) {
            protocol = "mailto:";
            name = url.substring(protocol.length());
                    } else {
            name = url;
                    }
        String ret;
        if(protocol.equals("mailto:"))
            try {
                MailUtil.checkGoodEmail(name);
                ret = protocol + name;
            } catch(Exception ex) {
                ret = "";
            } else
                ret = protocol + encodePath(name);
        return ret;
    }
    
    public static String encodePath(String path) {
        path = removeInvalidUserInURL(path);
        return path;
    }
    
    private static String removeInvalidUserInURL(String path) {
        int atIndex = path.lastIndexOf('@');
        if(atIndex != -1) {
            int pecentIndex = path.indexOf('%');
            if(pecentIndex != -1 && pecentIndex < atIndex)
                return path.substring(atIndex + 1);
        }
        return path;
    }
    
    private static MessageDigest digest = null;
    private static boolean isInited = false;
    private static Method encodeMethod1_4 = null;
    private static Method decodeMethod1_4 = null;
    
    
}
