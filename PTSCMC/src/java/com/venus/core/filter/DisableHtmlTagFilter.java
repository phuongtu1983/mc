/*
 * DisableHtmlTagFilter.java
 *
 * Created on March 24, 2007, 10:34 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.venus.core.filter;

/**
 *
 * @author Administrator
 */
public class DisableHtmlTagFilter {
    
    /** Creates a new instance of DisableHtmlTagFilter */
    public DisableHtmlTagFilter() {
    }
    
    public static String filter(String input) {
        if(input == null)
            return null;
        char s[] = input.toCharArray();
        int length = s.length;
        StringBuffer ret = new StringBuffer(length + 100);
        for(int i = 0; i < length; i++)
            if(s[i] == '<')
                ret.append("&lt;");
            else
                if(s[i] == '>')
                    ret.append("&gt;");
                else
                    if(s[i] == '&') {
            if(i + 3 < length && s[i + 1] == '#' && s[i + 2] >= '0' && s[i + 1] <= '9' && s[i + 3] >= '0' && s[i + 2] <= '9')
                ret.append(s[i]);
            else
                if(i + 3 < length && s[i + 1] == 'l' && s[i + 2] == 't' && s[i + 3] == ';')
                    ret.append(s[i]);
                else
                    if(i + 3 < length && s[i + 1] == 'g' && s[i + 2] == 't' && s[i + 3] == ';')
                        ret.append(s[i]);
                    else
                        if(i + 4 < length && s[i + 1] == 'a' && s[i + 2] == 'm' && s[i + 3] == 'p' && s[i + 4] == ';')
                            ret.append(s[i]);
                        else
                            if(i + 5 < length && s[i + 1] == 'q' && s[i + 2] == 'u' && s[i + 3] == 'o' && s[i + 4] == 't' && s[i + 5] == ';')
                                ret.append(s[i]);
                            else
                                ret.append("&amp;");
                    } else
                        if(s[i] == '"')
                            ret.append("&quot;");
                        else
                            ret.append(s[i]);
        
        return ret.toString();
    }
    
    
}
