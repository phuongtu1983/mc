package com.venus.core.util;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Check for a valid User logged on in the current session.  If there is no
 * such user, forward control to the logon page.
 *
 * @author Craig R. McClanahan
 * @author Marius Barduta
 * @version $Rev: 54934 $ $Date: 2012/05/26 09:00:25 $
 */

public final class CheckLogonTag extends TagSupport {
    
    public static final long serialVersionUID = 1;
    /**
     * The session scope attribute under which the User object
     * for the currently logged in user is stored.
     */
    public static final String USER_KEY = "memberObj";
    
    public static final String LOGIN_PAGE = "loginForm.jsp";
    
    // --------------------------------------------------- Instance Variables
    /**
     * The key of the session-scope bean we look for.
     */
    private String name = USER_KEY;
    
    
    /**
     * The page to which we should forward for the user to log on.
     */
    private String page = LOGIN_PAGE;
    
    
    // ----------------------------------------------------------- Properties
    
    
    /**
     * Return the bean name.
     */
    public String getName() {
        
        return (this.name);
        
    }
    
    
    /**
     * Set the bean name.
     *
     * @param name The new bean name
     */
    public void setName(String name) {
        
        this.name = name;
        
    }
    
    
    /**
     * Return the forward page.
     */
    public String getPage() {
        
        return (this.page);
        
    }
    
    
    /**
     * Set the forward page.
     *
     * @param page The new forward page
     */
    public void setPage(String page) {
        
        this.page = page;
        
    }
    
    
    // ------------------------------------------------------- Public Methods
    
    
    /**
     * Defer our checking until the end of this tag is encountered.
     *
     * @exception JspException if a JSP exception has occurred
     */
    @Override
    public int doStartTag() throws JspException {
        
        return (SKIP_BODY);
        
    }
    
    
    /**
     * Perform our logged-in user check by looking for the existence of
     * a session scope bean under the specified name.  If this bean is not
     * present, control is forwarded to the specified logon page.
     *
     * @exception JspException if a JSP exception has occurred
     */
    @Override
    public int doEndTag() throws JspException {
        
        // Is there a valid user logged on?
        boolean valid = false;
        HttpSession session = pageContext.getSession();
        
        if ((session != null) && (session.getAttribute(name) != null))
            valid = true;
        
        // Forward control based on the results
        if (valid)
            return (EVAL_PAGE);
        else {
            try {
                pageContext.forward(page);
            } catch (Exception e) {
                throw new JspException(e.toString());
            }
            return (SKIP_PAGE);
        }
        
    }
    
    
    /**
     * Release any acquired resources.
     */
    @Override
    public void release() {
        
        super.release();
        this.name = USER_KEY;
        this.page = LOGIN_PAGE;
        
    }
    
    static public boolean isValidSession(HttpSession session, String attr) {
        boolean valid = false;
        
        if ((session != null) && (session.getAttribute(attr) != null))
            valid = true;
        return valid;
    }
}
