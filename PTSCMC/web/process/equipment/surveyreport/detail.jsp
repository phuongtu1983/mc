<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" width="100%" class="its" id="materialDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.del"/></th>
             <th><bean:message key="message.surveyreport.equId"/></th>
            <th><bean:message key="message.surveyreport.code"/></th>
            <th><bean:message key="message.surveyreport.quantity"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.SURVEYREPORT_MATERIAL_LIST%>">
            <tr>
                <td width="30px">
                    <div align="center"><input type="checkbox" name="detId" value='<bean:write name="detail" property="detId"/>'/></div>
                    <input type="hidden" name="reqMatId" value='<bean:write name="detail" property="detId"/>'/>
                    <html:hidden name="detail" property="matId"/>
                </td>
                <td>
                    <span><bean:write name="detail" property="matName"/></span>
                    <html:hidden name="detail" property="matName"/>
                </td>
                <td>
                    <span><bean:write name="detail" property="code"/></span>
                    <html:hidden name="detail" property="code"/>
                </td>
                <td width="80px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="detail" property="qt"  onblur="return tryNumberFormat(this)" /></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>