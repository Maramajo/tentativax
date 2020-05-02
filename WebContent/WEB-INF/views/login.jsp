<head>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet" />
<link type="text/css" href="css/app.css" rel="stylesheet" />
</head>
<html>
<body>
	<h2>Página de Login das Tarefas</h2>
	 <table>
        <tr>
        <td bgcolor="00FF7F">${msg}</td>
        </tr>
        </table>
	<form action="efetuaLogin" method="post">
		Login: <input type="text" name="usuario" /> <br /> 
		Senha: <input type="password" name="senha" /> <br /> 
		<input type="submit" value="Entrar nas tarefas" />
	</form>
</body>
</html>
