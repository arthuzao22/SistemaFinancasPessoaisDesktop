package com.mycompany.projeto_final_java.view;

import com.mycompany.projeto_final_java.dao.impl.UsuarioDAO;
import com.mycompany.projeto_final_java.model.Usuario;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewUsuarios extends javax.swing.JFrame {

    private JButton listarButton;
    private JButton novoUsuarioButton;
    private JButton excluirUsuarioButton;
    private JButton editarUsuarioButton;
    private JButton botaoSuperiorDireito;
    private JTable usuariosTable;
    private DefaultTableModel tableModel;

    public ViewUsuarios() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        listarButton = new javax.swing.JButton();
        novoUsuarioButton = new javax.swing.JButton();
        excluirUsuarioButton = new javax.swing.JButton();
        editarUsuarioButton = new javax.swing.JButton();
        botaoSuperiorDireito = new javax.swing.JButton();
        JScrollPane scrollPane = new javax.swing.JScrollPane();
        usuariosTable = new javax.swing.JTable();
        tableModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nome", "Email"}
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listarButton.setText("Listar Usuários");
        listarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                listarButtonActionPerformed(evt);
            }
        });

        novoUsuarioButton.setText("Novo Usuário");
        novoUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                novoUsuarioButtonActionPerformed(evt);
            }
        });

        excluirUsuarioButton.setText("Excluir Usuário");
        excluirUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                excluirUsuarioButtonActionPerformed(evt);
            }
        });

        editarUsuarioButton.setText("Editar Usuário");
        editarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editarUsuarioButtonActionPerformed(evt);
            }
        });

        botaoSuperiorDireito.setText("SAIR");
        botaoSuperiorDireito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botaoSuperiorDireitoActionPerformed(evt);
            }
        });

        usuariosTable.setModel(tableModel);
        scrollPane.setViewportView(usuariosTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(listarButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(novoUsuarioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(excluirUsuarioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(editarUsuarioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(botaoSuperiorDireito)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(listarButton)
                                        .addComponent(novoUsuarioButton)
                                        .addComponent(excluirUsuarioButton)
                                        .addComponent(editarUsuarioButton)
                                        .addComponent(botaoSuperiorDireito))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    private void listarButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<Usuario> usuarios = usuarioDAO.listarUsuarios();

            // Limpa a tabela antes de adicionar novos dados
            tableModel.setRowCount(0);

            for (Usuario usuario : usuarios) {
                tableModel.addRow(new Object[]{
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail()
                });
            }

            usuarioDAO.closeConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar usuários: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void novoUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Implementação fictícia para adicionar um novo usuário
        new ViewCriarUsuario().setVisible(true);
    }

    private void excluirUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Implementação para excluir um usuário
        new ViewuExcluirUsuario().setVisible(true);
    }

    private void editarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Implementação para editar um usuário
        new ViewUpdateUsuarios().setVisible(true);
    }

    private void botaoSuperiorDireitoActionPerformed(java.awt.event.ActionEvent evt) {
        // Implementação da ação do novo botão superior direito
        new ViewHome().setVisible(true);
        dispose(); // Fecha a janela atual
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewUsuarios().setVisible(true);
            }
        });
    }
}
