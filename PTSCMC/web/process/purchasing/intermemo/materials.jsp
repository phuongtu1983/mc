<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="intermemoMaterialTable" style="width:100%">
    <logic:iterate id="material" name="<%=Constants.MATERIAL_LIST%>">
        <tr>
            <td width="30px">
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="reqDetId" value="0"/>
                <html:hidden name="material" property="matId"/>
            </td>
            <td><span><bean:write name="material" property="matName"/></span></td>
            <td>
                <span><bean:write name="material" property="unit"/></span>
                <html:hidden name="material" property="unit"/>
            </td>
            <td width="60px">
                <span><bean:write name="material" property="presentQuantity"/></span>
                <html:hidden name="material" property="presentQuantity"/>
            </td>
            <td width="80px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="material" property="additionalQuantity"  onblur="return tryNumberFormat(this)" /></td>
            <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="requestQuantity"  onblur="return tryNumberFormat(this)" /></td>
            <td>
                <select name="materialKind">
                    <logic:iterate id="kind" name="<%=Constants.REQUEST_MATERIAL_KIND_LIST%>">
                        <option value="${kind.value}">${kind.label}</option>
                    </logic:iterate>
                </select>
            </td>
            <td width="120px"><input name="note" class="lbl10" style="border-width:1px;text-align:right" size="40" value="" type="text"></td>
        </tr>
    </logic:iterate>
</table>