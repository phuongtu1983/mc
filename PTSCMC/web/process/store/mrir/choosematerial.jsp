<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedMaterial_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'materialMrirRequestSelectedDiv');">&nbsp;<bean:message key='message.request.material.selected'/></font></legend>
                    <div id='materialMrirRequestSelectedDiv'>
                        <input type="button" onclick="return delMrirMaterialRequest();" value="<bean:message key="message.del"/>"/>&nbsp;<input type="button" onclick="return chooseMrirMaterialSelected();" value="<bean:message key="message.ok"/>"/>
                        <form name="materialMrirRequestSelectedForm">
                            <table id="materialRequestSelectedTbl" class="its" style="width:100%">
                                <thead>
                                    <tr>
                                        <th><bean:message key="message.choose"/></th>
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
                <form name="selectMrirMaterialForm">
                    <select name="searchid">
                        <option value='0'><bean:message key='message.all'/></option>
                        <option value='1'><bean:message key='message.material.nameVn'/></option>
                    </select>
                    <input type="textbox" name="searchvalue"/>
                    <input type="button" onclick="return searchMrirMaterialRequest();" value="<bean:message key="message.search"/>"/>&nbsp;<input type="button" onclick="return setMrirMaterialSelected();" value="<bean:message key="message.choose"/>"/>
                </form>                
        </td></tr>
    </table>
    <form name="materialMrirRequestForm">
        <div id='materialMrirRequestList'></div>
    </form>
    <input type="hidden" id="callbackFunc"/>
</div>
