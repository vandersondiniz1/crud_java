package br.com.crud.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] Args) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		System.out.println("Conexão aberta!");
		connection.close();
	}

}