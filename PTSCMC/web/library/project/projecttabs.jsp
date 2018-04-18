<%-- 
    Document   : projecttabs
    Created on : Jul 22, 2009, 10:11:54 PM
    Author     : kngo
--%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<div id="projectTabs" style="height: 700px;">
    <div class="projectTabChild" id='projectdetail' title='<bean:message key="message.projectdetail.title"/>'></div>
    <div class="projectTabChild" id='projectstore' title='<bean:message key="message.project.store.title"/>'></div>
    <input id="proIdHidden" type="hidden">
</div>
