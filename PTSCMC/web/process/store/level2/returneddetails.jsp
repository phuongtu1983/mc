<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" id="rmsDetailTable" class="its">
    <thead>
        <tr>
            <th></th>
            <th><bean:message key="message.material.nameVn"/></th>
            <th><bean:message key="message.material.uniId"/></th>
            <th><bean:message key="message.rms.umsQuantity"/></th>
            <th><bean:message key="message.rms.rmsQuantity"/></th>
            <th><bean:message key="message.status"/></th>
            <th><bean:message key="message.note"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.RMS_DETAIL_LIST%>">
            <logic:iterate id="detail" indexId="counter" name="<%=Constants.RMS_DETAIL_LIST%>">
                <bean:define id="mod2" value="${counter%2}"/>
                <logic:equal name="mod2" value="1"><tr class="odd"></logic:equal>
                <logic:equal name="mod2" value="0"><tr class="even"></logic:equal>
                    <td width="30px">
                        <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                        <input type="hidden" name="rmsDetId" value="<bean:write name="detail" property="detId"/>"/>
                        <html:hidden name="detail" property="umsDetId"/>
                        <html:hidden name="detail" property="matId"/>
                        <html:hidden name="detail" property="reqDetailId"/>
                    </td>
                    <td><span><bean:write name="detail" property="matName"/></span></td>
                    <td><span><bean:write name="detail" property="unit"/></span></td>
                    <td width="60px"><html:text size="10" name="detail" property="currentQuantity" readonly="true" styleId="currentquantity${detail.reqDetailId}"/></td>
                    <td width="60px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="detail" styleId="returnedquantity${detail.reqDetailId}" property="returnedQuantity"  onblur="tryNumberFormat(this);rmsCheckQuantity(${detail.reqDetailId});" /></td>
                    <td><html:textarea rows="1" cols="10" name="detail" property="status"/></td>
                    <td><html:textarea rows="1" cols="10" name="detail" property="note"/></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>