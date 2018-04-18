<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchUnitForm' name='searchUnitForm'>
    <table border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
        <tr>
            <td height="22"><p style="margin-top:0; margin-bottom:0" align="right"><bean:message key="message.unit.unitEn"/>&nbsp;</p></td>
            <td height="22" colspan="5"><p style="margin-top:0; margin-bottom:0" align="left"><html:text style="font-size: 8pt; font-family: Tahoma; border: 1px solid #D0CCC4;" property="unitEn" size="80" name="<%=Constants.UNIT%>"/></p></td>
        </tr>
        <tr>
            <td height="22"><p style="margin-top:0; margin-bottom:0" align="right"><bean:message key="message.unit.unitVn"/>&nbsp;</p></td>
            <td height="22" colspan="5"><p style="margin-top:0; margin-bottom:0" align="left"><html:text style="font-size: 8pt; font-family: Tahoma; border: 1px solid #D0CCC4;" property="unitVn" size="80" name="<%=Constants.UNIT%>" /></p></td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvUnit();"/>
</form>