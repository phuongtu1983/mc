<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<select style="width: 170px;" name="venId" onchange="return contractVendorChanged(this);">
     <logic:iterate id="vendor" name="<%=Constants.VENDOR_LIST%>">
            <option value="${vendor.venId}">${vendor.name}</option>
        </logic:iterate>
</select>