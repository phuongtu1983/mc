<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>

<logic:present name="<%=Constants.MRIR_MATERIAL_LIST%>">    
    <select class="lbl10" name="cbxMaterialOfReq">
        <logic:iterate id="mat_iter" name="<%=Constants.MRIR_MATERIAL_LIST%>">
            <option class="lbl10" value="${mat_iter.value}">${mat_iter.label}</option>
        </logic:iterate>
    </select>
</logic:present>

            