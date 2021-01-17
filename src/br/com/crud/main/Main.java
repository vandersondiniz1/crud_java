package br.com.crud.main;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import br.com.crud.sql.SqlCrud;

/* @author vanderson.r.diniz
 * 2019-06-04 
 * CLASSE PRINCIPAL
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) throws SQLException {

		// O USUARIO SERA PASSADO COMO PARAMETRO PARA ALGUNS DOS METODOS DO CRUD
		String usuario = new String();
		String senha = new String();
		String operation = new String();
		Scanner sc = new Scanner(System.in);

		// CRIA UM NOVO OBJETO PARA ACESSAR A CLASSE SqlCRUD
		SqlCrud crud = new SqlCrud();
		// CRIA UM OBJETO LOG4J
		Logger logger = Logger.getLogger("CRUD_LOG");
		logger.info("INICIANDO A APLICACAO");
		// PERMITE QUE O USUARIO ESCOLHA UMA OPCAO PARA PROSSEGUIR
		System.out.println("\n1 - CREATE\n2 - READ\n3 - UPDATE\n4 - DELETE\n5 - SAIR\n" + "ESCOLHA UMA OPCAO");

		// AGUARDA A OPCAO INFORMADA PELO USUARIO
		do {
			operation = sc.nextLine();

			if (operation.equalsIgnoreCase("C")) {
				logger.info("\nCRIANDO UM NOVO USUARIO");
				System.out.println("INFORME UM USUARIO");
				usuario = sc.nextLine();
				System.out.println("INFORME UMA SENHA");
				senha = sc.nextLine();
				crud.Create(usuario,senha);
				break;
			} else if (operation.equalsIgnoreCase("R")) {
				logger.info("\nSELECIONANDO USUARIO");
				System.out.println("INFORME UM USUARIO");
				usuario = sc.nextLine();
				crud.READ(usuario);
				break;
			} else if (operation.equalsIgnoreCase("U")) {
				logger.info("\nATUALIZANDO REGISTROS NA TABELA");
				System.out.println("INFORME UM USUARIO");
				usuario = sc.nextLine();
				crud.Update(usuario);
				break;
			} else if (operation.equalsIgnoreCase("D")) {
				logger.info("\nDELETANDO REGISTROS NA TABELA");
				usuario = sc.nextLine();
				crud.Delete(usuario);
				break;
			} else
				logger.info("\nOPCAO INVALIDA");
			System.out.println("<OPCAO INVALIDA>");
			break;
		} while (operation.equalsIgnoreCase("sair"));
		sc.close();
	}

}
