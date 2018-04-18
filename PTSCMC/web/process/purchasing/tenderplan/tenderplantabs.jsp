<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.tenderplanadd.title"/>/<bean:message key="message.detail.s"/></h3>
<div id="tenderPlanTabs" style="height: 1900px;">
  <div class="tenderPlanTabChild" id='tenderplandetail' title='<bean:message key="message.tenderplandetail.title"/>'></div>
  <div class="tenderPlanTabChild" id='tenderplanletter' title='<bean:message key="message.tenderletter"/>'></div>
  <%--<div class="tenderPlanTabChild" id='tenderplanbidclosing' title='<bean:message key="message.tenderplan.bidclosing"/>'></div>
  <div class="tenderPlanTabChild" id='tenderplanbidopening' title='<bean:message key="message.tenderplan.bidopening"/>'></div>
  <div class="tenderPlanTabChild" id='tenderplantecheval' title='<bean:message key="message.techeval"/>'></div>
  <div class="tenderPlanTabChild" id='tenderplancomeval' title='<bean:message key="message.comeval"/>'></div>
  <div class="tenderPlanTabChild" id='tenderplancomevalmaterial' title='<bean:message key="message.comevalmaterial"/>'></div>
  --%>
  <div class="tenderPlanTabChild" id='tenderplanbidevalsum' title='<bean:message key="message.bidevalsum"/>'></div>
  <%--
  <div class="tenderPlanTabChild" id='tenderplanpricecomparison' title='<bean:message key="message.pricecomparison"/>'></div>
  --%>
</div>
<input id="tenIdHidden" type="hidden"/>