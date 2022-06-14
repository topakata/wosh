<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="models.Participant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% Participant participant = (Participant)session.getAttribute("participant"); %>
<table>
	<tr>
	<th>Промоционален код</th>
	<th>Име</th>
	<th>Фамилия</th>
	<th>Телефонен номер</th>
	</tr>
	<tr>
	<td><%= participant.getCode() %></a></td>
	<td><%= participant.getFirstName() %></td>
	<td><%= participant.getLastName() %></td>
	<td><%= participant.getPhone() %></td>	
	</tr>
	</table>
</body>
</html>