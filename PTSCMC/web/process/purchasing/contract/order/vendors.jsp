<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.util.Constants"%>
<td><bean:message key="message.u_vendor"/></td>
<td colspan="3">
    <span id="orderSourceVendorIdSpan">
        <select style="width: 170px;" name="venId" id="orderSourceVendorIdCmd">
            <logic:iterate id="vendor" name="<%=Constants.VENDOR_LIST%>">
                <option value="${vendor.venId}">${vendor.name}</option>
            </logic:iterate>
        </select>
        <input type="button" onclick="return comboTextIdClick('contractForm','venName','venId','orderSourceVendorIdSpan','orderSourceVendorNameSpan');" value='<>'/>
    </span>
    <span  style="display:none" id="orderSourceVendorNameSpan">
        <input type="textbox" size="29" name="venName"/>
        <input type="button" onclick="return comboTextNameClick('contractForm','getVendorToCombobox.do',null,'venName','venId','orderSourceVendorIdCmd','orderSourceVendorIdSpan','orderSourceVendorNameSpan');" value='<>'/>
    </span>
</td>