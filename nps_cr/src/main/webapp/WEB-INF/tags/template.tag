<%@tag language="java" pageEncoding="ISO-8859-15"%>
<%@ include file="/common/taglibs.jsp"%>
<%@attribute name="pageTitle"%>
<%@attribute name="scrollPanelTitle"%>
<%@attribute name="headArea" fragment="true" %>
<%@attribute name="bodyArea" fragment="true" %>

<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page ="/WEB-INF/templates/css.jspf"/>
        <jsp:include page ="/WEB-INF/templates/js.jspf"/>
        <jsp:include page="/WEB-INF/templates/msg.jspf" />
        <jsp:invoke fragment="headArea"/>
        <c:set var="context1" value="${pageContext.request.contextPath}" />
        <title>CONSULTAS | ${pageTitle}</title>
    </head>
    <body>
        <div id="overlay_loading" style="display:none;">
            <div class="fancytree-loading"><span class="fancytree-expander"></span></div>
        </div>
        <div id="notifynormal"></div>
        <jsp:include page ="/WEB-INF/templates/header.jspf"/>
        <jsp:include page ="/WEB-INF/templates/menu.jspf"/>

        <div title="${scrollPanelTitle}" class="scrollPanel">
            <jsp:invoke fragment="bodyArea"/>
        </div>
    </body>
</html>