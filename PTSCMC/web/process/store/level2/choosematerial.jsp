<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedMaterial_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'materialUmsSelectedDiv');">&nbsp;<bean:message key='message.request.material.selected'/></font></legend>                    
                    <div id='materialUmsSelectedDiv'>
                        <input type="button" onclick="return delMaterialUms();" value="<bean:message key="message.del"/>"/>&nbsp;<input type="button" onclick="return chooseMaterialUmsSelected();" value="<bean:message key="message.ok"/>"/>
                        <form name="materialUmsSelectedForm">
                            <table id="materialUmsSelectedTbl" class="its" style="width:100%">
                                <thead>
                                    <tr>
                                        <th><bean:message key="message.choose"/></th>
                                        <th><bean:message key="message.material.code"/></th>
                                        <th><bean:message key="message.material.nameVn"/></th>
                                        <th><bean:message key="message.umsdetail.currentQuantity"/></th>
                                        <th><bean:message key="message.umsdetail.request"/></th>
                                        <th><bean:message key="message.mivNumber"/></th>
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
                    <div>
                        <select name="searchid" style="width: 120px">
                            <option value='0'><bean:message key='message.all'/></option>
                            <option value='1'><bean:message key='message.material.code'/></option>
                            <option value='2'><bean:message key='message.material.nameVn'/></option>
                        </select>
                        <input type="textbox"  size="40" name="searchvalue"/>
                        <input type="button" onclick="return searchMaterialUms();" value="<bean:message key="message.search"/>"/>&nbsp;<input type="button" onclick="return setMaterialUmsSelected();" value="<bean:message key="message.choose"/>"/>
                    </div>
                    <div>
                        <select name="extraSearchId">
                            <option value='3'><bean:message key='message.rfm.requestNumber'/></option>
                        </select>
                        <input type="textbox"  size="40" name="extraSearchValue"/>
                    </div>
                </form>                
            </td></tr>
    </table>
    <form name="materialUmsForm"><div id='materialUmsList'></div></form>
</div>