<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>
	<sec:authentication property="principal" var="username" />
	<spring:message code="welcome.text" arguments="${username}" argumentSeparator=";" />
</h1>

Links: <a href="<c:url value='/users'/>"><spring:message code="admin.link.users" /></a>
