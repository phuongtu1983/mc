/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.core.util;

/**
 *
 * @author Administrator
 */
public class GenericValidator {
    
    public static boolean isBlankOrNull(String str) {
        if (str == null || str.trim().length() == 0 || str.equals("null")) {
            return true;
        }
        return false;
    }
    
    public static boolean isZeroOrNull(Integer i) {
        if (i == null || i == 0) {
            return true;
        }
        return false;
    }
    
    public static boolean isNull(Object o) {
        if (o == null) {
            return true;
        }
        return false;
    }
}
