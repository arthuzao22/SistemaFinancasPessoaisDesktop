package com.mycompany.projeto_final_java.model;

public class UserSession {
    private static int id;
    private static String nome;

    public UserSession(int id, String nome){
        this.id = id;
        this.nome = nome;
    }
    public static String getNome() {
        return nome;
    }

    public static int getId() {
        return id;
    }

}
