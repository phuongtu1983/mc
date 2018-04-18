/*
 * CharsetFilter.java
 *
 * Created on February 9, 2007, 9:26 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.venus.core.util;

import java.io.IOException;
import javax.servlet.*;

/**
 *
 * @author Administrator
 */
public class CharsetFilter implements Filter {
    
    private String encoding;
    
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");        
        if (encoding == null) {
            encoding = "UTF-8";
        }        
    }
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain next) throws IOException, ServletException {        
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);        
        next.doFilter(request, response);
        }
    
    public void destroy() {
    }
}


