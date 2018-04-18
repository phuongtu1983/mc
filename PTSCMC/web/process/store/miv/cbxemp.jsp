<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="org.apache.struts.util.LabelValueBean"%>
<select name="receiver">
    <logic:present name="<%=Constants.COMBO_SELECTED%>">
        <logic:iterate id="emp_iter" name="<%=Constants.EMP_LIST%>">
            <logic:equal name="<%=Constants.COMBO_SELECTED%>" value="${emp_iter.value}">
                <option selected value="${emp_iter.value}">${emp_iter.label}</option>
            </logic:equal>
            <logic:notEqual name="<%=Constants.COMBO_SELECTED%>" value="${emp_iter.value}">
                <option  value="${emp_iter.value}">${emp_iter.label}</option>
            </logic:notEqual>
        </logic:iterate>
    </logic:present>
    <logic:notPresent name="<%=Constants.COMBO_SELECTED%>">
        <logic:iterate id="emp_iter" name="<%=Constants.EMP_LIST%>">
            <option  value="${emp_iter.value}">${emp_iter.label}</option>
        </logic:iterate>
    </logic:notPresent>
</select>
