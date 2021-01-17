package br.com.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * CLASSE QUE CRIA A CONEXAO COM O BANCO DE DADOS
 */
public class ConnectionFactory {

	public static Connection getConnection() throws SQLException {

		   try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		    } catch (ClassNotFoundException ex) {
		    }
		    return DriverManager.getConnection("jdbc:mysql://localhost/login_java", "usuario","senha");		   
	}
}
