<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="tenderPlanCertificateTbl" class="its"  style="width:100%">
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.del"/></th>
            <th><bean:message key="message.tenderplan.certificate.name"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="certificate" name="<%=Constants.MATERIAL_CERTIFICATE_LIST%>">
            <tr>
                <td>
                    <input type="checkbox" name="tenderPlanCertificateChk" value="<bean:write name="certificate" property="tcId"/>"/>
                    <input type="hidden" name="tcId" value="<bean:write name="certificate" property="tcId"/>"/>
                    <input type="hidden" name="cerId" id="cerId_<bean:write name="certificate" property="cerId"/>" value="<bean:write name="certificate" property="cerId"/>"/>
                </td>
                <td><span><bean:write name="certificate" property="cerName"/></span></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>