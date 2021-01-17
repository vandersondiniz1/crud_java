package br.com.crud.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import br.com.crud.dao.ConnectionFactory;

/* @author vanderson.r.diniz
 * 2019-06-04 
 * CLASSE DAS OPERACOES: CREATE, READ, UPDATE AND DELETE
 * @version 1.0
 */
public class SqlCrud {
	// UTILIZANDO LOG4J PARA GERAR LOG
	Logger logger = Logger.getLogger("CRUD_LOG");

	// METODO QUE FAZ UM SELECT, A PARTIR DE UM USUARIO, NA BASE DE DADOS
	public void READ(String usuario) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM tabela_usuario WHERE nome = ?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			stmt.setString(1, rs.getString(usuario));
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				if (nome.equalsIgnoreCase(usuario)) {
					JOptionPane.showMessageDialog(null, "USUARIO " + nome + "RETORNADO COM SUCESSO");					
				}
			}
			// FECHA AS CONEXOES
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "USUARIO NAO CONFERE" + e);
		}
	}

	// METODO QUE CRIA UM USUARIO NA BASE DE DADOS
	public void Create(String usuario, String senha) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "INSERT INTO tabela_usuario" + "(nome,senha) " + "values (?,?)";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, usuario);
		stmt.setString(2, senha);
		// EXECUTA A ACAO NO BANCO DE DADOS
		stmt.execute();
		stmt.close();

		// EXIBE MENSAGEM DE SUCESSO
		JOptionPane.showMessageDialog(null, "GRAVADO");
		logger.info("\nUSUARIO" + usuario + "GRAVADO COM SUCESSO");
		con.close();
	}

	// METODO QUE ATUALIZA UM REGISTRO NA BASE DE DADOS
	public void Update(String usuario) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "UPDATE tabela_usuario SET nome = ? where nome = ?";

		try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
			preparedStatement.setString(1, usuario);

			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				preparedStatement.setString(1, usuario);
				JOptionPane.showMessageDialog(null, "ATUALIZADO COM SUCESSO");
				logger.info("\nUSUARIO" + usuario + "ATUALIZADO COM SUCESSO");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ALGUM ERRO ACONTECEU");
			logger.info("\nALGUM ERRO ACONTECEU" + e);

		}

	}

	// METODO QUE DELETA UM REGISTRO NA BASE DE DADOS
	public void Delete(String usuario) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "DELETE FROM tabela_usuario WHERE nome = ?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, usuario);

			// EXECUTA A ACAO NO BANCO DE DADOS
			stmt.execute();
			stmt.close();
			// EXIBE MENSAGEM DE SUCESSO
			JOptionPane.showMessageDialog(null, "DELETADO COM SUCESSO");
			logger.info("\nUSUARIO" + usuario + "DELETADO COM SUCESSO");
		} catch (Exception e) {
			// EXIBE MENSAGEM DE ERRO
			JOptionPane.showMessageDialog(null, "ALGUM ERRO ACONTECEU");
			logger.info("\nALGUM ERRO ACONTECEU");
		}
		// FECHA A CONEXAO
		con.close();

	}

}
