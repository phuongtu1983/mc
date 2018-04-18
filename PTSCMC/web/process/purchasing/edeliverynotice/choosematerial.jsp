<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedMaterial_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'materialEdnRequestSelectedDiv');">&nbsp;<bean:message key='message.request.material.selected'/></font></legend>
                    <div id='materialEdnRequestSelectedDiv'>
                        <input type="button" onclick="return delEdnMaterialRequest();" value="<bean:message key="message.del"/>"/>&nbsp;<input type="button" onclick="return chooseEdnMaterialSelected();" value="<bean:message key="message.ok"/>"/>
                        <form name="materialEdnRequestSelectedForm">
                            <table id="materialRequestSelectedTbl" class="its" style="width:100%">
                                <thead>
                                    <tr>
                                        <th><bean:message key="message.choose"/></th>
                                        <th><bean:message key="message.material.nameVn"/></th>
                                        <th><bean:message key="message.dn.unit"/></th>
                                        <th><bean:message key="message.dn.quantity"/></th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </form>
                    </div>
                </fieldset>
        </td></tr>
        <tr><td>           
                <form name="selectEdnMaterialForm">
                    <select name="searchid">
                        <option value='0'><bean:message key='message.all'/></option>
                        <option value='1'><bean:message key='message.material.nameVn'/></option>
                        <option value='2'><bean:message key='message.dn.unit'/></option>
                    </select>
                    <input type="textbox" name="searchvalue"/>
                    <input type="button" onclick="return searchEdnMaterialRequest();" value="<bean:message key="message.search"/>"/>&nbsp;<input type="button" onclick="return setEdnMaterialSelected();" value="<bean:message key="message.choose"/>"/>
                </form>                
        </td></tr>
    </table>
    <form name="materialEdnRequestForm">
        <div id='materialEdnRequestList'></div>
    </form>
    <input type="hidden" id="callbackFunc"/>
</div>