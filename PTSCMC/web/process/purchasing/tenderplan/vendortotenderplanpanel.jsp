<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><p style='margin-top:0; margin-bottom:0' align='right'><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedVendor_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'vendorTenderPlanSelectedDiv');">&nbsp;<bean:message key='message.request.material.selected'/></font></p></legend>
                    <div id='vendorTenderPlanSelectedDiv'>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_TENDERPLAN)) {%>
                        <input type="button" onclick="return delVendorTenderPlan();" value='<bean:message key="message.del"/>'/>&nbsp;
                        <input type="button" onclick="return chooseVendorTenderPlanSelected();" value="<bean:message key="message.ok"/>"/>
                        <%}%>
                        <form name="vendorTenderPlanSelectedForm">
                            <table id="vendorTenderPlanSelectedTbl" class="its">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th><bean:message key="message.vendor.l_name"/></th>
                                        <th><bean:message key="message.vendor.charterCapital"/></th>
                                        <th><bean:message key="message.vendor.presenter"/></th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </form>
                    </div>
                </fieldset>
            </td></tr>
        <tr><td>
                <div>
                    <form name="selectVendorTenderPlanForm">
                        <table width="100%">
                            <tr>
                                <td width="100px"><bean:message key="message.u_vendor"/></td>
                                <td><input type="textbox" class="lbl10" size="20" name="vendorName"/></td>
                            </tr>
                            <tr id="vendorToTenderPlanGroupIdTr">
                                <td><bean:message key="message.vendor.material.group"/></td>
                                <td>
                                    <select style="width: 300px;" name="materialGroup" id="vendorToTenderPlanGroupCmd">
                                        <logic:iterate id="group" name="<%=Constants.VENDOR_GROUP_MATERIAL_LIST%>">
                                            <option value="${group.groId}">${group.name}</option>
                                        </logic:iterate>
                                    </select>
                                    <input type="button" onclick="return comboTextIdClick('selectVendorTenderPlanForm','materialGroupName','materialGroup','vendorToTenderPlanGroupIdTr','vendorToTenderPlanGroupNameTr');" value='<>'/>
                                </td>
                            </tr>
                            <tr style="display:none" id="vendorToTenderPlanGroupNameTr">
                                <td><bean:message key="message.vendor.material.group"/></td>
                                <td colspan="3">
                                    <input type="textbox" size="55" name="materialGroupName"/>
                                    <input type="button" onclick="return comboTextNameClick('selectVendorTenderPlanForm','getMaterialGroupToCombobox.do',null,'materialGroupName','materialGroup','vendorToTenderPlanGroupCmd','vendorToTenderPlanGroupIdTr','vendorToTenderPlanGroupNameTr');" value='<>'/>
                                </td>
                            </tr>
                            <!--<tr>
                                <td>
                            <bean:message key="message.contract.effectedDate"/> <bean:message key="message.from"/>
                            <input type="textbox" size="21" name="fromDateContract" id="fromDateContract" onclick="javascript:showCalendar('fromDateContract')"/>
                            <bean:message key="message.to"/>
                            <input type="textbox" size="20" name="toDateContract" id="toDateContract" onclick="javascript:showCalendar('toDateContract')"/>
                        </td>
                    </tr>-->
                            <tr>
                                <td colspan="2">
                                    <input type="button" onclick="return searchVendorTenderPlan();" value="<bean:message key="message.search"/>"/>&nbsp;
                                    <input type="button" onclick="return setVendorTenderPlanSelected();" value="<bean:message key="message.choose"/>"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </td></tr>
        <tr><td><form name='tenderplanvendorForm' id='tenderplanvendorForm'><div id='tenderplan_vendorList'></div></form></td></tr>
    </table>
    <input type="hidden" id="callbackFunc"/>
    <input type="hidden" id="vendorSource"/>
    <input type="hidden" id="parentFormVendor"/>
</div>