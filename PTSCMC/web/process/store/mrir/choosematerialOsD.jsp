<%@page import="com.venus.core.util.NumberUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
            int detId = (int) NumberUtil.parseInt(request.getAttribute("detId").toString(), 0);
            int osdId = (int) NumberUtil.parseInt(request.getAttribute("osdId").toString(), 0);
            String matName = (String) request.getAttribute("matName").toString();
%>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <%--<fieldset>--%>
                    <%--<legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedMaterial_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'materialOsDSelectedDiv');">&nbsp;<bean:message key='message.request.material.selected'/></font></legend>--%>
                    <div id='materialOsDSelectedDiv'>
                        <%--   
                            <input type="button" onclick="return delMaterialOsD();" value="<bean:message key="message.del"/>"/>&nbsp;<input type="button" onclick="return chooseMaterialOsDSelected();" value="<bean:message key="message.ok"/>"/>
                        --%>
                        <form name="materialOsDSelectedForm">
                            <table id="materialOsDSelectedTbl" class="its" style="width:100%">
                           <%--     <thead>
                                    <tr>
                                        <th><bean:message key="message.choose"/></th>
                                        <th><bean:message key="message.material.code"/></th>
                                        <th><bean:message key="message.material.nameVn"/></th>
                                    </tr>
                                </thead>
                           --%>
                                <tbody></tbody>
                            </table>
                        </form>
                    </div>
                <%--</fieldset>--%>
        </td></tr>
        <tr><td>           
                <form name="selectMaterialOsDForm">
                    <select name="searchid">
                        <option value='0'><bean:message key='message.all'/></option>
                        <option value='1'><bean:message key='message.material.code'/></option>
                        <option value='2'><bean:message key='message.material.nameVn'/></option>
                    </select>
                     <input type="hidden" name="matName" value ="<%=matName%>"/>
                    <input type="textbox" size="70" name="searchvalue"/>
                    <input type="button" onclick="return searchMaterialOsD();" value="<bean:message key="message.search"/>"/>&nbsp;<input type="button" onclick="return setMaterialOsDSelected();" value="<bean:message key="message.choose"/>"/>
                </form>                
        </td></tr>
    </table>
    <form name="materialOsDForm">
        <div id='materialOsDList'></div>
        <input type="hidden" name="detId" value ="<%=detId%>"/>
        <input type="hidden" name="osdId" value ="<%=osdId%>"/>
    </form>
    <input type="hidden" id="callbackFunc"/>
</div>