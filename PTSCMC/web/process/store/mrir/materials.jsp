<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>

<table id="materialTbl" class="its" style="width:100%">
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.del"/></th>
            <th><bean:message key="message.mrirdetail.matId"/></th>
            <th><bean:message key="message.mrirdetail.unit"/></th>
            <th><bean:message key="message.mrirdetail.quantity"/></th>
            <th><bean:message key="message.mrirdetail.manufacture"/></th>
            <th><bean:message key="message.mrirdetail.heatSerial"/></th>
            <th><bean:message key="message.mrirdetail.inspection"/></th>
            <th><bean:message key="message.mrirdetail.original"/></th>
            <th><bean:message key="message.mrirdetail.quality"/></th>
            <th><bean:message key="message.mrirdetail.warranty"/></th>
            <th><bean:message key="message.mrirdetail.insurance"/></th>
            <th><bean:message key="message.mrirdetail.approvalType"/></th>
            <th><bean:message key="message.mrirdetail.complCert"/></th>            
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.MRIR_MATERIAL_LIST%>"> 
            <logic:iterate id="material" name="<%=Constants.MRIR_MATERIAL_LIST%>">
                <tr>
                    <td width="20px">
                        <input type="checkbox" name="mrirMaterialChk" value="<bean:write name="material" property="detId"/>">
                        <input type="hidden" name="material" value="<bean:write name="material" property="matId"/>"/>
                    </td>
                    <td><bean:write name="material" property="matName"/></td>
                    <td><bean:write name="material" property="unit"/></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="quantity"  onblur="return tryNumberFormat(this)" /></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="manufacture" /></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="heatSerial" /></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="inspection" /></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="original" /></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="quality" /></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="warranty" /></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="insurance" /></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="approvalType" /></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="complCert" /></td>
                </tr>
            </logic:iterate>
        </logic:present> 
    </tbody>
</table>