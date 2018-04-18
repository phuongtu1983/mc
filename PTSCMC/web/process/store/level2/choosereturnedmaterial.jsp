<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedMaterial_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'materialRmsSelectedDiv');">&nbsp;<bean:message key='message.request.material.selected'/></font></legend>                    
                    <div id='materialRmsSelectedDiv'>
                        <input type="button" onclick="return delMaterialRms();" value="<bean:message key="message.del"/>"/>&nbsp;<input type="button" onclick="return chooseMaterialRmsSelected();" value="<bean:message key="message.ok"/>"/>
                        <form name="materialRmsSelectedForm">
                            <table id="materialRmsSelectedTbl" class="its" style="width:100%">
                                <thead>
                                    <tr>
                                        <th><bean:message key="message.choose"/></th>
                                        <th><bean:message key="message.material.code"/></th>
                                        <th><bean:message key="message.material.nameVn"/></th>
                                        <th><bean:message key="message.umsdetail.currentQuantity"/></th>
                                        <th><bean:message key="message.umsdetail.request"/></th>
                                        <th><bean:message key="message.ums.title"/></th>
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
                    <table>
                        <tr>
                            <td>
                                <select name="searchid" style="width: 120px">
                                    <option value='0'><bean:message key='message.all'/></option>
                                    <option value='1'><bean:message key='message.material.code'/></option>
                                    <option value='2'><bean:message key='message.material.nameVn'/></option>
                                </select>
                            </td>
                            <td>
                                <input type="textbox"  size="40" name="searchvalue"/>
                                <input type="button" onclick="return searchMaterialRms();" value="<bean:message key="message.search"/>"/>&nbsp;<input type="button" onclick="return setMaterialRmsSelected();" value="<bean:message key="message.choose"/>"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <select name="extraSearchId">
                                    <option value='3'><bean:message key='message.ums.title'/></option>
                                </select>
                            </td>
                            <td>
                                <input type="textbox"  size="40" name="extraSearchValue"/>
                            </td>
                        <tr style="display: none">
                            <td width="80px"><bean:message key="message.materialreturned.date"/></td>
                            <td>
                                <input name="searchUpdateDateRms" size="10" onclick="javascript: showCalendar(this)" id="searchUpdateDateRms" type="text"/>
                            </td>
                        </tr>
                    </table>
                </form>                
            </td></tr>
    </table>
    <form name="materialRmsForm"><div id='materialRmsList'></div></form>
</div>