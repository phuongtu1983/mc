<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="materialTable" style="width:100%">
    <logic:iterate id="material" name="<%=Constants.MATERIAL_LIST%>">
        <tr>
            <td width="30px">
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="reqMatId" value="0"/>
                <html:hidden name="material" property="matId"/>
            </td>            
            <td>
                <span><bean:write name="material" property="matName"/></span>
                <html:hidden name="material" property="matName"/>
            </td>
            <td>
                <span><bean:write name="material" property="matCode"/></span>
                <html:hidden name="material" property="matCode"/>
            </td>
            <td width="80px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="material" property="qt"  onblur="return tryNumberFormat(this)" /></td>
                        
        </tr>
    </logic:iterate>
</table>