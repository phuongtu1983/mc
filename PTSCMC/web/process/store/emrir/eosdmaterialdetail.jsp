<%-- 
    Document   : eosdmaterialdetail
    Created on : Oct 18, 2009, 6:48:55 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="eosDMaterialDetailTbl">
    <tr>
        <td width="20px">
            <input type="checkbox" name="eosDMaterialChk" value="0">
            <input type="hidden" name="material" value="<bean:write name="<%=Constants.EOSD_MATERIAL%>" property="ematId"/>"/>
        </td>
        <td><bean:write name="<%=Constants.EOSD_MATERIAL%>" property="matName"/></td>
        <td><bean:write name="<%=Constants.EOSD_MATERIAL%>" property="unit"/></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="<%=Constants.EOSD_MATERIAL%>" property="quantity"  onblur="return tryNumberFormat(this)" /></td>
    </tr>
</table>
