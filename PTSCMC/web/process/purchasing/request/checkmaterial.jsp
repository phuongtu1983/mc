<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.venus.mc.util.Constants"%>
<div style="width:500px;height:450px;overflow:auto;" >
    <form name="checkRequestForm">
        <table width="100%">
            <tr>
                <td class="lbl10"><bean:message key="message.request.number"/></td>
                <td><html:text styleClass="lbl10" size="50" readonly="true" property="requestNumber" name="<%=Constants.REQUEST%>"/></td>
            </tr>
            <tr>
                <td class="lbl10"><bean:message key="message.store"/></td>
                <td>
                    <html:select style="width: 330px;" property="orgId" name="<%=Constants.REQUEST%>" onchange="return checkMaterialRequest()">
                        <html:options collection="<%=Constants.STORE_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td class="lbl10"><bean:message key="message.material.nameVn"/></td>
                <td>
                    <select style="width: 330px;" name="material" onchange="return checkMaterialRequest()">
                        <logic:iterate id="detail" name="<%=Constants.MATERIAL_LIST%>">
                            <option class="lbl9" value="${detail.matId}">${detail.matName}</option>
                        </logic:iterate>
                    </select>
                </td>
            </tr>
        </table>
        <html:hidden property="reqId" name="<%=Constants.REQUEST%>"/>
    </form>
    <form name="checkMaterialRequestForm">
        <div id='checkMaterialRequestList'></div>
    </form>
</div>