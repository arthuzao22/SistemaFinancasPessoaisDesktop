package com.mycompany.projeto_final_java.dao.impl;

import com.mycompany.projeto_final_java.dao.conectionBD.ConnectionFactory;
import com.mycompany.projeto_final_java.model.Categoria;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private static Connection connection;

    public CategoriaDAO() {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    // Método para adicionar uma categoria
    public boolean addCategoria(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categorias (nome_categoria) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNomeCategoria());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se inseriu pelo menos um registro
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir CATEGORIA: " + e.getMessage());
            return false;
        }
    }

    // Método para obter todas as categorias
    public List<Categoria> getAllCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setCategoriaId(resultSet.getInt("categoria_id"));
                categoria.setNomeCategoria(resultSet.getString("nome_categoria"));
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    // Método para atualizar uma categoria
    public void updateCategoria(Categoria categoria) throws SQLException {
        String sql = "UPDATE categorias SET nome_categoria = ? WHERE categoria_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoria.getNomeCategoria());
            statement.setInt(2, categoria.getCategoriaId());
            statement.executeUpdate();
        }
    }

    // Método para deletar uma categoria
    public boolean excluirCategoria(int id) {
        String sql = "DELETE FROM categorias WHERE categoria_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se excluiu pelo menos um registro
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir categoria: " + e.getMessage());
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (connection!= null) {
                connection.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}