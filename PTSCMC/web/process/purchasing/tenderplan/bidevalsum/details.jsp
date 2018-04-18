<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" class="its" id="bidEvalSumDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.bidevalsum.stt"/></th>
            <th><bean:message key="message.bidevalsum.nameVn"/></th>
            <th><bean:message key="message.bidevalsum.unit"/></th>
            <th><bean:message key="message.bidevalsum.quantity"/></th>
            <th><bean:message key="message.bidevalsum.price"/></th>
            <th><bean:message key="message.comevalmaterial.priceAfter"/></th>
            <th><bean:message key="message.bidevalsum.total"/></th>
            <th><bean:message key="message.techeval.delivery_time"/></th> 
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.BID_EVAL_SUM_DETAIL_LIST%>">
            <tr>
                <td><p align="center"><bean:write name="detail" property="stt"/></p><input type="hidden" name="comDetId" value="${detail.detId}"/></td>
                <td><p align="left"><bean:write name="detail" property="nameVn"/></p><input type="hidden" name="matId" value="${detail.matId}"/></td>
                <td><p align="center"><bean:write name="detail" property="unit"/></p><input type="hidden" name="unit" value="${detail.unit}"/></td>
                <td><p align="center"><bean:write name="detail" property="quantity"/></p><input type="hidden" name="quantity" value="${detail.quantity}"/></td>                
                <td><p align="center"><bean:write name="detail" property="price"/></p><input type="hidden" name="price" value="${detail.price}"/></td>
                <td><p align="center"><bean:write name="detail" property="priceAfter"/></p><input type="hidden" name="priceAfter" value="${detail.priceAfter}"/></td>
                <td><p align="center"><bean:write name="detail" property="total"/></p><input type="hidden" name="total" value="${detail.total}"/></td>
                <td><p align="center"><bean:write name="detail" property="deliveryTime"/></p><input type="hidden" name="deliveryTime" value="${detail.deliveryTime}"/></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>