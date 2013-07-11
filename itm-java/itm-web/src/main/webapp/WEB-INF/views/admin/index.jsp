<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h1>
	<sec:authentication property="principal" var="username" />
	<spring:message code="welcome.text" arguments="${username}" argumentSeparator=";" />
</h1>
