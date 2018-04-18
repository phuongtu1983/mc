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
<table style="width:100%" id="contractDetailTable" class="its">
    <thead>
        <tr>
            <th width="20px"></th>
            <th><bean:message key="message.rowNum"/></th>
            <th><bean:message key="message.contract.material.name"/></th>
            <% if (contract.getIsNotResell() == 0) {%>
            <th><bean:message key="message.contract.material.matarialName"/></th>
            <%}%>
            <th><bean:message key="message.request"/></th>
            <th><bean:message key="message.contract.material.origin"/></th>
            <th><bean:message key="message.contract.material.unit"/></th>
            <th><bean:message key="message.contract.material.quantity"/></th>
            <%if (isPricePer == 1) {%>
            <th><bean:message key="message.contract.material.price"/></th>
            <th><bean:message key="message.contract.material.VAT"/></th>
            <th><bean:message key="message.contract.material.total"/></th>
            <%}%>
            <th><bean:message key="message.status"/></th>
            <% if (contract.getIsNotResell() == 0) {%>
            <th><bean:message key="message.confirm"/></th>  
            <%}%>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.CONTRACT_DETAIL_LIST%>">
            <logic:iterate id="detail" indexId="counter" name="<%=Constants.CONTRACT_DETAIL_LIST%>">
                <bean:define id="mod2" value="${counter%2}"/>
                <logic:equal name="mod2" value="1"><tr class="odd"></logic:equal>
                <logic:equal name="mod2" value="0"><tr class="even"></logic:equal>
                    <logic:equal value="0" name="detail" property="cb">
                        <td width="30px">
                            <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                            <input type="hidden" name="conDetId" value="<bean:write name="detail" property="detId"/>"/>
                            <input type="hidden" name="reqDetId" value="${detail.reqDetailId}"/>
                            <html:hidden name="detail" property="matId"/>
                            <html:hidden name="detail" property="matIdTemp"/>
                        </td>
                    </logic:equal>
                    <logic:greaterThan value="0" name="detail" property="cb">
                        <td width="30px">
                            <div align="center"><input disabled type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                            <input type="hidden" name="conDetId" value="<bean:write name="detail" property="detId"/>"/>
                            <input type="hidden" name="reqDetId" value="${detail.reqDetailId}"/>
                            <html:hidden name="detail" property="matId"/>
                            <html:hidden name="detail" property="matIdTemp"/>
                        </td>
                    </logic:greaterThan>
                        <input type="hidden" id="materialTempName${detail.detId}" value="${detail.materialName}"/>
                    <td width="10px"><bean:write name="detail" property="stt"/></td>
                    <td><span><bean:write name="detail" property="matName"/></span></td>
                    <% if (contract.getIsNotResell() == 0) {%>
                    <td width="60px"><html:textarea rows="2" cols="20" styleClass="lbl10" name="detail" property="materialName" styleId="materialName3_${detail.reqDetailId}" /></td>
                    <%} else {%>
            <input type="hidden" name="materialName"/>
            <%}%>
            <td><span><bean:write name="detail" property="requestNumber"/></span></td>
            <td><span><bean:write name="detail" property="matOrigin"/></span></td>
            <td>
                <span><bean:write name="detail" property="unit"/></span>
                <html:hidden name="detail" property="unit"/>
            </td>
            <td width="60px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="detail" property="quantity" styleId="detquantity${detail.matId}_${detail.reqDetailId}"  onblur="tryNumberFormat(this);caculateContractDetail('${detail.matId}_${detail.reqDetailId}')"/></td>
            <%if (isPricePer == 1) {%>
            <td width="40px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="10" name="price" id="detprice<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="price"/>"  onblur="tryNumberFormat(this);caculateContractDetail('${detail.matId}_${detail.reqDetailId}')"  /></td>
            <td width="30px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="3" name="vat" id="detvat<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="vat"/>"  onblur="tryNumberFormat(this);caculateContractDetail('${detail.matId}_${detail.reqDetailId}')"  /></td>
            <td width="40px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="10" name="detTotal" readonly id="detTotal<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="total"/>"  onblur="caculateContractDetail('${detail.matId}_${detail.reqDetailId}')" readonly/></td>
                <%} else {%>
                <html:hidden property="price" name="detail"/>
                <html:hidden property="vat" name="detail"/>
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
            <% if (contract.getIsNotResell() == 0) {%>
            <td width="80px"><html:select name="detail" property="confirm" style="border-width:1px;text-align:left" >
                    <html:options collection="<%=Constants.EVAL_TBE_LIST%>" property="value" labelProperty="label"/> </html:select></td>
                <%} else {%>
            <select style="display: none"></select>
            <%}%>

            <%

                if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_ORDER, contract.getCreatedEmp())) {
            %>
            <% if (contract.getIsNotResell() == 0) {

            %>
            <td>
                <img src="images/ico_them.gif" onclick="return selectMaterialOrderContract('setSelectedMaterialOrderContract','',${detail.matId},'${detail.reqDetailId}','<%=matIds%>','<%=reqIds%>','<%=detIds%>',${detail.detId},'3');"/>
                <img src="images/ico_themvt.gif" onclick="return newMaterialOrderContract('setSelectedMaterialOrderContract',null,'<bean:message key="message.contract.material.matarialName"/>/<bean:message key="message.material.add"/>',${detail.detId},'${detail.reqDetailId}','<%=matIds%>','<%=reqIds%>','<%=detIds%>','3','${detail.matId}');"/>
                <img src="images/ico_xoa.gif" onclick="return deleteMaterialOrderContract(${detail.reqDetailId});"/>
            </td>
            <%}
                }%>
        </tr>
    </logic:iterate>
</logic:present>
</tbody>
</table>
