<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="welcome.title"/></title>
        <link rel="StyleSheet" href="css/wf.css" type="text/css" />
        <link rel="shortcut icon" href="css/favicon.ico" />
        <link rel="StyleSheet" href="css/dtree.css" type="text/css" />
        <style type="text/css">
            <!--
                BODY {
                font-family: Tahoma;
                font-size: 8px;
                margin: 0 0 0 0;
            }
            -->
        </style>
        <script language="JavaScript" src="js/md5.js" type="text/javascript"></script>
        <script language="JavaScript" src="js/mc.js" type="text/javascript"></script>    
        <script language="JavaScript" type="text/javascript">
            function isBlank(field, strBodyHeader) {
                strTrimmed = trim(field.value);
                if (strTrimmed.length > 0) return false;
                    alert("\"" + strBodyHeader + "\" is a required field.");
                    field.focus();
                    return true;
                }

            function checkEnter(event) {
                var agt=navigator.userAgent.toLowerCase();

                // Maybe, Opera make an onClick event when user press enter key
                // on the text field of the form
                if (agt.indexOf('opera') >= 0) return;

                // enter key is pressed
                if (getKeyCode(event) == 13)
                    submitForm();
            }

            function submitForm() {
                if (validateForm() == true) {
                    return true;
                }
                return false;
            }
            function validateForm() {
                var username = document.forms[0].username; //document.getElementById('main:nickname');
                var email = document.forms[0].email; //document.getElementById('main:password');
                if (isBlank(username, "Username")) return false;
                if (isBlank(email, "Email")) return false;
                return true;
            }            
        </script>
    </head>
    <body>
        <html:form action="resetPassword.do">                       
            <div align="left">
                <table border="0" width="100%" cellspacing="0" cellpadding="0" background="images/login_back.jpg" height="761">
                    <tr>
                        <td height="759" align="left" valign="top">
                            <table border="0" width="755" cellspacing="0" cellpadding="0" height="35%" id="table1" background="images/forgetpass.jpg">
                                <tr>
                                    <td width="76" height="59">&nbsp;</td>
                                    <td height="59">&nbsp;</td>
                                    <td width="54" height="59" align="right" valign="top">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td width="76">&nbsp;</td>
                                    <td valign="top">
                                        <div align="left">
                                            <table border="0" width="574" id="table3" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="74" align="right" height="93">&nbsp;</td>
                                                    <td align="right" height="93" colspan="4">
                                                    <p align="right" style="margin-left: 0px; margin-top: -3px; margin-bottom: 0"></td>
                                                </tr>
                                                <tr>
                                                    <td width="74" align="right">&nbsp;</td>
                                                    <td width="169" align="right">&nbsp;</td>
                                                    <td width="228" align="left" colspan="2"><font size="1" style="font-size: 9pt; font-weight: 700" face="Microsoft Sans Serif"><html:text property="username" size="15" style="font-family: Tahoma; font-size: 10pt; border: 1px solid #3C4F77; background-color: #3C4F77; text-align:left; margin-left:0; margin-right:0; color:#FFFFFF; font-weight:bold" tabindex="1" onfocus="clearDefault(this)" value="User Name Here" /></font></td>
                                                    <td width="104" valign="bottom">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td width="74" align="right" height="38">&nbsp;</td>
                                                    <td width="169" align="right" height="38">&nbsp;</td>
                                                    <td width="32" align="right" height="38">&nbsp;</td>
                                                    <td width="196" height="38" align="left" valign="bottom"><font size="1" style="font-size: 9pt; font-weight: 700" face="Microsoft Sans Serif"><html:text value='' property="email" size="27" style="font-family: Tahoma; font-size: 10pt; border: 1px solid #CAD5E9; background-color: #CAD5E9; text-align:left; margin-left:0; margin-right:0; " tabindex="2" /></font></td>
                                                    <td width="104" valign="bottom">
                                                    <html:submit value="Enter" tabindex="3" style="font-size: 8pt; font-family: Tahoma"  onclick="return submitForm();" /></td>                                                                                
                                                </tr>
                                                <tr>                                                    
                                                    <td width="74" align="right" height="38">&nbsp;</td>
                                                    <td width="228" colspan="4" height="20" style="border-bottom-style: none; border-bottom-width: medium">&nbsp;</td>
                                                </tr>
                                                <tr>                                                    
                                                    <td width="74" align="right">&nbsp;</td>
                                                    <td colspan="4" align="center" style="border-bottom-style: none; border-bottom-width: medium">
                                                        <font face="Verdana" size="1" color="#FFFF99"><b><bean:message key="message.forgetpass.guide"/></b></font>
                                                    </td>
                                                </tr>                                                
                                                <tr>
                                                    <td width="74" height="5" align="right" style="font-size: 6">
                                                    </td>
                                                    <td width="500" height="5" align="right" colspan="4" style="font-size: 6">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="74" height="39" align="right">&nbsp;</td>
                                                    <td width="500" height="39" align="left" colspan="4">
                                                        <logic:messagesPresent>
                                                            <p style="margin-top: 0; margin-bottom: 0">                                                
                                                                <font face="Verdana" size="1" color="#FFFF99">
                                                                <b><bean:message key="errors.message"/></b></font>
                                                                <font face="Verdana" size="1" color="#99FF66"><span style="color: Lime"><html:errors /></span></font>
                                                            </p>
                                                        </logic:messagesPresent>                                                        
                                                    </td>
                                                </tr>
                                    </table></div></td>
                                    <td width="54">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        </html:form>
    </body>
</html>
