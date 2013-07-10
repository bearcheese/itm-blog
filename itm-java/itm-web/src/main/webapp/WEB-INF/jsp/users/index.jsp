<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<h1>Felhasználók</h1>

	<table>
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Admin</th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.name}</td>
				<td>${user.email}</td>
				<td>${user.admin}</td>
				<td>
					<!--%= link_to 'Edit', edit_user_path(user) %-->Edit</td>
				<td>
					<!--%= link_to 'Destroy', user, confirm: 'Are you sure?', method: :delete %-->Delete</td>
			</tr>
		</c:forEach>
	</table>

	<br />

	<a href="/itm-web/users/new">Új felhasználó</a>


