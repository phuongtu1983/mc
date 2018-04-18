<%-- 
    Document   : osdmaterials
    Created on : Sep 27, 2009, 1:03:20 AM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="osDMaterialTbl" class="its" style="width:100%">
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.del"/></th>
            <th><bean:message key="message.osddetail.matId"/></th>
            <th><bean:message key="message.osddetail.unit"/></th>
            <th><bean:message key="message.osddetail.quantity"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="material" name="<%=Constants.OSD_MATERIAL_LIST%>">
            <tr>
                <td width="20px">
                    <input type="checkbox" name="osDMaterialChk" value="<bean:write name="material" property="detId"/>">
                    <input type="hidden" name="material" value="<bean:write name="material" property="matId"/>"/>
                </td>
                <td><bean:write name="material" property="matName"/></td>
                <td><bean:write name="material" property="unit"/></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="quantity"  onblur="return tryNumberFormat(this)" /></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>
