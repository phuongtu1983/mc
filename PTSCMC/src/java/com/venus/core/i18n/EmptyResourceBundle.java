/*
 * EmptyResourceBundle.java
 *
 * Created on March 21, 2007, 11:00 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.venus.core.i18n;

import java.util.ListResourceBundle;

/**
 *
 * @author Administrator
 */
public class EmptyResourceBundle extends ListResourceBundle {
    
    /** Creates a new instance of EmptyResourceBundle */
    public EmptyResourceBundle() {
    }
    
    protected Object[][] getContents() {
        return (new Object[][] {
            new Object[] {
                "emptykey", "Empty Value from EmptyResourceBundle"
            }
        });
    }
    
}



