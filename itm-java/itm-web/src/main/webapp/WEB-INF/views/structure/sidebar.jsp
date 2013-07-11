<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<ul>
	<li>
		<div id="search">
			<form accept-charset="UTF-8" action="/search/index" method="get">
				<div>
					<input id="search-text" name="search" type="text" /> <input
						id="search-submit" type="submit" value="Keres" />
				</div>
			</form>
		</div>
		<div style="clear: both;">&nbsp;</div>
	</li>
	<li>
		<p id="alert"></p>
		<div id="login-box">
			<sec:authorize access="isAnonymous()">
				<c:url value='/j_spring_security_check' var="login_url" />
				<form:form acceptCharset="UTF-8" id="login_user" commandName="user"
					action="${login_url}" method="POST">

					<fieldset>
						<legend>
							<spring:message code="sidebar.login.label" />
						</legend>

						<div>
							<label for="user_name"><spring:message
									code="sidebar.login.name" />:</label>
							<form:input id="user_name" path="name" size="30" />
						</div>

						<div>
							<label for="user_password"><spring:message
									code="sidebar.login.password" />:</label>
							<form:password id="user_password" path="password" size="30" />
						</div>

						<div class="actions">
							<spring:message code="sidebar.login.button" var="login_button" />
							<input id="login-btn" name="commit" type="submit"
								value="${login_button}" />
							<spring:message code="sidebar.login.or" />
							<a href="<c:url value='/register'/>"><spring:message
									code="sidebar.login.register" /></a>!
						</div>

					</fieldset>
				</form:form>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<jsp:directive.include file="/WEB-INF/fragments/userinfo.jspf" />
			</sec:authorize>
		</div>
	</li>
	<li id="calendar"></li>
	<li>
		<h2>Kategóriák</h2>
		<ul>
			<li>Category1</li>
			<li>Category2</li>
			<li>Category3</li>
			<li>Category4</li>
		</ul>
	</li>
	<li>
		<h2>Hasznos linkek</h2>
		<ul>
			<li><a href="http://itmenedzsment.blogspot.com/">Blog</a></li>
			<li><a href="http://itmenedzsment.pbworks.com/">Wiki oldal</a></li>
		</ul>
	</li>
</ul>