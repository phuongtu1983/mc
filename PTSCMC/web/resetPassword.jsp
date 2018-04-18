<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <script language="JavaScript" src="js/leidc.js" type="text/javascript"></script>
        <style type="text/css">
            <!--
                BODY {
                font-family: Tahoma;
                font-size: 8px;
                margin: 0 0 0 0;
            }
            -->
        </style>
        <script language="JavaScript" type="text/javascript">
            function isBlank(field, strBodyHeader) {
                strTrimmed = trim(field.value);
                if (strTrimmed.length > 0) return false;
                    alert("\"" + strBodyHeader + "\" is a required field.");
                    field.focus();
                    return true;
                }
            function submitForm() {
                return validateForm();
            }
            function validateForm() {
                var username = document.forms[0].username;
                var email = document.forms[0].email;
                if (isBlank(username, "User Name")) return false;
                if (isBlank(email, "Email")) return false;                
                return true;
            }
        </script>
    </head>
    <body>
        <html:form action="resetPassword.do">    
            <div align="left">
                <table border="0" width="100%" cellspacing="0" cellpadding="0" height="761"  background="images/login_back.jpg">
                    <tr>
                        <td align="left" valign="top">
                            <table border="0" width="100%" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td height="150" width="25%">&nbsp;</td>
                                    <td width="15%">&nbsp;</td>
                                    <td width="35%">&nbsp;</td>
                                    <td width="25%">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="center" height="40" valign="top">                                        
                                        <div>
                                            <font size="4" color="#FFFFFF" face="Tahoma"><b><bean:message key="message.resetpassword.title"/></b></font>
                                        </div>  
                                    </td>                
                                </tr>
                                <tr align="center">
                                    <td>&nbsp;</td>                                                                        
                                    <td height="25" colspan="2" align="left">
                                        <font size="2" color="#FFFFFF" face="Tahoma" >
                                            <b>
                                                <bean:message key="message.resetpassword.guide1"/>
                                            </b>
                                        </font>
                                    </td>                
                                    <td>&nbsp;</td>
                                </tr>
                                <tr align="center">                                    
                                    <td>&nbsp;</td>
                                    <td height="25" colspan="2" align="left">
                                        <font size="2" color="#FFFFFF" face="Tahoma" >
                                            <b>
                                                <bean:message key="message.resetpassword.guide2"/>
                                            </b>
                                        </font>
                                    </td>                
                                    <td>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td align="right" height="30">
                                        <font size="2" color="#FFFFFF" face="Tahoma"><b><bean:message key="message.resetpassword.username"/></b></font>
                                    </td>
                                    <td align="left">
                                        &nbsp;&nbsp;&nbsp;<html:text style="font-size: 8pt; font-family: Tahoma; border: 1px solid #D0CCC4;" size="38" property="username" value=""/>
                                    </td>                                    
                                    <td>&nbsp;</td>
                                </tr>            
                                <tr>
                                    <td>&nbsp;</td>
                                    <td align="right" height="30">
                                        <font size="2" color="#FFFFFF" face="Tahoma"><b><bean:message key="message.resetpassword.email"/></b></font>
                                    </td>
                                    <td align="left">
                                        &nbsp;&nbsp;&nbsp;<html:text style="font-size: 8pt; font-family: Tahoma; border: 1px solid #D0CCC4;" size="38" property="email" value=""/>
                                    </td>                                    
                                    <td>&nbsp;</td>
                                </tr>
                                <tr> 
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td align="center" height="30">
                                        <html:submit style="font-size: 8pt; font-family: Tahoma"  onclick="return submitForm();">
                                            <bean:message key='message.resetpassword.email.title'/>
                                        </html:submit>
                                        <td>&nbsp;</td>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td align="left">
                                        <font size="3" color="#FFFFFF" face="Tahoma">
                                            <b><html:errors /></b>
                                        </font>
                                    </td>
                                    <td>&nbsp;</td>
                                </tr>                                
                            </table> 
                        </td>
                    </tr>
                </table>
            </div>
        </html:form>
    </body>
</html>
