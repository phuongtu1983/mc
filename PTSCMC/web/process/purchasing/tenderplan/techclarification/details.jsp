<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" class="its" id="techClarificationDetailTable">
    <thead>
        <tr>
            <th title="Click here to delete !" onmouseover="Tip('Click here to delete ! <br><img src=images/hinh.gif >', WIDTH, 120, PADDING, 6, BGCOLOR, 'yellow')" onmouseout="UnTip()" onclick="return delTechClarificationDetail();" style="cursor:pointer;color:red"><bean:message key="message.del"/></th>
            <th><bean:message key="message.techclarificationdetail.subcategory"/></th>
            <th><bean:message key="message.techclarificationdetail.reference"/></th>
            <th><bean:message key="message.techclarificationdetail.clarification"/></th>
            <th><bean:message key="message.techclarificationdetail.supplierReply"/></th>
            <th><bean:message key="message.techclarificationdetail.status"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.TCL%>">
            <tr>
                <td>
                    <input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/>
                    <input type="hidden" name="detId1" value="<bean:write name="detail" property="detId"/>"/>
                </td>
                <td><p align="left"><bean:write name="detail" property="subcategory"/></p></td>
                <td><p align="left"><bean:write name="detail" property="reference"/></p></td>
                <td><p align="center"><bean:write name="detail" property="clarification"/></p></td>
                <td><p align="center"><bean:write name="detail" property="supplierReply"/></p></td>
                <td width="80px"><html:select name="detail" property="status" style="border:0px;background-color:#fea">
                <html:options collection="<%=Constants.TCL_STATUS_LIST%>" property="value" labelProperty="label"/> </html:select></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>