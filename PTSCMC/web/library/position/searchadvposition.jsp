<%-- 
    Document   : searchadvposition
    Created on : Aug 30, 2009, 8:47:17 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchPositionForm' name='searchPositionForm'>
    <table border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
        <tr>
            <td height="22"><bean:message key="message.position.l_name"/></td>
            <td height="22" colspan="5"><html:text property="name" size="80" name="<%=Constants.POSITION%>"/></td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvPosition();"/>
</form>
