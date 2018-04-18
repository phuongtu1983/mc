<%-- 
    Document   : chooseequipment
    Created on : Nov 7, 2009, 8:44:19 PM
    Author     : kngo
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedEquipment_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'equipmentMcoSelectedDiv');">&nbsp;<bean:message key='message.equipment.selected'/></font></legend>
                    <div id='equipmentMcoSelectedDiv'>
                        <input type="button" onclick="return delMcoEquipment();" value="<bean:message key="message.del"/>"/>&nbsp;<input type="button" onclick="return chooseMcoEquipmentSelected();" value="<bean:message key="message.ok"/>"/>
                        <form name="equipmentMcoSelectedForm">
                            <table id="equipmentSelectedTbl" class="its" style="width:100%">
                                <thead>
                                    <tr>
                                        <th><bean:message key="message.choose"/></th>
                                        <th><bean:message key="message.equipment.usedCode"/></th>
                                        <th><bean:message key="message.equipment.nameVn"/></th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </form>
                    </div>
                </fieldset>
        </td></tr>
        <tr><td>           
                <form name="selectMcoEquipmentForm">
                    <select name="searchid">
                        <option value='0'><bean:message key='message.all'/></option>
                        <option value='1'><bean:message key='message.equipment.nameVn'/></option>
                    </select>
                    <input type="textbox" name="searchvalue"/>
                    <input type="button" onclick="return searchMcoEquipmentRequest();" value="<bean:message key="message.search"/>"/>&nbsp;<input type="button" onclick="return setMcoEquipmentSelected();" value="<bean:message key="message.choose"/>"/>
                </form>                
        </td></tr>
    </table>
    <form name="equipmentMcoForm">
        <div id='equipmentMcoList'></div>
    </form>
    <input type="hidden" id="callbackFunc"/>
</div>