<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.VendorEvalDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<display:table style="width:100%" pagesize="15" requestURIcontext="false" name="<%=Constants.VENDOR_CRI_LIST%>" id="criterion" class="its" >
    <display:setProperty name="css.tr.odd" value ="" />
    <display:setProperty name="css.tr.even" value ="" /> 
    <display:column titleKey="message.rowNum" ><%=pageContext.getAttribute("criterion_rowNum")%></display:column>
    <display:column titleKey="message.vendor.criterion.name">
        <%=((VendorEvalDetailBean)pageContext.getAttribute("criterion")).getCriName()%>
        <input type="hidden" name="detIds" value="<%=((VendorEvalDetailBean)pageContext.getAttribute("criterion")).getDetid()%>">
    </display:column>
    <display:column titleKey="message.vendor.evaluate.result">
        <select class="lbl10" name="detResults">
            <logic:iterate id="result" name="<%=Constants.RESULT_LIST%>">
                <logic:equal name="criterion" property="evalResult" value="${result.value}"><option class="lbl10" selected value="<bean:write name="result" property="value"/>"><bean:write name="result" property="label"/></option></logic:equal>
                <logic:notEqual name="criterion" property="evalResult" value="${result.value}"><option class="lbl10" value="<bean:write name="result" property="value"/>"><bean:write name="result" property="label"/></option></logic:notEqual>
            </logic:iterate>
        </select>
    </display:column>
    <display:column titleKey="message.note">
        <input type="textbox"  name="detNotes" size="30" value="<%=((VendorEvalDetailBean)pageContext.getAttribute("criterion")).getNote()%>">
    </display:column>
</display:table>