<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" id="mivDetailTable" class="its">
    <thead>
        <tr>
            <th width="20px"></th>
            <th><bean:message key="message.material.nameVn"/></th>
            <th><bean:message key="message.material.code"/></th>
            <th><bean:message key="message.material.uniId"/></th>
            <th><bean:message key="message.contract.material.quantity"/></th>
            <th><bean:message key="message.contract.material.price"/></th>
            <th><bean:message key="message.contract.material.total"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.MIV_DETAIL_LIST%>">
            <logic:iterate id="detail" indexId="counter" name="<%=Constants.MIV_DETAIL_LIST%>">
                <bean:define id="mod2" value="${counter%2}"/>
                <logic:equal name="mod2" value="1"><tr class="odd"></logic:equal>
                <logic:equal name="mod2" value="0"><tr class="even"></logic:equal>
                    <td width="30px">
                        <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                        <input type="hidden" name="mivDetId" value="<bean:write name="detail" property="detId"/>"/>
                        <input type="hidden" name="reqDetId" value="<bean:write name="detail" property="reqDetailId"/>"/>
                        <html:hidden name="detail" property="matId"/>
                    </td>
                    <td><span><bean:write name="detail" property="matName"/></span></td>
                    <td><span><bean:write name="detail" property="matCode"/></span></td>
                    <td>
                        <span><bean:write name="detail" property="unit"/></span>
                        <html:hidden name="detail" property="unit"/>
                    </td>
                    <td width="60px"><html:text size="10" name="detail" property="quantity" readonly="true" styleId="detquantity${detail.matId}"  onblur="tryNumberFormat(this);caculateMivDetail(${detail.matId})" /></td>
                    <td width="40px"><html:text size="10" name="detail" property="price" readonly="true" styleId="detprice${detail.matId}"/></td>
                    <td width="40px"><input type="text" size="10" name="detTotal" readonly id="detTotal<bean:write name="detail" property="matId"/>" value="<bean:write name="detail" property="total"/>"/></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>