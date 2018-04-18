<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.RequestBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<div id="listMrirDetailDataDiv" style="width:100%;height:400px;overflow:auto">
    <display:table style="width:100%" pagesize="15" name="<%=Constants.REQUEST_LIST%>" id="req" class="its">
        <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_request'/></display:setProperty>
        <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_request'/></display:setProperty>
        <display:column property="requestNumber" title="Request No."/>
        <display:column property="requestDeliveryDate" title="Date receive Request"/>
        <display:column property="provideDate" title="ROS"/>
        <display:column property="projectName" title="Project"/>
        <display:column property="organization" title="Organization"/>
        <display:column property="description" title="Purpose"/>
        <display:column property="matName" title="Description"/>
        <display:column property="matUnit" title="Unit"/>
        <display:column property="quantity" title="Qty"/>
        <display:column property="price" title="Unit price"/>
        <display:column property="total" title="Total"/>
        <display:column property="vat" title="VAT %"/>
        <display:column property="sum" title="Total incl. VAT"/>
        <display:column property="invoice" title="Invoice No"/>
        <display:column property="invoiceDate" title="Invoice Date"/>
        <display:column property="status" title="Status"/>
        <display:column property="contractNumber" title="PO"/>
        <display:column property="contractDate" title="PO Date"/>
        <display:column property="vendor" title="Supplier"/>
        <display:column property="deliveryDate" title="Delivered date"/>
        <display:column property="moveCreateMrir" titleKey="message.contract.material.moveCreateMrir"/>
        <display:column property="mrirNumber" title="MRIR No."/>
        <display:column property="mrirDate" title="MRIR Date"/>
        <display:column property="receiveMrir" titleKey="message.contract.material.receiverMrir"/>
        <display:column property="moveCreateMsv" titleKey="message.contract.material.moveCreateMsv"/>
        <display:column property="msvNumber" title="Instock No"/>
        <display:column property="msvDate" title="Instock Date"/>
        <display:column property="receiveMsv" titleKey="message.contract.material.receiveMsv"/>
        <display:column property="moveAccountingDate" title="To Account Deparment"/>
        <display:column property="paymentDate" title="Date payment"/>
        <display:column property="assetVendor" titleKey="message.requestreport.assetVendor"/>
        <display:column property="volume" title="Location store"/>
        <display:column property="responseEmp" titleKey="message.requestreport.responseEmp"/>
    </display:table>
</div>