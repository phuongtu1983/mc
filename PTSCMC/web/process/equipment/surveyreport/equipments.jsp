<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="equipmentTable" style="width:100%">
    <logic:iterate id="material" name="<%=Constants.EQUIPMENT_LIST%>">
        <tr>
            <td width="30px">
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="reqDetId" value="0"/>
                <html:hidden name="material" property="equId"/>
            </td>
            <td><span><bean:write name="material" property="usedCode"/></span></td>
            <td>
                <span><bean:write name="material" property="equipmentName"/></span>
                <html:hidden name="material" property="equipmentName"/>
            </td>
            <td>
                <span><bean:write name="material" property="unit"/></span>
                <html:hidden name="material" property="unit"/>
            </td>
            <td width="80px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="material" readonly="true" property="quantity"  onblur="return tryNumberFormat(this)" /></td>
                        
        </tr>
    </logic:iterate>
</table>