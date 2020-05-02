<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Novo Alien</title>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setHeader("Expires", "0"); // Proxies
%>
    <div align="center">
        <h2>Novo alien</h2>
         <table>
        <tr>
        <td bgcolor="00FF7F">${msg}</td>
        </tr>
        </table>
       
        <form:form action="restadd" method="post" modelAttribute="data">
            <table border="0" cellpadding="5">
             <tr>
                    <td>Id: </td>
                    <td><form:input path="id" /></td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td>Pontos: </td>
                    <td><form:input path="points" /></td>
                </tr>
  
                <tr>
                    <td colspan="2"><input type="submit" value="Adiciona"></td>
                </tr>                    
            </table>
        </form:form>
    </div>
</body>
</html>