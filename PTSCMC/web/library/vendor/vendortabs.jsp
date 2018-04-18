<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.vendoradd.title"/>/<bean:message key="message.detail.s"/></h3>
<div id="vendorTabs" style="height: 700px;">
  <div class="vendorTabChild" id='vendoredetail' title='<bean:message key="message.vendordetail.title"/>'></div>
  <div class="vendorTabChild" id='vendoregroupmaterial' title='<bean:message key="message.vendor.groupmaterial.title"/>'></div>
  <div class="vendorTabChild" id='vendormaterial' title='<bean:message key="message.vendor.material.title"/>'></div>
  <div class="vendorTabChild" id='vendorcontact' title='<bean:message key="message.vendor.contact.title"/>'></div>
  <div class="vendorTabChild" id='vendorevaluate' title='<bean:message key="message.vendor.evaluate.title"/>'></div>
</div>
<input id="venIdHidden" type="hidden"/>