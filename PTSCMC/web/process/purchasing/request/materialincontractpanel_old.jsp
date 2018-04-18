<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.requestadd.title"/>/<bean:message key="message.materialincontractlist.title"/></h3>
<form name='materialInContractPrincipleForm' id='materialInContractPrincipleForm'>
    <br/><strong><bean:message key="message.material.incontractprinciple"/></strong>
    <br/><div id='materialInContractPrincipleList'></div>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
    <input type="button" onclick="return createDeliveryRequest(<%=ContractBean.KIND_DELIVERY_REQUEST%>);" value="<bean:message key="message.deliveryrequest.create"/>"/>
    <input type="button" onclick="return createTenderPlan('principle');" value="<bean:message key="message.tenderplan.create"/>"/>
    <%}%>
</form>
<form name='materialInContractPrincipleExpireForm' id='materialInContractPrincipleExpireForm'>
    <br/><strong><bean:message key="message.material.incontractprincipleexpire"/></strong>
    <br/><div id='materialInContractPrincipleExpireList'></div>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
    <input type="button" onclick="return createTenderPlan('principleexpire');" value="<bean:message key="message.tenderplan.create"/>"/>
    <%}%>
</form>
<form name='materialInContractForm' id='materialInContractForm'>
    <br/><strong><bean:message key="message.material.incontract"/></strong>
    <br/><div id='materialInContractList'></div>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
    <input type="button" onclick="return createAppendix(<%=ContractBean.KIND_CONTRACT%>,'<%=ContractBean.KIND_APPENDIX%>');" value="<bean:message key="message.appendix.create"/>"/>
    <input type="button" onclick="return createTenderPlan('contract');" value="<bean:message key="message.tenderplan.create"/>"/>
    <%}%>
</form>
<form name='materialInOrderForm' id='materialInOrderForm'>
    <br/><strong><bean:message key="message.material.inorder"/></strong>
    <br/><div id='materialInOrderList'></div>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
    <input type="button" onclick="return createOrder('order','<%=ContractBean.KIND_ORDER%>');" value="<bean:message key="message.order.create"/>"/>
    <input type="button" onclick="return createTenderPlan('order');" value="<bean:message key="message.tenderplan.create"/>"/>
    <%}%>
</form>
<form name='materialNotInContractForm' id='materialNotInContractForm'>
    <br/><strong><bean:message key="message.material.notincontract"/></strong>
    <br/><div id='materialNotInContractList'></div>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
    <input type="button" onclick="return createOrder('notincontract','<%=ContractBean.KIND_ORDER%>');" value="<bean:message key="message.retail"/>"/>
    <input type="button" onclick="return createTenderPlan('notincontract');" value="<bean:message key="message.tenderplan.create"/>"/>
    <%}%>
</form>