/*
 * CacheResourceBundle.java
 *
 * Created on March 21, 2007, 10:54 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.venus.core.i18n;

import java.text.MessageFormat;
import java.util.*;
/**
 *
 * @author Administrator
 */
public class CacheResourceBundle {
    
    private String bundleName;
    private Hashtable cacheResourceBundle;
    
    /** Creates a new instance of CacheResourceBundle */
    public CacheResourceBundle(String bundleName) {
        this.bundleName = null;
        cacheResourceBundle = new Hashtable();
        if(bundleName == null) {
            throw new IllegalArgumentException("BundleName cannot be null.");
        } else {
            this.bundleName = bundleName;
            return;
        }
    }
    
    public String getString(Locale locale, String key) {
        ResourceBundle resourceBundle = getResourceBundle(locale);
        try {
            return resourceBundle.getString(key);
        } catch(Exception ex) {
            return "[[" + key + "]]";
        }
    }
    
    public ResourceBundle getResourceBundle(Locale locale) {
        if(locale == null)
            locale = Locale.ENGLISH;
        ResourceBundle resourceBundle = (ResourceBundle)cacheResourceBundle.get(locale);
        if(resourceBundle == null) {
            try {
                resourceBundle = ResourceBundle.getBundle(bundleName, locale);
            } catch(MissingResourceException e) {
                resourceBundle = new EmptyResourceBundle();
            }
            cacheResourceBundle.put(locale, resourceBundle);
        }
        return resourceBundle;
    }
    
    public String getString(Locale locale, String key, Object args[]) {
        ResourceBundle resourceBundle = getResourceBundle(locale);
        try {
            String message = resourceBundle.getString(key);
            MessageFormat formatter = new MessageFormat(message);
            if(locale != null)
                formatter.setLocale(locale);
            message = formatter.format(((Object) (args)));
            return message;
        } catch(Exception ex) {
            return "[[[" + key + "]]]";
        }
    }
    
    
}
