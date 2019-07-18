<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui"%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<portlet:actionURL name="orderFileController" var="orderFileControllerURL" />

<aui:form action="<%= orderFileControllerURL.toString() %>" method="post" enctype="multipart/form-data">
		<input name="csvfile" type="file" inlineLabel="left"  label="Order CSV File :" id="required1" style="padding:10px;"/>
				 
		<aui:button id ="submitButton" type="submit" value="Submit" style="width:100%; margin-top:20px"  />

</aui:form>			