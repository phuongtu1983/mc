<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedMaterial_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'materialRequestSelectedDiv');">&nbsp;<bean:message key='message.request.material.selected'/></font></legend>                    
                    <div id='materialRequestSelectedDiv'>
                        <input type="button" onclick="return delEquipmentRequest();" value="<bean:message key="message.del"/>"/>&nbsp;<input type="button" onclick="return chooseEquipmentSelected();" value="<bean:message key="message.ok"/>"/>
                        <form name="equipmentRequestSelectedForm">
                            <table id="materialRequestSelectedTbl" class="its" style="width:100%">
                                <thead>
                                    <tr>
                                        <th><bean:message key="message.choose"/></th>
                                        <th><bean:message key="message.requirerepair.usedCode"/></th>
                                        <th><bean:message key="message.requirerepair.equId"/></th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </form>
                    </div>
                </fieldset>
        </td></tr>
        <tr><td>           
                <form name="selectMaterialForm">
                    <select name="searchid">
                        <option value='0'><bean:message key='message.all'/></option>
                        <option value='1'><bean:message key='message.requirerepair.usedCode'/></option>
                        <option value='2'><bean:message key='message.requirerepair.equId'/></option>
                    </select>
                    <input type="textbox" name="searchvalue"/>
                    <input type="button" onclick="return searchEquipmentRequireRepair();" value="<bean:message key="message.search"/>"/>&nbsp;<input type="button" onclick="return setEquipmentSelected();" value="<bean:message key="message.choose"/>"/>
                </form>                
        </td></tr>
    </table>
    <form name="materialRequestForm">
        <div id='materialRequestList'></div>
    </form>
    <input type="hidden" id="callbackFunc"/>
</div>