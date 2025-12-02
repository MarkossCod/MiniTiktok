package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; // Importação essencial para tratar erros de SQL
import java.sql.Statement;
import java.sql.ResultSet;

public class conexaoBD {

    // 1. Definição das constantes de conexão
    private static final String URL = "jdbc:mysql://localhost:3306/minitok?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    //Estabelendo conexão
    public static Connection getConnection() {
        Connection conexao = null;

        try {
            //Carregamento do driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Tenta estabelecer a conexão
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);

            //Menssagem de sucesso
            System.out.println("[Sucesso] - Conexão estabelecida com sucesso!");
            return conexao;

        } catch (ClassNotFoundException e) {
            System.out.println("[ERRO] - Driver JDBC não encontrado!");
            throw new RuntimeException("Driver não encontrado.", e);

        } catch (SQLException e) {
            // Erros como credenciais inválidas, banco de dados inexistente, ou MySQL desligado.
            System.out.println("[ERRO] - Não foi possível se conectar ao SQL! Verifique URL, Usuário/Senha ou se o MySQL está ativo.");
            throw new RuntimeException("Falha na conexão com o banco de dados.", e);
        }
    }


    //  2. Métodos de Fechamento ==================================

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("[ERRO] - Motivo: " + e.getMessage());
            }
        }
    }


    public static void closeConnection(Connection conn, Statement stmt) {
        closeConnection(conn);
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("[ERRO] - Motivo: " + e.getMessage());
            }
        }
    }


    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
        closeConnection(conn, stmt);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("[ERRO] - Motivo: " + e.getMessage());
            }
        }
    }
}
