<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.bean.MivBean"%>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<table width="220" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td id="navMenuContainer" background="images/im101.gif">
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
            <div style="margin-left:1px;" ><img src="img/mnu300.gif"></div>
            <div style="width:217px;margin-left:1px;margin-top:0;" dojoType="dijit.Menu" id="navMenu">
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem1">
                    <span><bean:message key="message.menu20.text"/><%=MCUtil.getMenuSpaces(33, request)%></span>
                    <div dojoType="dijit.Menu" id="navMenuSub1">
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item1" iconClass="mcMenuIconList"
                             onClick="loadVendorList();"><bean:message key="message.menu.list"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item2" iconClass="mcMenuIconNew"
                             onClick="vendorForm();"><bean:message key="message.menu.add"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_VENDOR_EVAL)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item3"
                             onClick="loadCriterionList();"><bean:message key="message.menu200.text"/></div>
                        <%}%>
                    </div>
                </div>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY)) {%>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem2">
                    <span><bean:message key="message.menu21.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub2">
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub2_item1" iconClass="mcMenuIconList"
                             onClick="loadMaterialList();"><bean:message key="message.menu2111.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_OUT)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub2_item2" iconClass="mcMenuIconList"
                             onClick="loadEmaterialList();"><bean:message key="message.menu2112.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_CATALOG)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub2_item3"
                             onClick="addSpe();"><bean:message key="message.menu210.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_UNIT)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub2_item4"
                             onClick="loadUnitList();"><bean:message key="message.menu211.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_ORIGIN)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub2_item5"
                             onClick="loadOriginList();"><bean:message key="message.menu212.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub2_item6"
                             onClick="loadMaterialPriceList();"><bean:message key="message.menu213.text"/></div>
                        <%}%>
                    </div>
                </div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_STORE)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadStoreList();"><bean:message key="message.menu22.text"/></div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_PROJECT)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadWebList();"><bean:message key="message.menu24.text"/></div>

                <div dojoType="dijit.MenuItem"
                     onClick="loadProjectList();"><bean:message key="message.menu23.text"/></div>
                <%}%>
                <!--<div dojoType="dijit.MenuSeparator"></div>-->
            </div>
            <%}%>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST)) {%>
            <div style="margin-left:1px;" ><img src="img/mnu301.gif"></div>
            <div style="width:217px;margin-left:1px;margin-top:0;" dojoType="dijit.Menu" id="navMenu1">
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem3">
                    <span><bean:message key="message.menu31.text"/><%=MCUtil.getMenuSpaces(30, request)%></span>
                    <div dojoType="dijit.Menu" id="navMenuSub3">
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub3_item1" iconClass="mcMenuIconList"
                             onClick="loadRequestList();"><bean:message key="message.menu.list"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub3_item2" iconClass="mcMenuIconNew"
                             onClick="requestForm();"><bean:message key="message.menu.add"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub3_item3"
                             onClick="loadMaterialListInContract();"><bean:message key="message.menu310.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_TRACING)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub3_item4"
                             onClick="loadRequestReportForm();"><bean:message key="message.menu311.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_GATHER)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub3_item5"
                             onClick="loadRequestTimeForm();"><bean:message key="message.menu312.text"/></div>
                        <%}%>
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem4">
                    <span><bean:message key="message.menu32.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub4">
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_INTERMEMO)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub4_item1" iconClass="mcMenuIconList"
                             onClick="loadIntermemoList();"><bean:message key="message.menu.list"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_INTERMEMO)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub4_item2" iconClass="mcMenuIconNew"
                             onClick="intermemoForm();"><bean:message key="message.menu.add"/></div>
                        <%}%>
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem5">
                    <span><bean:message key="message.menu33.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub5">
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_TENDERPLAN)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub5_item1" iconClass="mcMenuIconList"
                             onClick="loadTenderPlanList();"><bean:message key="message.menu.list"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_TENDERPLAN)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub5_item2" iconClass="mcMenuIconNew"
                             onClick="tenderPlanForm();"><bean:message key="message.menu.add"/></div>
                        <%}%>
                    </div>
                </div>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_CONTRACT)) {%>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem6">
                    <span><bean:message key="message.menu34.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub6">
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_CONTRACT)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub6_item1" iconClass="mcMenuIconList"
                             onClick="loadContractList();"><bean:message key="message.menu.list"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_PRINCIPLE)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub6_item2"
                             onClick="loadContractPrincipleList();"><bean:message key="message.menu340.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_CONTRACT_FOLLOW)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub6_item3"
                             onClick="loadContractFollowList();"><bean:message key="message.menu341.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_CONTRACT_EXECUTE)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub6_item4"
                             onClick="printReportContractForm(<%=ContractBean.KIND_CONTRACT%>);"><bean:message key="message.menu342.text"/></div>
                        <%}%>
                    </div>
                </div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_ORDER)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadOrderList();"><bean:message key="message.menu35.text"/></div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_DELIVERYREQUEST)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadDeliveryRequestList();"><bean:message key="message.menu36.text"/></div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadDnList();"><bean:message key="message.menu38.text"/></div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_INVOICE)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadInvoiceList();"><bean:message key="message.menu371.text"/></div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_PAYMENT)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadPaymentList();"><bean:message key="message.menu37.text"/></div>
                <%}%>
                <!--<div dojoType="dijit.MenuSeparator"></div>-->
            </div>
            <%}%>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_STOCK_MRIR)) {%>
            <div style="margin-left:1px;" ><img src="img/mnu302.gif"></div>
            <div style="width:217px;margin-left:1px;margin-top:0;" dojoType="dijit.Menu" id="navMenu2">
            <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem21">
                <span><bean:message key="message.menu41.text"/><%=MCUtil.getMenuSpaces(31, request)%></span>
                <div dojoType="dijit.Menu" id="navMenuSub211">
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_MRIR)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub211_item1" iconClass="mcMenuIconList"
                         onClick="loadMrirList();"><bean:message key="message.menu.list"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_MRIR)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub211_item2" iconClass="mcMenuIconNew"
                         onClick="mrirForm();"><bean:message key="message.menu410.text"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_MRIR_PROJECT)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub211_item3" iconClass="mcMenuIconNew"
                         onClick="mrirForm(null,1);"><bean:message key="message.menu411.text"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_MRIR_STORE)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub211_item4" iconClass="mcMenuIconNew"
                         onClick="mrirForm(null,2);"><bean:message key="message.menu412.text"/></div>
                    <%}%>
                </div>
            </div>

            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_MSV)) {%>
            <div dojoType="dijit.MenuItem"
                 onClick="loadMsvTab();"><bean:message key="message.menu42.text"/></div>
            <%}%>
            <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem43">
                <span><bean:message key="message.menu43.text"/></span>
                <div dojoType="dijit.Menu" id="navMenuSub43">
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_RFM_MATERIAL)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub431_item1" iconClass="mcMenuIconList"
                         onClick="loadMaterialListInStore();"><bean:message key="message.menu430.text"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_RFM)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub431_item2" iconClass="mcMenuIconList"
                         onClick="loadRfmList();"><bean:message key="message.menu.list"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_RFM)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub431_item3" iconClass="mcMenuIconNew"
                         onClick="addRfm();"><bean:message key="message.menu.add"/></div>
                    <%}%>
                </div>
            </div>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_MIV)) {%>
            <div dojoType="dijit.MenuItem"
                 onClick="loadMivList(<%=MivBean.KIND_COMPANY%>);"><bean:message key="message.menu44.text"/></div>
            <%}%>
            <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem441">
                <span><bean:message key="message.menu441.text"/></span>
                <div dojoType="dijit.Menu" id="navMenuSub441">
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_REPORT_REQUEST)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub441_item1"
                         onClick="loadMCRequestReportForm();"><bean:message key="message.menu4411.text"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_REPORT_STORE)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub441_item2"
                         onClick="loadMCReportForm();"><bean:message key="message.menu4412.text"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_REPORT_STORE_PROJECT)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub441_item3"
                         onClick="loadMCProjectStoreReportForm();"><bean:message key="message.menu4413.text"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_REPORT_BALANCE)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub441_item4"
                         onClick="loadMCMaterialBalanceReportForm();"><bean:message key="message.menu4415.text"/></div>
                    <%}%>
                </div>
            </div>
            <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem44">
                <span><bean:message key="message.menu57.text"/></span>
                <div dojoType="dijit.Menu" id="navMenuSub44">
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_INVENTORY)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub44_item1" iconClass="mcMenuIconList"
                         onClick="loadInventoryList();"><bean:message key="message.menu.list"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_INVENTORY)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub44_item2" iconClass="mcMenuIconNew"
                         onClick="inventoryForm();"><bean:message key="message.menu.add"/></div>
                    <%}%>
                </div>
            </div>
            <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem45">
                <span><bean:message key="message.menu432.text"/></span>
                <div dojoType="dijit.Menu" id="navMenuSub45">
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE_PROJECT)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub1_item450" iconClass="mcMenuIconList"
                         onClick="loadEdnList();"><bean:message key="message.menu38.text"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_PROJECT_MRIR)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub1_item451"
                         onClick="loadEmrirList();"><bean:message key="message.menu41.text"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_PROJECT_MSV)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub1_item452"
                         onClick="loadEmsvTab();"><bean:message key="message.menu42.text"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_PROJECT_STATISTICS)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub1_item453"
                         onClick="loadMaterialListInStore1();"><bean:message key="message.menu453.text"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_PROJECT_RFM)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub1_item454"
                         onClick="loadRfmList1();"><bean:message key="message.menu43.text"/></div>
                    <%}%>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_PROJECT_MSV)) {%>
                    <div dojoType="dijit.MenuItem" id="navMenuSub1_item455"
                         onClick="loadMivList(<%=MivBean.KIND_PARTNER%>);"><bean:message key="message.menu44.text"/></div>
                    <%}%>
                </div>
            </div>
            <%}%>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_STOCK_STORE2)) {%>
            <%if (!PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_STOCK_STORE2)) {%>
            <div style="margin-left:1px;" ><img src="img/mnu302.gif"></div>
            <div style="width:217px;margin-left:1px;margin-top:0;" dojoType="dijit.Menu" id="navMenu2">
                <%}%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadStoreLevel2List();"><bean:message key="message.menu48.text"/></div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_STOCK_STORE2)) {%>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem480">
                    <span><bean:message key="message.menu480.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub480">
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_YCKT_STORE2)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub481_item1" iconClass="mcMenuIconList"
                             onClick="loadDn2List();"><bean:message key="message.menu481.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_YCKT_REDUNDANT)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub483_item1" iconClass="mcMenuIconList"
                             onClick="loadDn3List();"><bean:message key="message.menu483.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_YCKT_PROJECT)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub482_item2" iconClass="mcMenuIconList"
                             onClick="loadDn4List();"><bean:message key="message.menu482.text"/></div>
                        <%}%>
                    </div>
                </div>

                <!--<div dojoType="dijit.MenuSeparator"></div>-->
            </div>
            <%}%>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_EQUIPMENT)) {%>
            <div style="margin-left:1px;" ><img src="img/mnu304.gif"></div>
            <div style="width:217px;margin-left:1px;margin-top:0;" dojoType="dijit.Menu" id="navMenu4">
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_COLOR)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadColorCodeList();"><bean:message key="message.menu490.text"/></div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadEquipmentList();"><bean:message key="message.menu49.text"/></div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadEquipmentList2();"><bean:message key="message.menu50.text"/></div>
                <%}%>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem46">
                    <span><bean:message key="message.menu58.text"/><%=MCUtil.getMenuSpaces(31, request)%></span>
                    <div dojoType="dijit.Menu" id="navMenuSub461">
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_MC)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub461_item2" iconClass="mcMenuIconList"
                             onClick="loadMcoList();"><bean:message key="message.menu582.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_MCO)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub461_item1" iconClass="mcMenuIconList"
                             onClick="loadMcList();"><bean:message key="message.menu581.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_EMC)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub461_item3" iconClass="mcMenuIconList"
                             onClick="loadEmcList();"><bean:message key="message.menu583.text"/></div>
                        <%}%>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_EMCO)) {%>
                        <div dojoType="dijit.MenuItem" id="navMenuSub461_item4" iconClass="mcMenuIconList"
                             onClick="loadEmcoList();"><bean:message key="message.menu584.text"/></div>
                        <%}%>
                    </div>
                </div>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_FACTREPORT)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadReportDamageList();"><bean:message key="message.menu60.text"/></div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadRequireRepairList();"><bean:message key="message.menu70.text"/></div>
                <div dojoType="dijit.MenuItem"
                     onClick="loadTimeUsingList();"><bean:message key="message.menu74.text"/></div>
                <div dojoType="dijit.MenuItem"
                     onClick="loadRequireVerifiedList();"><bean:message key="message.menu71.text"/></div>
                <div dojoType="dijit.MenuItem"
                     onClick="loadRequireTransferList();"><bean:message key="message.menu72.text"/></div>
                <div dojoType="dijit.MenuItem"
                     onClick="loadHandedReportList();"><bean:message key="message.menu73.text"/></div>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_SURVEYREPORT)) {%>
                <div dojoType="dijit.MenuItem"
                     onClick="loadSurveyReportList();"><bean:message key="message.menu80.text"/></div>
                <div dojoType="dijit.MenuItem"
                     onClick="loadAcceptanceTestList();"><bean:message key="message.menu90.text"/></div>
                <div dojoType="dijit.MenuItem"
                     onClick="loadRequireSettlingList();"><bean:message key="message.menu100.text"/></div>
                <%}%>
            </div>
            <%}%>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_SYSTEM)) {%>
            <div style="margin-left:1px;" ><img src="img/mnu303.gif"></div>
            <div style="width:217px;margin-left:1px;margin-top:0;" dojoType="dijit.Menu" id="navMenu3">
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem11">
                    <span><bean:message key="message.menu51.text"/><%=MCUtil.getMenuSpaces(33, request)%></span>
                    <div dojoType="dijit.Menu" id="navMenuSub11">
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item11" iconClass="mcMenuIconList"
                             onClick="loadEmployeeList();"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item12" iconClass="mcMenuIconNew"
                             onClick="addEmployee();"><bean:message key="message.menu.add"/></div>
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem12">
                    <span><bean:message key="message.menu52.text"/><%=MCUtil.getMenuSpaces(33, request)%></span>
                    <div dojoType="dijit.Menu" id="navMenuSub21">
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item21" iconClass="mcMenuIconList"
                             onClick="loadOrganizationList();"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item22" iconClass="mcMenuIconNew"
                             onClick="addOrganization();"><bean:message key="message.menu.add"/></div>
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem13">
                    <span><bean:message key="message.menu53.text"/><%=MCUtil.getMenuSpaces(33, request)%></span>
                    <div dojoType="dijit.Menu" id="navMenuSub31">
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item31" iconClass="mcMenuIconList"
                             onClick="loadPositionList();"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item32" iconClass="mcMenuIconNew"
                             onClick="addPosition();"><bean:message key="message.menu.add"/></div>
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem10">
                    <span><bean:message key="message.menu54.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub10">
                        <div dojoType="dijit.MenuItem" id="navMenuSub10_item1" iconClass="mcMenuIconList"
                             onClick="loadPermissionList()"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub10_item2" iconClass="mcMenuIconNew"
                             onClick=""><bean:message key="message.menu.add"/></div>
                    </div>
                </div>
                <div dojoType="dijit.MenuItem"
                     onClick="loadSystemConfig();"><bean:message key="message.menu55.text"/></div>
                <div dojoType="dijit.MenuItem"
                     onClick=""><bean:message key="message.menu56.text"/></div>
            </div>
            <%}%>
        </td>
    </tr>
</table>
