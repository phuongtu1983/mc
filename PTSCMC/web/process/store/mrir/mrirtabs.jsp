<%-- 
    Document   : mrirtabs
    Created on : Sep 24, 2009, 8:00:16 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<div id="mrirTabs" style="height: 1900px;">
  <div class="mrirTabChild" id='tab1' title='<bean:message key="message.mrir.title"/>'></div>
  <div class="mrirTabChild" id='tab2' title='<bean:message key="message.osd.title"/>'></div>
</div>
<input id="mrirIdHidden" type="hidden"/>
