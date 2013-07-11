<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<h1><spring:message code="register.legend"/></h1>              

<c:url value="/register" var="register_url" />
<form:form acceptCharset="UTF-8" cssClass="new_user" id="new_user" commandName="user" action="${register_url}">

<fieldset class="register">
  <div class="field">
    <label for="user_name"><spring:message code="register.user.name"/></label>
    <form:input path="name" size="30" value="${ user.name }"/><form:errors path="name" />
  </div>
  <div class="field">
    <label for="user_email"><spring:message code="register.user.email"/></label>
    <form:input path="email" size="30" type="email" value="${ user.email }"/><form:errors path="email" />
  </div>
  <div class="field">
    <label for="user_password"><spring:message code="register.user.password"/></label>
    <form:password path="password" size="30" /><form:errors path="password" />
  </div>
  <div class="field">
    <label for="user_password_confirmation"><spring:message code="register.user.password_again"/></label>
    <form:password path="passwordConfirmation" size="30" /><form:errors path="" />
  </div>
  <div class="actions">
    <spring:message code="register.button" var="register_button"/>
    <input id="register-btn" name="commit" type="submit" value="${register_button}" />
  </div>
  </fieldset>
</form:form>

<a href="<c:url value='/'/>">Back</a>