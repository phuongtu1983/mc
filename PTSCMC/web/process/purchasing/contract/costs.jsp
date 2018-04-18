<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<div>
    <!--<html:image src="images/ico_xoa.gif" onclick="return delContractCosts();"/>
    <html:image src="images/ico_them.gif" onclick="return setSelectedContractCostProject();"/>-->
    <span id="listContractProjectDataSpan"><select name="project"></select></span>
    <input type="text" class="lbl10" style="border-width:1px;text-align:right;display:none" size="30" id="inputCostTemplate"  onblur="tryNumberFormat(this)" />
</div>
<table style="width:100%" id="contractCostTable" class="its">
    <thead>
        <tr>
            <th width="50px"></th>
            <th><bean:message key="message.contract.cost.project"/></th>
            <th width="150px"><bean:message key="message.contract.cost.cost"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.CONTRACT_PROJECT_LIST%>">
            <logic:iterate id="detail" indexId="counter" name="<%=Constants.CONTRACT_PROJECT_LIST%>">
                <tr>
                    <td width="30px">
                        <div align="center"><input type="checkbox" name="pcId" value="<bean:write name="detail" property="pcId"/>"/></div>
                        <input type="hidden" name="conProId" value="<bean:write name="detail" property="pcId"/>"/>
                        <html:hidden name="detail" property="proId"/>
                    </td>
                    <td><span><bean:write name="detail" property="projectName"/></span></td>
                    <td width="60px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="detail" property="cost"  onblur="tryNumberFormat(this)" /></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>