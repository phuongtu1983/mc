<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" width="100%" class="its" id="equipmentDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.del"/></th>
            <th><bean:message key="message.acceptancetest.usedCode"/></th>
            <th><bean:message key="message.acceptancetest.equId"/></th>
            <th><bean:message key="message.acceptancetest.unit"/></th>
            <th><bean:message key="message.acceptancetest.quantity"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.ACCEPTANCETEST_DETAIL_LIST%>">
            <tr>
                <td width="30px">
                    <div align="center"><input type="checkbox" name="detId" value='<bean:write name="detail" property="detId"/>'/></div>
                    <input type="hidden" name="reqDetId" value='<bean:write name="detail" property="detId"/>'/>
                    <html:hidden name="detail" property="equId"/>
                </td>
                <td><span><bean:write name="detail" property="usedCode"/></span></td>
                <td>
                    <span><bean:write name="detail" property="matName"/></span>
                    <html:hidden name="detail" property="matName"/>
                </td>
                <td>
                    <span><bean:write name="detail" property="unitName"/></span>
                    <html:hidden name="detail" property="unitName"/>
                </td>                
                <td width="80px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="detail" property="quantity"  onblur="return tryNumberFormat(this)" /></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>