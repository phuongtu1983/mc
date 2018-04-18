<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchOriginForm' name='searchOriginForm'>
    <table border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
        <tr>
            <td height="22"><p style="margin-top:0; margin-bottom:0" align="right"><bean:message key="message.origin.nameEn"/>&nbsp;</p></td>
            <td height="22" colspan="5"><p style="margin-top:0; margin-bottom:0" align="left"><html:text style="font-size: 8pt; font-family: Tahoma; border: 1px solid #D0CCC4;" property="nameEn" size="80" name="<%=Constants.ORIGIN%>"/></p></td>
        </tr>
        <tr>
            <td height="22"><p style="margin-top:0; margin-bottom:0" align="right"><bean:message key="message.origin.nameVn"/>&nbsp;</p></td>
            <td height="22" colspan="5"><p style="margin-top:0; margin-bottom:0" align="left"><html:text style="font-size: 8pt; font-family: Tahoma; border: 1px solid #D0CCC4;" property="nameVn" size="80" name="<%=Constants.ORIGIN%>" /></p></td>
        </tr>
        <tr>
            <td height="22"><p style="margin-top:0; margin-bottom:0" align="right"><bean:message key="message.origin.note"/>&nbsp;</p></td>
            <td height="22" colspan="5"><p style="margin-top:0; margin-bottom:0" align="left"><html:text style="font-size: 8pt; font-family: Tahoma; border: 1px solid #D0CCC4;" property="note" size="80" name="<%=Constants.ORIGIN%>" /></p></td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvOrigin();"/>
</form>