<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="repositories.Repository" %>
<%@ page import="models.Participant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="registration" method="post" name="participantform">
					<label for="code">Промоционален код</label> <input type="text"
						name="code" id="code"
						placeholder="Въведете промоционален код" /> <br>

					<label for="first-name">Име</label> <input type="text"
						name="first-name" id="first-name"
						placeholder="Въведете име" /> <br>
						
					<label for="last-name">Фамилия</label> <input type="text"
						name="last-name" id="last-name"
						placeholder="Въведете фамилия" /> <br>
						
					<label for="phone">Телефонен номер</label> 
					<input type="text"
						name="phone" id="phone"
						placeholder="Въведете телефонен номер" /> <br>
						
				<input type="submit" value="Вход" />
			</form>
			
			<% Repository collection = Repository.getInstance(); %>
	<table>
	<tr>
	<th>Промоционален код</th>
	<th>Име</th>
	<th>Фамилия</th>
	<th>Телефон</th>
	</tr>
	<% for(Participant p:Repository.getCollection()) {%>
	<tr>
	<td><%= p.getCode() %></a></td>
	<td><%= p.getFirstName() %></td>
	<td><%= p.getLastName() %></td>
	<td><%= p.getPhone() %></td>	
	</tr>
	<%} %>
	</table>
			
</body>
</html>