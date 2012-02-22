<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:directive.include file="/WEB-INF/jsp/header.jspf" />

<h1>Editing user</h1>

<jsp:directive.include file="_form.jspf" />

<a href="/users">Back</a>

<jsp:directive.include file="/WEB-INF/jsp/footer.jspf" />