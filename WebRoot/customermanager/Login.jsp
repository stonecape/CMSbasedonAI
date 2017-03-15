<%@ page contentType="text/html" pageEncoding="UTF-8" session = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<c:set var="pageTitle" scope="request">CMS based on AI</c:set>
<c:set var="mainBody" scope="request">customermanager/login.jsp</c:set>
<c:set var="sideBar" scope="request">customermanager/cusmanagersidebar.jsp</c:set>
<c:set var="menu" scope="request">customermanager/cusmanagermenu.jsp</c:set>

<jsp:include page="/WEB-INF/jspf/common/layout.jsp">
    <jsp:param name="subTitle" value="客户经理登录" />
</jsp:include>