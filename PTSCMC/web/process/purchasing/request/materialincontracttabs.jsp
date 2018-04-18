<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.materialincontractlist.title"/>/<bean:message key="message.detail.s"/></h3>
<div id="materialInContractTabs" style="height: 1000px;width: 750px">
  <div class="materialInContractTabChild" id='incontractprinciplepanel' title='<bean:message key="message.material.incontractprinciple_t"/>'></div>
  <!--<div class="materialInContractTabChild" id='incontractprincipleexpirepanel' title='<bean:message key="message.material.incontractprincipleexpire_t"/>'></div>-->
  <div class="materialInContractTabChild" id='inadjustmentcontractpanel' title='<bean:message key="message.material.inadjustmentcontract_t"/>'></div>
  <div class="materialInContractTabChild" id='incontractpanel' title='<bean:message key="message.material.incontract_t"/>'></div>
  <div class="materialInContractTabChild" id='inorderpanel' title='<bean:message key="message.material.order_t"/>'></div>
  <div class="materialInContractTabChild" id='notincontractpanel' title='<bean:message key="message.material.notincontrac_t"/>'></div>
</div>
<input id="venIdHidden" type="hidden"/>