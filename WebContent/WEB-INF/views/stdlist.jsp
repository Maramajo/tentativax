<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.List"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Alien Rest</title> 
</head> 
<body> 
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setHeader("Expires", "0"); // Proxies
%>
<div align="center">
	<h1>Mostrando Alien</h1> 
	<h3><a href="restnovo">Novo Alien</a></h3>
	<table border ="1" width="500" align="center"> 
	<tr bgcolor="00FF7F"> 
                <th>Id</th>
				<th>Nome</th>
				<th>Pontos</th>
				<th>Ação</th>
            </tr>
		<!-- percorre contatos montando as linhas da tabela -->
		<%-- <%List<Dados> std = (List<Dados>)request.getAttribute("data"); %>  --%>
		
		<c:forEach items="${data}" var="contato">  
            <tr> 
                <td>${contato.id}</td>
				<td>${contato.name}</td>
				<td>${contato.points}</td>
				<td>
				<a href="restedit?id=${contato.id}">Edit</a>
				&nbsp;&nbsp;&nbsp;
				<a href="restdelete?id=${contato.id}">Delete</a>
			</td>
            </tr> 
    </c:forEach>
	</table>
		<hr/> 
		<p>funcionou hehehe</p>
		</div>
	</body> 
</html> 
