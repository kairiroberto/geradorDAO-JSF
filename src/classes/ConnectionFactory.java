/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kairi
 */
public class ConnectionFactory {

    public static Connection getConexao() {
        String caminho = "jdbc:mysql";
        String host = "localhost";
        String porta = "3306";
        String bd = "estoque_java";

        String login = "root";
        //String login = "kairiroberto1";

        String senha = "";
        //String senha = "C0l0@@u@";

        String url = "jdbc:mysql://localhost:3306/estoque_java";
        //String url = "jdbc:mysql://estoque-java.mysql.uhserver.com/estoque_java";
        //String url = caminho + "://" + host + ":" + porta + "/" + bd;

        Connection conexao = null;

        try {
            System.out.println("Conectando ao banco.");
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);
            System.out.println("Conexão estabelecida.");
        } catch (SQLException e) {
            System.out.println("Não foi possível estabelecar a conexão com o banco selecionado.");
            while (e != null) {
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Mensagem: " + e.getMessage());
                System.out.println("Error Code: " + e.getErrorCode());
                e = e.getNextException();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexao;
    }

}
