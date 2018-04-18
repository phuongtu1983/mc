<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" id="umsMaterialTable" class="its">
    <tbody>
        <logic:present name="<%=Constants.UMS_DETAIL_LIST%>">
            <logic:iterate id="detail" indexId="counter" name="<%=Constants.UMS_DETAIL_LIST%>">
                <bean:define id="mod2" value="${counter%2}"/>
                <logic:equal name="mod2" value="1"><tr class="odd"></logic:equal>
                <logic:equal name="mod2" value="0"><tr class="even"></logic:equal>
                    <td width="30px">
                        <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                        <html:hidden name="detail" property="umsDetId" value="0"/>
                        <html:hidden name="detail" property="matId"/>
                        <html:hidden name="detail" property="reqDetailId"/>
                        <html:hidden name="detail" property="mivId"/>
                    </td>
                    <td><span><bean:write name="detail" property="matName"/></span></td>
                    <td><span><bean:write name="detail" property="unit"/></span></td>
                    <td width="60px"><html:text size="10" name="detail" property="currentQuantity" readonly="true" styleId="currentquantity${detail.reqDetailId}"/></td>
                    <td width="60px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="detail" styleId="usedquantity${detail.reqDetailId}" property="usedQuantity"  onblur="tryNumberFormat(this);umsCheckQuantity(${detail.reqDetailId});" /></td>
                    <td><html:textarea rows="1" cols="10" name="detail" property="note"/></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>