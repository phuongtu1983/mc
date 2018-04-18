<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<select name="mcStore">
    <logic:present name="<%=Constants.STORE_LIST%>">
        <logic:iterate id="store_iter" name="<%=Constants.STORE_LIST%>">
            <option class="lbl9" value="${store_iter.stoId}">${store_iter.name}</option>
        </logic:iterate>
    </logic:present>
</select> 