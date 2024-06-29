/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto_final_java;

import com.mycompany.projeto_final_java.view.ViewLogin; // Assuming ViewLogin is your login view class

/**
 * Main class to start the application.
 */
public class Main {
    public static void main(String[] args) {
        // Create an instance of ViewLogin and make it visible
        java.awt.EventQueue.invokeLater(() -> {
            new ViewLogin().setVisible(true);
        });
    }
}
