<%@page import="com.venus.core.util.NumberUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
            int matIda = (int) NumberUtil.parseInt(request.getAttribute("matId").toString(), 0);
            String detIds = request.getAttribute("detIds").toString();
            String detId = request.getAttribute("detId").toString();
            String matIds = request.getAttribute("matIds").toString();
            String reqIds = request.getAttribute("reqIds").toString();
            String kind = request.getAttribute("kind").toString();
%>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedMaterial_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'materialOrderContractSelectedDiv');">&nbsp;<bean:message key='message.request.material.selected'/></font></legend>
                    <div id='materialOrderContractSelectedDiv'>
                        <input type="button" onclick="return delMaterialOrderContract();" value="<bean:message key="message.del"/>"/>&nbsp;<input type="button" onclick="return chooseMaterialOrderContractSelected(4);" value="<bean:message key="message.ok"/>"/>
                        
                        <form name="materialOrderContractSelectedForm">
                            <table id="materialOrderContractSelectedTbl" class="its" style="width:100%">
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
                <form name="selectMaterialOrderContractForm">
                    <select name="searchid">
                        <option value='0'><bean:message key='message.all'/></option>
                        <option value='1'><bean:message key='message.material.code'/></option>
                        <option value='2'><bean:message key='message.material.nameVn'/></option>
                    </select>
                    <input type="textbox"  size="70" name="searchvalue"/>
                    <input type="button" onclick="return searchMaterialOrderContract();" value="<bean:message key="message.search"/>"/>&nbsp;<input type="button" onclick="return setMaterialOrderContractSelected(<%=kind%>);" value="<bean:message key="message.choose"/>"/>
                </form>                
        </td></tr>
    </table>
    <form name="materialOrderContractForm">
        <div id='materialOrderContractList'></div>
        <input type="hidden" name="detIds" value ="<%=detIds%>"/>
        <input type="hidden" name="detId" value ="<%=detId%>"/>
        <input type="hidden" name="matIda" value ="<%=matIda%>"/>
        <input type="hidden" name="matIds" value ="<%=matIds%>"/>
        <input type="hidden" name="reqIds" value ="<%=reqIds%>"/>
        <input type="hidden" name="kind" value ="<%=kind%>"/>
    </form>
    <input type="hidden" id="callbackFunc"/>
</div>