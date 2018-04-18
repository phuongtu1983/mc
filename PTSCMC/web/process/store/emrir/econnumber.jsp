<%-- 
    Document   : econnumber
    Created on : Oct 18, 2009, 3:33:43 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<table width="100%">
    <tr>    
        <td class="lbl10"><bean:message key="message.emrir.econNumber"/></td>
        <td colspan="2"><html:text styleClass="lbl10" size="30" property="econNumber" name="<%=Constants.EMRIR_DELIVERY_NOTICE_LIST%>" /></td>
    </tr>
</table>