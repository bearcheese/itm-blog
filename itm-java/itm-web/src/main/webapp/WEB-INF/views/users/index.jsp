<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

    <h1><spring:message code="users.title" /></h1>

    <table>
        <tr>
            <th><spring:message code="users.table.name" /></th>
            <th><spring:message code="users.table.email" /></th>
            <th><spring:message code="users.table.admin" /></th>
            <th></th>
            <th></th>
        </tr>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <c:choose>
                    <c:when test="${user.admin}">
                        <c:set var="check_attr" value="checked='checked'" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="check_attr" value="" />
                    </c:otherwise>
                </c:choose>
                <td><input type="checkbox" disabled="disabled" ${check_attr}></td>
                <td><spring:message code="users.table.edit" /></td>
                <td><spring:message code="users.table.delete" /></td>
            </tr>
        </c:forEach>
    </table>

    <br />

    <a href="/itm-web/users/new"><spring:message code="users.new" /></a>


