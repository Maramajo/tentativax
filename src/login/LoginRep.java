package login;

import java.util.ArrayList;
import java.util.List;

import net.codejava.customer.Usuario;

import java.sql.*;

public class LoginRep {

	Connection con = null;

	public LoginRep(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		con = DriverManager.getConnection("jdbc:mysql://192.168.0.20:3306/fj21?useTimezone=true&serverTimezone=UTC", "computabranco", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean existeUsuario(Usuario usuario){
		
		String sql = "select * from usuario where usuario= ?  and senha= ?";
		
		try{
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, usuario.getUsuario());
		stmt.setString(2, usuario.getSenha());
		
		ResultSet rs = stmt.executeQuery();
		
		//stmt.execute();
		
		//verifica se existe retorno na consulta
		if(rs.next())
		{
			stmt.close();
			return true;
		}
		else
		{
			stmt.close();
			return false;
		}
		
	}catch(SQLException e){
		throw new RuntimeException(e);
	}
}
}