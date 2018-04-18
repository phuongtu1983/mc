<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.contractdetail.title"/>/<bean:message key="message.detail.s"/></h3>
<div id="appendixTabs" style="height: 1900px;">
  <div class="appendixTabChild" id='contractdetail' title='<bean:message key="message.contract"/>'></div>
  <div class="appendixTabChild" id='appendixlist' title='<bean:message key="message.appendixdetail.title"/>'></div>
  <div class="appendixTabChild" id='adjustmentlist' title='<bean:message key="message.adjustmentdetail.title"/>'></div>
</div>
<input id="conIdHidden" type="hidden"/>