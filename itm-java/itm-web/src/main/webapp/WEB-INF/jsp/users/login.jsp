<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form acceptCharset="UTF-8" cssClass="login_user" id="login_user" commandName="user" action="/itm-web/j_spring_security_check">
<fieldset class="register">
  <div class="field">
    <label for="user_name">Név</label>
    <form:input path="name" size="30" value="${ user.name }"/><form:errors path="name" />
  </div>
  <div class="field">
    <label for="user_password">Jelszó</label>
    <form:password path="password" size="30" /><form:errors path="password" />
  </div>

  <div class="actions">
    <input id="login-btn" name="commit" type="submit" value="Bejelentkezés" />
  </div>
  </fieldset>

</form:form>