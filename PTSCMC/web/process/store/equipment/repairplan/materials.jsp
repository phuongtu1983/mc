<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="repairMaterialsTable">
    <logic:iterate id="detail" name="<%=Constants.MATERIAL_LIST%>">
        <tr>
            <td width="30px">
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="reqDetId" value="0"/>
                <html:hidden name="detail" property="matId"/>
            </td>
            <td width="100px"><span><bean:write name="detail" property="matName"/></span></td>
            <td><span><bean:write name="detail" property="matCode"/></span></td>
            <td>
                <span><bean:write name="detail" property="unit"/></span>
                <html:hidden name="detail" property="unit"/>               
            </td>
            <td width="7px"><input type="textbox" styleClass="lbl10" style="border-width:1px;text-align:right" size="7" name="quantity" id="detquantity${detail.rmId}" value="${detail.quantity}" /></td>
        </tr>
    </logic:iterate>
</table>