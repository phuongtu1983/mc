<%@page import="com.venus.core.util.NumberUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%
    String detIds = "";
    String matIds = "";
    String reqIds = "";
    if (request.getAttribute("detIds") != null) {
        detIds = request.getAttribute("detIds").toString();
    }
    if (request.getAttribute("reqIds") != null) {
        reqIds = request.getAttribute("reqIds").toString();
    }
    if (request.getAttribute("matIds") != null) {
        matIds = request.getAttribute("matIds").toString();
    }
    int isPricePer = 0;
    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {
        isPricePer = 1;
    }
    ContractBean contract = (ContractBean) request.getAttribute(Constants.CONTRACT);
%>
<table width="100%" id="orderSourceMaterialTable">
    <logic:iterate id="detail" name="<%=Constants.MATERIAL_LIST%>">
        <tr>
            <td width="30px">
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="conDetId" value="${detail.detId}"/>
                <input type="hidden" name="reqDetId" value="${detail.reqDetailId}"/>
                <html:hidden name="detail" property="matId"/>
                <html:hidden name="detail" property="matIdTemp"/>
                <input type="hidden" id="maxMatQuantity${detail.matId}_${detail.reqDetailId}" value="${detail.remainQuantity}"/>
            </td>
            <td></td>
            <td><span><bean:write name="detail" property="matName"/></span></td>
            <% if (contract.getNote().equals("muale")) {%>
            <td width="60px"><html:textarea rows="2" cols="20" styleClass="lbl10" name="detail" property="materialName"  /></td>
            <% }%>
            <td><span><bean:write name="detail" property="requestNumber"/></span></td>
            <td><span><bean:write name="detail" property="matOrigin"/></span></td>
            <td>
                <span><bean:write name="detail" property="unit"/></span>
                <html:hidden name="detail" property="unit"/>
            </td>
            <td width="60px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="detail" property="quantity" styleId="detquantity${detail.matId}_${detail.reqDetailId}"  onblur="return checkContractMatValidQuantity('${detail.matId}_${detail.reqDetailId}');tryNumberFormat(this);caculateContractDetail('${detail.matId}_${detail.reqDetailId}')" /></td>
            <%if (isPricePer == 1) {%>
            <td width="40px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="10" name="price" id="detprice<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="price"/>"  onblur="tryNumberFormat(this);caculateContractDetail('${detail.matId}_${detail.reqDetailId}')" /></td>
            <td width="30px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="3" name="vat" id="detvat<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="vat"/>"  onblur="tryNumberFormat(this);caculateContractDetail('${detail.matId}_${detail.reqDetailId}')" /></td>
            <td width="40px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="10" name="detTotal" readonly id="detTotal<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="total"/>"  onblur="caculateContractDetail('${detail.matId}_${detail.reqDetailId}')"/></td>
                <%} else {%>
                <input type="hidden" name="price"/>
                <input type="hidden" name="vat"/>
                <input type="hidden" name="detTotal"/>
        <%}%>
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
        <% if (contract.getNote().equals("muale")) {%>
        <td width="80px"><html:select name="detail" property="confirm" style="border-width:1px;text-align:left" >
                <html:options collection="<%=Constants.EVAL_TBE_LIST%>" property="value" labelProperty="label"/> </html:select></td>                
            <td>
                <img src="images/ico_them.gif" onclick="return selectMaterialOrderContract('setSelectedMaterialOrderContract','',${detail.matId},'${detail.reqDetailId}','<%=matIds%>','<%=reqIds%>','<%=detIds%>',${detail.detId});"/>
            <img src="images/ico_themvt.gif" onclick="return newMaterialOrderContract('setSelectedMaterialOrderContract',null,'<bean:message key="message.contract.material.matarialName"/>/<bean:message key="message.material.add"/>',${detail.detId},'${detail.reqDetailId}','<%=matIds%>','<%=reqIds%>','<%=detIds%>');"/>
        </td>
        <% }%>
    </tr>
</logic:iterate>
</table>