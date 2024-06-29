package com.mycompany.projeto_final_java.dao.impl;

import com.mycompany.projeto_final_java.dao.conectionBD.ConnectionFactory;
import com.mycompany.projeto_final_java.model.Categoria;
import com.mycompany.projeto_final_java.model.Transacao;
import com.mycompany.projeto_final_java.model.UserSession;
import com.mycompany.projeto_final_java.model.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransacoesDAO {

    static Connection connection;

    public List<Object[]> listarCompras() {
        int id_usuario = UserSession.getId();
        String sql = "SELECT "
                + "    transacoes.transacao_id, "
                + "    transacoes.data_dia, "
                + "    transacoes.dataMesAno, "
                + "    transacoes.descricao, "
                + "    transacoes.valor, "
                + "    categorias.nome_categoria "
                + "FROM "
                + "    transacoes "
                + "INNER JOIN "
                + "    categorias "
                + "ON "
                + "    transacoes.categoria_id = categorias.categoria_id "
                + "WHERE "
                + "    transacoes.usuario_id = " + id_usuario;

        List<Object[]> compras = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] compra = new Object[6]; // Array para armazenar os dados da compra
                compra[0] = rs.getString("transacao_id"); // Convertendo para LocalDate (se aplicável)
                compra[1] = rs.getString("data_dia"); // Convertendo para LocalDate (se aplicável)
                compra[2] = rs.getString("dataMesAno"); // Mantendo como String, dependendo do formato
                compra[3] = rs.getString("descricao");
                compra[4] = rs.getDouble("valor");
                compra[5] = rs.getString("nome_categoria");
                compras.add(compra);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar as compras: " + e.getMessage());
        }

        return compras;
    }


    public TransacoesDAO() {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public List<Object[]> listarComprasPorData(String dataMesAno) {
        int id_usuario = UserSession.getId();
        String sql = "SELECT "
                + "    transacoes.transacao_id, "
                + "    transacoes.data_dia, "
                + "    transacoes.dataMesAno, "
                + "    transacoes.descricao, "
                + "    transacoes.valor, "
                + "    categorias.nome_categoria "
                + "FROM "
                + "    transacoes "
                + "INNER JOIN "
                + "    categorias "
                + "ON "
                + "    transacoes.categoria_id = categorias.categoria_id "
                + "WHERE "
                + "    transacoes.dataMesAno =?"
                + "AND "
                + "    transacoes.usuario_id = " + id_usuario;

        List<Object[]> compras = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dataMesAno); // Define o valor do parâmetro

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] compra = new Object[6]; // Array para armazenar os dados da compra
                compra[0] = rs.getString("transacao_id"); // Convertendo para LocalDate (se aplicável)
                compra[1] = rs.getString("data_dia"); // Convertendo para LocalDate (se aplicável)
                compra[2] = rs.getString("dataMesAno"); // Mantendo como String, dependendo do formato
                compra[3] = rs.getString("descricao");
                compra[4] = rs.getDouble("valor");
                compra[5] = rs.getString("nome_categoria");
                compras.add(compra);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar as compras por data: " + e.getMessage());
        }

        return compras;
    }
    public boolean inserirTransacao(Transacao transacao) {
        String sql = "INSERT INTO transacoes (usuario_id, categoria_id, valor, data_dia, dataMesAno, descricao) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, UserSession.getId());
            stmt.setInt(2, transacao.getCategoriaId());
            stmt.setDouble(3, transacao.getValor());
            stmt.setString(4, transacao.getDataDia());
            stmt.setString(5, transacao.getDataMesAno());
            stmt.setString(6, transacao.getDescricao());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Transação inserida com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir transação: " + e.getMessage());
        }
        return true;

    }

    public boolean excluirTransacao(int transacaoId) {
        String sql = "DELETE FROM transacoes WHERE transacao_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, transacaoId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se excluiu pelo menos um registro
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir transacoes: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarTransacao(Transacao transacao) {
        String sql = "UPDATE transacoes SET usuario_id=?, valor =?, categoria_id =?, data_dia =?, dataMesAno =?, descricao =? WHERE transacao_id =?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, UserSession.getId());
            stmt.setDouble(2, transacao.getValor());
            stmt.setInt(3, transacao.getCategoriaId()); // Atualiza o campo categoria_id
            stmt.setString(4, transacao.getDataDia() != null ? transacao.getDataDia() : "");
            stmt.setString(5, transacao.getDataMesAno() != null ? transacao.getDataMesAno() : "");
            stmt.setString(6, transacao.getDescricao() != null ? transacao.getDescricao() : "");
            stmt.setInt(7, transacao.getTransacaoId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se atualizou pelo menos um registro
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar transação: " + e.getMessage());
            return false;
        }
    }
    
    public List<Object[]> listarEstatisticas() {
        int id_usuario = UserSession.getId();

        String sql = "SELECT 'Maior_Transacao' AS Categoria, Maior_Transacao AS Valor FROM ("
                + "    SELECT "
                + "        MAX(valor) AS Maior_Transacao,"
                + "        MIN(valor) AS Menor_Transacao,"
                + "        SUM(CASE WHEN valor >= 0 THEN valor ELSE 0 END) AS Receitas,"
                + "        SUM(CASE WHEN valor < 0 THEN valor ELSE 0 END) AS Despesas,"
                + "        SUM(valor) AS Saldo_Total"
                + "    FROM"
                + "        transacoes"
                + "    WHERE usuario_id = " + id_usuario
                + ") AS Pivotado"
                + " UNION ALL"
                + " SELECT 'Menor_Transacao' AS Categoria, Menor_Transacao AS Valor FROM ("
                + "    SELECT "
                + "        MAX(valor) AS Maior_Transacao,"
                + "        MIN(valor) AS Menor_Transacao,"
                + "        SUM(CASE WHEN valor >= 0 THEN valor ELSE 0 END) AS Receitas,"
                + "        SUM(CASE WHEN valor < 0 THEN valor ELSE 0 END) AS Despesas,"
                + "        SUM(valor) AS Saldo_Total"
                + "    FROM"
                + "        transacoes"
                + "    WHERE usuario_id = " + id_usuario
                + ") AS Pivotado"
                + " UNION ALL"
                + " SELECT 'Receitas' AS Categoria, Receitas AS Valor FROM ("
                + "    SELECT "
                + "        MAX(valor) AS Maior_Transacao,"
                + "        MIN(valor) AS Menor_Transacao,"
                + "        SUM(CASE WHEN valor >= 0 THEN valor ELSE 0 END) AS Receitas,"
                + "        SUM(CASE WHEN valor < 0 THEN valor ELSE 0 END) AS Despesas,"
                + "        SUM(valor) AS Saldo_Total"
                + "    FROM"
                + "        transacoes"
                + "    WHERE usuario_id = " + id_usuario
                + ") AS Pivotado"
                + " UNION ALL"
                + " SELECT 'Despesas' AS Categoria, Despesas AS Valor FROM ("
                + "    SELECT "
                + "        MAX(valor) AS Maior_Transacao,"
                + "        MIN(valor) AS Menor_Transacao,"
                + "        SUM(CASE WHEN valor >= 0 THEN valor ELSE 0 END) AS Receitas,"
                + "        SUM(CASE WHEN valor < 0 THEN valor ELSE 0 END) AS Despesas,"
                + "        SUM(valor) AS Saldo_Total"
                + "    FROM"
                + "        transacoes"
                + "    WHERE usuario_id = " + id_usuario
                + ") AS Pivotado"
                + " UNION ALL"
                + " SELECT 'Saldo_Total' AS Categoria, Saldo_Total AS Valor FROM ("
                + "    SELECT "
                + "        MAX(valor) AS Maior_Transacao,"
                + "        MIN(valor) AS Menor_Transacao,"
                + "        SUM(CASE WHEN valor >= 0 THEN valor ELSE 0 END) AS Receitas,"
                + "        SUM(CASE WHEN valor < 0 THEN valor ELSE 0 END) AS Despesas,"
                + "        SUM(valor) AS Saldo_Total"
                + "    FROM"
                + "        transacoes"
                + "    WHERE usuario_id = " + id_usuario
                + ") AS Pivotado";


        List<Object[]> estatisticas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] estatistica = new Object[2];
                estatistica[0] = rs.getString("Categoria");
                estatistica[1] = rs.getDouble("Valor");
                estatisticas.add(estatistica);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar as estatísticas: " + e.getMessage());
        }

        return estatisticas;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
