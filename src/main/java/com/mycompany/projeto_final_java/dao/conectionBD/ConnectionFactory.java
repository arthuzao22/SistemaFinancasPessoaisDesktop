package com.mycompany.projeto_final_java.dao.conectionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Gestao_Financeira_poo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "arthur";

    // Method to get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }

    // Example usage
    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            System.out.println("CONECTADO!");
        } catch (SQLException e) {
            System.err.println("DEU RUIM ->: " + e.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
