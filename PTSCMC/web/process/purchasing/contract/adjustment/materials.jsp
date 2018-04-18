<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
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
%>
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
            <td><span><bean:write name="detail" property="stt"/></span></td>
            <td><span><bean:write name="detail" property="matName"/></span></td>
            <td><span><bean:write name="detail" property="requestNumber"/></span></td>
            <td><span><bean:write name="detail" property="matOrigin"/></span></td>
            <td>
                <span><bean:write name="detail" property="unit"/></span>
                <html:hidden name="detail" property="unit"/>
            </td>
            <td width="60px"><html:text readonly="true" style="border:0px" size="7" name="detail" property="quantity1" styleId="det2quantity${detail.matId}"  onblur="return checkContractMatValidQuantity2('${detail.matId}');tryNumberFormat(this);caculateAdjustmentDetail(${detail.matId})" /></td>
            <td width="40px"><input type="text" readonly style="border:0px" size="10" name="price1" id="det2price<bean:write name="detail" property="matId"/>" value="<bean:write name="detail" property="price1"/>"  onblur="tryNumberFormat(this);caculateAdjustmentDetail(${detail.matId})" /></td>
            <td width="30px"><input type="text" readonly style="border:0px" size="2" name="vat" id="det2vat<bean:write name="detail" property="matId"/>" value="<bean:write name="detail" property="vat"/>"  onblur="tryNumberFormat(this);caculateAdjustmentDetail(${detail.matId})" /></td>
            <td width="50px"><input type="text" style="border:0px" size="12" name="detTotal" readonly id="det2Total<bean:write name="detail" property="matId"/>" value="<bean:write name="detail" property="total"/>"  onblur="caculateAdjustmentDetail(${detail.matId})"/></td>
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
</table>