<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table  style="width:100%" class="its" id="repairMaterialTable">
    <thead>
        <tr>
            <th><bean:message key="message.del"/></th>            
            <th><bean:message key="message.dn.matName"/></th>
            <th><bean:message key="message.dn.matCode"/></th>
            <th><bean:message key="message.dn.unit"/></th>
            <th><bean:message key="message.dn.quantity"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.REPAIR_MATERIAL_LIST%>">
            <logic:iterate id="detail" name="<%=Constants.REPAIR_MATERIAL_LIST%>">
                <tr>
                    <td width="30px">
                        <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                        <input type="hidden" name="reqDetId" value='<bean:write name="detail" property="detId"/>'/>
                        <html:hidden name="detail" property="matId"/>
                    </td>
                    <td width="300px"><span><bean:write name="detail" property="matName"/></span></td>
                    <td width="100px"><span><bean:write name="detail" property="matCode"/></span></td>
                    <td width="80px">
                        <span><bean:write name="detail" property="unit"/></span>
                        <html:hidden name="detail" property="unit"/>
                    </td>
                    <td width="7px"><input type="textbox" styleClass="lbl10" style="border-width:1px;text-align:right" size="7" name="quantity" id="detquantity${detail.rmId}" value="${detail.quantity}"  onblur="dnCheckQuantity(${detail.rmId});" /></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>