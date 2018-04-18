<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.core.util.DateUtil"%>
<%@ page import="com.venus.mc.util.MCUtil"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><bean:message key="welcome.title"/></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="Expires"/>
        <meta content="no-cache" http-equiv="Pragma"/>
        <meta content="no-cache" http-equiv="Cache-Control"/>
        <meta content="text/html; charset=UTF-8" http-equiv="content-type"/>
        <link rel="stylesheet" href="css/tm.css" type="text/css" />    
        <link rel="stylesheet" href="css/wf.css" type="text/css" />    
        <link media="print" type="text/css" href="css/print.css" rel="stylesheet">
            <style type="text/css">
                <!--
                .userstyle {color: #CC0000}
                -->
            </style>    
            <script language="JavaScript" src="js/md5.js" type="text/javascript"></script>
            <script language="JavaScript" src="js/mc.js" type="text/javascript"></script>
            <script type="text/javascript" src="js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad:true"></script>
            <script type="text/javascript" src="js/mootools-1.11.js"></script>
            <script type='text/javascript' src='js/Ajax.js'></script>
            <script language="JavaScript" type="text/javascript">
                sessionId='<%=session.getAttribute(Constants.SESSION_ID)%>';
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

                function changePassword() {
                    if (validateForm() == true) {
                        var password = document.forms[0].password; // document.getElementById('main:password');
                        var md5 = document.forms[0].md5pw; //document.getElementById('main:md5pw');            
                        pwtomd5(password, md5);
                        callAjaxCheckError("changePassword.do",null,document.forms[0],function(data){
                            window.location='index.do';
                        });
                        return false;
                    }
                    return false;
                }
                function validateForm() {            
                    var password = document.forms[0].password; 
                    var newpassword = document.forms[0].newpassword;
                    var retypepassword = document.forms[0].retypepassword;
                    if (isBlank(password, "Current Password")) return false;
                    if (isBlank(newpassword, "New Password")) return false;
                    if (isBlank(retypepassword, "Retype Password")) return false;
                    //Check Password's length
                    if (newpassword.value.length < 3) {
                        alert("Password must be at least 3 characters.");
                        newpassword.focus();
                        return false;
                    }
                    if (newpassword.value!=retypepassword.value) {
                        alert("Passwords don't match.");
                        return false;
                    }
                    return true;
                }
            </script>

    </head>
    <body leftmargin="0" topmargin="0" class="tundra">
        <script type='text/javascript' src='js/wz_tooltip.js'></script>
        <center>
            <table width="990" height="14" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                    <td width="14" background="img/lf.gif">&nbsp;</td>
                    <td width="962" bgcolor="#9AC8FA">
                        <div align="right"><font color="#0C478A">
                                <a href="#" onclick="return getAjaxData('aboutus.do',null);" class="my1">About us</a>
                                &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                <a href="#" class="my1">Help</a>
                                &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                <a href="passwordForm.do" class="my1">Change Password</a>
                                &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                <a href="logout.do" class="my1">Logout</a>
                            </font></div>
                    </td>
                    <td width="14" background="img/rf.gif">&nbsp;</td>
                </tr>
            </table>
            <table width="990" height="111" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                    <td width="14" background="img/lf.gif">&nbsp;</td>
                    <td width="962" background="img/mcbanner1.gif">
                        <p style="margin-top:55px; margin-bottom:0;margin-left:230px;"><font face="Tahoma" size="2"><strong><i>Welcome <font style="color: #FF0000"><%=MCUtil.getMemberFullName(session)%></font></i></strong></font>
                            <br/><font face="Tahoma" style="font-size: 11px"><b>Administrator</b></font>
                            <br/><font face="Tahoma" style="font-size: 11px"><b>Date: </b><%=DateUtil.today("dd/MM/yyyy")%></font></p>
                    </td>
                    <td width="14" background="img/rf.gif">&nbsp;</td>
                </tr>
            </table>
            <table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                    <td width="742">
                        <html:form action="changePassword.do">
                            <table border="0" width="50%" align="center" cellspacing="0" cellpadding="0" style="border: 3px solid #295BB0">
                                <tr><td><div align="center">
                                            <table width="91%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">                                            
                                                <tr><td colspan="2" height="22"></td></tr>
                                                <tr><td colspan="2" height="22" bgcolor="#1E3C7B" style="border-bottom-style: none; border-bottom-width: medium"><p align="left" style="margin-top: 0; margin-bottom: 0"><b><font size="2" color="#FFFFFF" face="Tahoma">
                                                                    <bean:message key="message.passwordform.title"/>
                                                                </font></b></p></td></tr>
                                                <tr><td valign="bottom" colspan="2" height="7" bgcolor="#4E79D3" style="border-top-style: none; border-top-width: medium"></td></tr>
                                                <tr><td colspan="2">
                                                        <logic:messagesPresent>
                                                            <p style="margin-top: 0; margin-bottom: 0;">
                                                                <font face="Verdana" color="#FF0000"> 
                                                                    <ul><h5><bean:message key="errors.message"/></h5>
                                                                        <html:errors />
                                                                    </ul></font></p>
                                                                </logic:messagesPresent>
                                                                <logic:equal name="success" value="ok">
                                                                    <bean:message key="message.passwordform.success"/>
                                                                </logic:equal>
                                                    </td></tr>
                                                <tr>
                                                    <td height="22"><p style="margin-top:0; margin-bottom:0" align="right"><font face="Tahoma" style="font-size: 8pt; font-style:italic; text-decoration:underline" color="#000080"><bean:message key="message.passwordform.curpassword"/> </font>&nbsp;</p></td>
                                                    <td height="22"><p style="margin-top:0; margin-bottom:0" align="left"><html:password property="password" size="20" /></p></td>
                                                </tr>
                                                <tr>
                                                    <td height="22"><p style="margin-top:0; margin-bottom:0" align="right"><font face="Tahoma" style="font-size: 8pt; font-style:italic; text-decoration:underline" color="#000080"><bean:message key="message.passwordform.newpassword"/></font>&nbsp;</p></td>
                                                    <td height="22"><p style="margin-top:0; margin-bottom:0" align="left"><html:password property="newpassword" size="20" /></p></td>
                                                </tr>
                                                <tr>
                                                    <td height="22"><p style="margin-top:0; margin-bottom:0" align="right"><font face="Tahoma" style="font-size: 8pt; font-style:italic; text-decoration:underline" color="#000080"><bean:message key="message.passwordform.retypepassword"/></font>&nbsp;</p></td>
                                                    <td height="22"><p style="margin-top:0; margin-bottom:0" align="left"><html:password property="retypepassword" size="20" /></p></td>
                                                </tr>                            
                                            </table>
                                            <p style="margin-top: 0; margin-bottom: 0" align="right">
                                                <html:image onclick="return changePassword();" src="images/but_save.gif" style="cursor: hand;"/>
                                            </p>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </html:form>
                    </td>
                </tr>
            </table>             
        </center>   
    </body>
</html>                