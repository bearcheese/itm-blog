<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="login-form">
<c:url value="/j_spring_security_check" var="login_url" />
<form:form acceptCharset="UTF-8" cssClass="login_user" id="login_user"
	commandName="user" action="${login_url}">
	<fieldset class="register">
	   <legend><spring:message code="login.legend"/></legend>
		<div class="field">
			<label for="user_name"><spring:message code="login.user.name" /></label>
			<form:input path="name" size="30" value="${ user.name }" />
			<form:errors path="name" />
		</div>
		<div class="field">
			<label for="user_password">
			 <spring:message code="login.user.password" />
			</label>
			<form:password path="password" size="30" />
			<form:errors path="password" />
		</div>

		<div class="actions">
			<spring:message code="login.button" var="login_button" />
			<input id="login-btn" name="commit" type="submit" value="${login_button}" />
			<spring:message code="login.or" />			
			<a href="/register"><spring:message code="login.register"/></a>!
		</div>
	</fieldset>

</form:form>
</div>