<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="org.apache.struts.util.LabelValueBean"%>

<select name="rtId">
 <logic:iterate id="org_iter" name="<%=Constants.REQUIRETRANSFER_LIST%>">
    <option class="lbl9" value="${org_iter.value}">${org_iter.label}</option>
</logic:iterate>
</select>