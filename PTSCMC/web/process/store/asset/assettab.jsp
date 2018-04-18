<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<div id="assetTabs" style="height: 1900px;">
  <div class="tabChild" id='tabChild1' title='<bean:message key="message.assettab.tab1"/>'></div>
  <div class="tabChild" id='tabChild2' title='<bean:message key="message.assettab.tab2"/>'></div>
  <div class="tabChild" id='tabChild3' title='<bean:message key="message.assettab.tab3"/>'></div>
  <div class="tabChild" id='tabChild4' title='<bean:message key="message.assettab.tab4"/>'></div>
</div>
<input id="assIdHidden" type="hidden"/>
<input id="assetNameHidden" type="hidden"/>
<input id="usedCodeHidden" type="hidden"/>
<input id="colorCodeHidden" type="hidden"/>