<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<p>
	Copyright (c) 2011 Sitename.com. All rights reserved. Design by <a href="http://www.freecsstemplates.org/">Free CSS Templates</a>.
</p>
<sec:authorize access="not isAnonymous()">
	<p style="font-size: smaller">
		Current: <sec:authentication property="principal" />
	</p>
</sec:authorize>
<a
	href="http://feedvalidator.org/check.cgi?url=http://snowqueen.herokuapp.com/feed/rss.xml">
	<img alt="[Valid RSS]"
	src="<c:url value="/assets/valid-rss-rogers.png"/>"
	title="Validate my RSS feed" />
</a>
<a href="http://jigsaw.w3.org/css-validator/check/referer"> <img
	style="border: 0; width: 88px; height: 31px"
	src="http://jigsaw.w3.org/css-validator/images/vcss"
	alt="Érvényes CSS!" />
</a>


<!-- end #footer -->
<script type="text/javascript">
	$(document).ready(
		function() {
			$("#calendar").html("<applet code = 'CalendarApplet' archive = '/itm-calendar.jar' width = '276' />");
		});
</script>