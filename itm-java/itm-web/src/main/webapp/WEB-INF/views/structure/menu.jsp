<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<ul>
	<li class="current_page_item"><a href="<c:url value='/'/>"><spring:message code="menu.homepage" /></a></li>
	<li><a href="http://itmenedzsment.blogspot.com/" target="_blank"><spring:message code="menu.blog" /></a></li>
	<li><a href="#"><spring:message code="menu.location" /></a></li>
	<li><a href="#"><spring:message code="menu.contact" /></a></li>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<li class="admin"><a href="<c:url value='/admin'/>">Admin</a></li>
	</sec:authorize>
</ul>