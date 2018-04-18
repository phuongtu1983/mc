<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import ="com.venus.core.util.*"%>
<%
            int kind = (int) NumberUtil.parseInt(session.getAttribute("kind").toString(),0);
%>
<table style="width:100%" class="its" id="techEvalDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.techeval.stt"/></th>
            <th><bean:message key="message.techeval.matId"/></th>
            <th><bean:message key="message.techeval.unit"/></th>
            <th><bean:message key="message.techeval.qty"/></th>
            <th><bean:message key="message.techeval.provideDate"/></th>
            <th><bean:message key="message.techeval.spec"/></th>
            <th>
                <bean:message key="message.techeval.delivery_time"/>
                <html:image src="images/arrow2.gif" onclick="return duplicateObject('techEvalDetailForm','deliveryTime');"/>
            </th>
            <% if (kind == 1) {%>
            <th><bean:message key="message.techeval.otherCondition"/></th>            
            <% }%>
            <th>
                <bean:message key="message.techeval.evalTbe"/>
                <html:image src="images/arrow2.gif" onclick="return duplicateObject('techEvalDetailForm','evalTbe');"/>
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.TECH_EVAL_DETAIL_LIST%>">
            <tr>
                <td><p align="center"><bean:write name="detail" property="stt"/></p><input type="hidden" name="detId" value="${detail.detId}"/></td>
                <td width="180px"><p align="left"><bean:write name="detail" property="nameVn"/></p></td>
                <td><p align="center"><bean:write name="detail" property="unit"/></p><input type="hidden" name="unit" value="${detail.unit}"/></td>
                <td><p align="center"><bean:write name="detail" property="qty"/></p><input type="hidden" name="qty" value="${detail.qty}"/></td>
                <td><p align="center"><bean:write name="detail" property="provideDate"/></p></td>
                <td width="80px"><p align="center"><html:textarea style="border-width:1px;text-align:left"  cols="40" rows="3" name="detail" property="spec" /></p></td>
                <td width="80px"><p align="center"><html:text style="border-width:1px;text-align:left"  size="10" name="detail" property="deliveryTime" /></p></td>
                <% if (kind == 1) {%>
                <td width="80px"><p align="center"><html:text style="border-width:1px;text-align:left"  size="10" name="detail" property="otherCondition" /></p></td>                   
                <% }%>
                <td width="80px">
                    <html:select name="detail" property="evalTbe" style="border-width:1px;text-align:left" >
                        <html:options collection="<%=Constants.EVAL_TBE_LIST%>" property="value" labelProperty="label"/>
                    </html:select></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>