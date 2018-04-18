<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.contract.ContractFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%
    String detIds = "";
    String matIds = "";
    String reqIds = "";
    ContractFormBean contract = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
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
%>
<table style="width:100%"  id="adjustmentDetailTable" class="its">
    <thead>
        <tr>
            <th width="20px"></th>
            <th><bean:message key="message.rowNum"/></th>
            <th><bean:message key="message.contract.material.name"/></th>
            <th><bean:message key="message.request"/></th>
            <th><bean:message key="message.contract.material.origin"/></th>
            <th><bean:message key="message.contract.material.unit"/></th>
            <th><bean:message key="message.contract.material.quantity"/></th>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
            <th><bean:message key="message.contract.material.price"/></th>
            <th><bean:message key="message.contract.material.VAT"/></th>
            <th><bean:message key="message.contract.material.total"/></th>
            <%}%>
            <th><bean:message key="message.adjustment.matName"/></th>
            <th><bean:message key="message.contract.material.unit"/></th>
            <th><bean:message key="message.contract.material.quantity"/></th>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
            <th><bean:message key="message.contract.material.price"/></th>
            <%}%>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.CONTRACT_DETAIL_LIST%>">
            <logic:iterate id="detail" indexId="counter" name="<%=Constants.CONTRACT_DETAIL_LIST%>">
                <bean:define id="mod2" value="${counter%2}"/>
                <logic:equal name="mod2" value="1"><tr class="odd"></logic:equal>
                <logic:equal name="mod2" value="0"><tr class="even"></logic:equal>
                        <td width="30px">
                        <logic:equal value="0" name="detail" property="cb">
                            <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                            </logic:equal>
                            <logic:greaterThan value="0" name="detail" property="cb">
                            <div align="center"><input type="checkbox" disabled name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                            </logic:greaterThan>
                        <input type="hidden" name="conDetId" value="<bean:write name="detail" property="detId"/>"/>
                        <input type="hidden" name="reqDetId" value="${detail.reqDetailId}"/>
                        <input type="hidden" id="materialTempName${detail.detId}" value="${detail.materialName}"/>
                        <html:hidden name="detail" property="matId"/>
                    </td>
                    <td width="10px"><bean:write name="detail" property="stt"/></td>
                    <td><span><bean:write name="detail" property="matName"/></span></td>
                    <td><span><bean:write name="detail" property="requestNumber"/></span></td>
                    <td><span><bean:write name="detail" property="matOrigin"/></span></td>
                    <td>
                        <span><bean:write name="detail" property="unit"/></span>
                        <html:hidden name="detail" property="unit"/>
                    </td>
                    <td width="40px"><html:text readonly="true" style="border:0px;text-align: right" size="6" name="detail" property="quantity1" styleId="det2quantity${detail.matId}"  onblur="tryNumberFormat(this);caculateAdjustmentDetail(${detail.matId})" /></td>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
                    <td width="40px"><input type="text"  style="border:0px;text-align: right" size="10" name="price1" id="det2price<bean:write name="detail" property="matId"/>" value="<bean:write name="detail" property="price1"/>"  onblur="tryNumberFormat(this);caculateAdjustmentDetail(${detail.matId})" /></td>
                    <td width="30px"><input type="text"  style="border:0px;text-align: right" size="3" name="vat" id="det2vat<bean:write name="detail" property="matId"/>" value="<bean:write name="detail" property="vat"/>"  onblur="tryNumberFormat(this);caculateAdjustmentDetail(${detail.matId})" /></td>
                    <td width="60px"><input type="text" style="border:0px;text-align: right" size="12" name="detTotal" readonly id="det2Total<bean:write name="detail" property="matId"/>" value="<bean:write name="detail" property="total"/>"  onblur="caculateAdjustmentDetail(${detail.matId})"/></td>
                        <%} else {%>
                        <html:hidden property="price" name="detail"/>
                        <html:hidden property="vat" name="detail"/>                    
            <input type="text" hidden="" name="detTotal" readonly id="detTotal" />
            <%}%>
            <td width="80px"><p align="center"><html:textarea style="border-width:1px;text-align:left"  cols="40" rows="3" name="detail" property="materialName" /></p></td>
            <td width="10px"><input type="text" readonly style="border:0px" size="3" name="unit1" value="<bean:write name="detail" property="unit"/>"  /></td>
            <td width="20px"><input type="text" styleClass="lbl10" style="border-width:1px;text-align:right" size="7" name="quantity" value="<bean:write name="detail" property="quantity"/>"  onblur="tryNumberFormat(this);" /></td>
            <td width="50px"><input type="text" styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="price"  value="<bean:write name="detail" property="price"/>"  onblur="tryNumberFormat(this);" /></td>
            <logic:greaterThan name="detail" property="detId" value="0">
            <td>
                <img src="images/ico_them.gif" onclick="return selectMaterialOrderContract('setSelectedMaterialOrderContract','',${detail.matId},'${detail.reqDetailId}','<%=matIds%>','<%=reqIds%>','<%=detIds%>',${detail.detId},'8');"/>
                <img src="images/ico_themvt.gif" onclick="return newMaterialOrderContract('setSelectedMaterialOrderContract',null,'<bean:message key="message.contract.material.matarialName"/>/<bean:message key="message.material.add"/>',${detail.detId},'${detail.reqDetailId}','<%=matIds%>','<%=reqIds%>','<%=detIds%>','8','${detail.matId}');"/>
            </td>
            </logic:greaterThan>


        </tr>
    </logic:iterate>
</logic:present>
</tbody>
</table>