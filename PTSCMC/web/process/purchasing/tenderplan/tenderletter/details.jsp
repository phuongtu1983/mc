<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import ="com.venus.core.util.*"%>
<%
    int kind = (int) NumberUtil.parseInt(request.getAttribute(Constants.TENDERPLAN_EVALKIND).toString(), 0);
%>
<table style="width:100%"  class="its" id="tenderLetterDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.tenderplan.bidclosing.replied"/></th>

            <th><bean:message key="message.tenderletter.vendor"/></th>
            <th><bean:message key="message.tenderletter.subfix"/></th>
            <th><bean:message key="message.tenderletter.phone"/></th>
            <th><bean:message key="message.tenderletter.fax"/></th>
            <th><bean:message key="message.tenderletter.email"/></th>
            <!--<th><bean:message key="message.tenderletter.address"/></th>-->
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.TENDER_LETTER_LIST%>">
            <tr>
        <input type="hidden" name="regDetId" value='<bean:write name="detail" property="detId"/>'/>
        <html:hidden name="detail" property="tevId"/>
        <html:hidden name="detail" property="detId"/>
        <html:hidden name="detail" property="venId"/>


        <td><input type="checkbox" name="vendorBidded" <logic:notEqual name="detail" property="bidded" value="0"> checked </logic:notEqual> value="<bean:write name="detail" property="venId"/>"></td>

            <td><span><bean:write name="detail" property="name"/></span></td>
        <td width="60px"><html:text style="border-width:1px;text-align:left" size="20" name="detail" property="subfix" /></td>
        <td width="60px"><span><bean:write name="detail" property="phone"/></span></td>
        <td width="60px"><span><bean:write name="detail" property="fax"/></span></td>
        <td width="50px"><span><bean:write name="detail" property="email"/></span></td>
        <!--<td><span><bean:write name="detail" property="address"/></span></td>-->
    </tr>
</logic:iterate>
</tbody>
</table>
