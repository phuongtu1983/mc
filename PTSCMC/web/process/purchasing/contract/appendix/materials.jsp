<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table width="100%" id="orderSourceMaterialTable">
    <logic:iterate id="detail" name="<%=Constants.MATERIAL_LIST%>">
        <tr>
            <td width="30px">
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="conDetId" value="0"/>
                <input type="hidden" name="reqDetId" value="${detail.reqDetailId}"/>
                <html:hidden name="detail" property="matId"/>
                <input type="hidden" id="maxMatQuantity2${detail.matId}" value="${detail.remainQuantity}"/>
            </td>
            <td></td>
            <td><span><bean:write name="detail" property="matName"/></span></td>
            <td><span><bean:write name="detail" property="requestNumber"/></span></td>
            <td><span><bean:write name="detail" property="matOrigin"/></span></td>
            <td>
                <span><bean:write name="detail" property="unit"/></span>
                <html:hidden name="detail" property="unit"/>
            </td>
            <td width="60px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="detail" property="quantity" styleId="det2quantity${detail.matId}"  onblur="return checkContractMatValidQuantity2('${detail.matId}');tryNumberFormat(this);caculateAppendixDetail(${detail.matId})" /></td>
            <td width="40px"><input type="text" styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="price" id="det2price<bean:write name="detail" property="matId"/>" value="<bean:write name="detail" property="price"/>"  onblur="tryNumberFormat(this);caculateAppendixDetail(${detail.matId})" /></td>
            <td width="30px"><input type="text" styleClass="lbl10" style="border-width:1px;text-align:right" size="3" name="vat" id="det2vat<bean:write name="detail" property="matId"/>" value="<bean:write name="detail" property="vat"/>"  onblur="tryNumberFormat(this);caculateAppendixDetail(${detail.matId})" /></td>
            <td width="40px"><input type="text" style="border:0px" size="10" name="detTotal" readonly id="det2Total<bean:write name="detail" property="matId"/>" value="<bean:write name="detail" property="total"/>"  onblur="caculateAppendixDetail(${detail.matId})"/></td>
            <td width="80px">
                <logic:equal name="detail" property="matStatus" value="1">
                    <html:select property="matStatus" name="detail">
                        <html:options collection="<%=Constants.PRINCIPLE_MATERIAL_KIND_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:equal>
                <logic:equal name="detail" property="matStatus" value="2">
                    <html:select property="matStatus" name="detail">
                        <html:options collection="<%=Constants.PRINCIPLE_MATERIAL_CANCEL_KIND_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:equal>
            </td>
        </tr>
    </logic:iterate>
</table>