<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"
	charset="UTF-8" />
<meta name="description"
	content="IT Menedzsment beadandó - Hercegkút honlapja" />
<meta name="keywords" content="itm,hercegkút,honlap,település,ismertető" />
<meta name="author" content="BearMaster Corp" />

<link type="text/css" rel="stylesheet" media="screen"
	href="<c:url value="/assets/css/application.css" />">
<title>
    <tiles:getAsString name="title" defaultValue="ITM"/>
</title>
</head>
<body>

	<div id="wrapper">
		<div id="menu">
			<tiles:insertAttribute name="menu" />
		</div>
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="page">
			<div id="page-bgtop">
				<div id="page-bgbtm">
					<div id="content">
					   <tiles:insertAttribute name="content" />
					</div>
					<div id="sidebar">
					   <tiles:insertAttribute name="sidebar" />
					</div>
					<div style="clear: both;">&nbsp;</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
	   <tiles:insertAttribute name="footer" />
	</div>
</body>
</html>