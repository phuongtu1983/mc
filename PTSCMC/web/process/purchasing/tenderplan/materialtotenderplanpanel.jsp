<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><p style='margin-top:0; margin-bottom:0' align='left'><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedMaterial_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'materialTenderPlanSelectedDiv');">&nbsp;<bean:message key='message.request.material.selected'/></font></p></legend>
                    <div id='materialTenderPlanSelectedDiv'>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_TENDERPLAN)) {%>
                        <input type="button" onclick="return delMaterialTenderPlan();" value="<bean:message key="message.del"/>"/>&nbsp;
                        <input type="button" onclick="return chooseMaterialTenderPlanSelected();" value="<bean:message key="message.ok"/>"/>
                        <%}%>
                        <form name="materialTenderPlanSelectedForm">
                            <table id="materialTenderPlanSelectedTbl" class="its">
                                <thead>
                                    <tr>
                                        <th><bean:message key="message.choose"/></th>
                                        <th><bean:message key="message.material.code"/></th>
                                        <th><bean:message key="message.material.nameVn"/></th>
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
                    <form name="selectMaterialTenderPlanForm">
                        <select name="searchid">
                            <option value='0'><bean:message key='message.all'/></option>
                            <option value='1'><bean:message key='message.material.code'/></option>
                            <option value='2'><bean:message key='message.material.nameVn'/></option>
                            <option value='3'><bean:message key='message.request'/></option>
                            <option value='4'><bean:message key='message.request.whichUse'/></option>
                        </select>
                        <input type="textbox"  size="40" name="searchvalue"/>
                        <input type="button" onclick="return searchMaterialTenderPlan();" value="<bean:message key="message.search"/>"/>&nbsp;<input type="button" onclick="return setMaterialTenderPlanSelected();" value="<bean:message key="message.choose"/>"/>
                    </form>
                </div>
        </td></tr>
        <tr><td><form name='tenderplanmaterialForm' id='tenderplanmaterialForm'><div id='tenderPlanMaterialList'></div></form></td></tr>
    </table>
    <input type="hidden" id="callbackFunc"/>
    <input type="hidden" id="materialSource"/>
</div>