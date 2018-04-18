<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table class="its" id="requestMaterialTable">
    <logic:iterate id="material" name="<%=Constants.MATERIAL_LIST%>">
        <tr>
            <td width="10px"></td>
            <td width="30px">
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="reqDetId" value="0"/>
                <html:hidden name="material" property="matId"/>
            </td>
            <td class="lbl10"><span><bean:write name="material" property="matCode"/></span></td>
            <td class="lbl10"><span><bean:write name="material" property="matName"/></span></td>
            <td class="lbl10">
                <html:text readonly="true" styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="unit"/>
            </td>
            <td class="lbl10" style="text-align:right;">
                <!--<span><bean:write name="material" property="presentQuantity"/></span>
                <html:hidden name="material" property="presentQuantity"/>-->
                <html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="presentQuantity" onblur="return tryNumberFormat(this)" onkeypress="return readonlyFloat(event);"/>
            </td>
            <td style="display:none"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="additionalQuantity"  onblur="return tryNumberFormat(this)" onkeypress="return readonlyFloat(event);"/></td>
            <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="requestQuantity"  onblur="return tryNumberFormat(this)" onkeypress="return readonlyFloat(event);" /></td>
            <td><input type="textbox" class="lbl10" style="border-width:1px;text-align:right" size="9" name="deliverDate" id="deliverDate${material.matId}" onclick="javascript:showCalendar('deliverDate${material.matId}')"/></td>
            <!--<td>
                <select name="materialKind">
                    <logic:iterate id="kind" name="<%=Constants.REQUEST_MATERIAL_KIND_LIST%>">
                        <logic:equal name="kind" property="value" value="1">
                            <option value="${kind.value}">${kind.label}</option>
                        </logic:equal>
                        <logic:equal name="kind" property="value" value="2">
                            <option selected value="${kind.value}">${kind.label}</option>
                        </logic:equal>
                    </logic:iterate>
                </select>
            </td>-->
        </tr>
    </logic:iterate>
</table>
