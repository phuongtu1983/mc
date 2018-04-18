<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.contractlist.title"/>/<bean:message key="message.list.s"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchContract.do" onsubmit="return searchContract(document.forms['contractsForm'].kind.value);">
    <table>
        <tr>
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_PURCHASING_CONTRACT)) {%>
                <img alt="delete" src="images/ico_xoa.gif" onclick="return delContracts(<%=ContractBean.KIND_CONTRACT%>);"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_CONTRACT)) {%>
                <img alt="add" src="images/ico_them.gif" onclick="return contractForm(<%=ContractBean.KIND_CONTRACT%>);"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.contract.number" value='1'/>
                        <html:option key="message.comevalmaterial.vendorName" value='2'/>
                        <!--<html:option key="message.vendor.material.group" value='3'/>-->
                        <html:option key="message.request" value='4'/>
                        <!--<html:option key="message.intermemo" value='5'/>-->
                        <html:option key="message.contract.responseEmp" value='6'/>
                        <html:option key="message.contract.followEmp" value='7'/>
                        <html:option key="message.contract.status.payments" value='8'/>
                        <html:option key="message.contract.process.status" value='9'/>
                        <html:option key="message.contract.deliveryDate" value='10'/>
                        <html:option key="message.contract.note" value='11'/>
                        <html:option key="message.material.nameVn" value='14'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <input type="button" onclick="return searchContract(<%=ContractBean.KIND_CONTRACT%>);" value="<bean:message key="message.search"/>"/>
                    <input type="button" onclick="return searchAdvContractForm(<%=ContractBean.KIND_CONTRACT%>);" value="<bean:message key="message.detailSearch"/>"/>
                    <img onclick="return exportContract(<%=ContractBean.KIND_CONTRACT%>);" src="images/but_print.gif"/>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='contractsForm' id='contractsForm'><div id='contractList'></div><input type="hidden" id="kind" value="<%=ContractBean.KIND_CONTRACT%>"/></form>
<input type="hidden" id="contractKindHidden" value="contract"/>
<input type="hidden" id="kind" value="<%=ContractBean.KIND_CONTRACT%>"/>