<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table  style="width:100%" class="its" id="dn2DetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.del"/></th>            
            <th><bean:message key="message.dn2.matName"/></th>
      <!--  <th><bean:message key="message.dn2.materialGrade"/></th>
            <th><bean:message key="message.dn2.materialType"/></th>
            <th><bean:message key="message.dn2.size1"/></th>
            <th><bean:message key="message.dn2.size2"/></th>
            <th><bean:message key="message.dn2.size3"/></th>
            <th><bean:message key="message.dn2.poNo"/></th>
            <th><bean:message key="message.dn2.mrirNo"/></th>
            <th><bean:message key="message.dn2.heatNo"/></th>
            <th><bean:message key="message.dn2.traceNo"/></th>-->
            <th><bean:message key="message.dn2.unit"/></th>
            <th><bean:message key="message.dn2.quantity"/></th>
            <th><bean:message key="message.dn.requestNumber"/></th>
            <th><bean:message key="message.dn2.area"/></th>
            <th><bean:message key="message.dn2.weight"/></th>
            <th><bean:message key="message.dn2.location"/></th>
            <th><bean:message key="message.dn2.remark"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.DN_DETAIL_LIST%>">
            <logic:iterate id="detail" name="<%=Constants.DN_DETAIL_LIST%>">
                <tr>
                    <td width="30px">
                        <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                        <input type="hidden" name="delDetId" value="<bean:write name="detail" property="detId"/>"/>
                        <html:hidden name="detail" property="matId"/>
                        <html:hidden name="detail" property="price"/>
                        <html:hidden name="detail" property="note"/>
                        <html:hidden name="detail" property="reqDetailId"/>
                        <html:hidden name="detail" property="conDetId"/>
                    </td>
                    <td><span><bean:write name="detail" property="matName"/></span></td>
<!--                    <td width="4px"><input type="textbox" size="4" name="materialGrade" value="${detail.materialGrade}"/></td>
                    <td width="4px"><input type="textbox" size="4" name="materialType" value="${detail.materialType}"/></td>
                    <td width="4px"><input type="textbox" size="4" name="size1" value="${detail.size1}"/></td>
                    <td width="4px"><input type="textbox" size="4" name="size2" value="${detail.size2}"/></td>
                    <td width="4px"><input type="textbox" size="4" name="size3" value="${detail.size3}"/></td>
                    <td width="4px"><input type="textbox" size="4" name="poNo" value="${detail.poNo}"/></td>
                    <td width="4px"><input type="textbox" size="4" name="mrirNo" value="${detail.mrirNo}"/></td>
                    <td width="4px"><input type="textbox" size="4" name="heatNo" value="${detail.heatNo}"/></td>
                    <td width="4px"><input type="textbox" size="4" name="traceNo" value="${detail.traceNo}"/></td>-->
                    <td>
                        <span><bean:write name="detail" property="unit"/></span>
                        <html:hidden name="detail" property="unit"/>
                    </td>
                    <td width="7px"><input type="textbox" styleClass="lbl10" style="border-width:1px;text-align:right" readonly size="7" name="quantity" id="detquantity${detail.detId}" value="${detail.quantity}"  onblur="dn2CheckQuantity(${detail.detId});" /></td>
                    <td><span><bean:write name="detail" property="requestNumber"/></span></td>
                    <td width="4px"><input type="textbox" size="4" name="area" value="${detail.area}"/></td>
                    <td width="4px"><input type="textbox" size="4" name="weight" value="${detail.weight}"/></td>
                    <td width="4px"><input type="textbox" size="4" name="location" value="${detail.location}"/></td>
                    <td width="4px"><input type="textbox" size="4" name="remark" value="${detail.remark}"/></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>