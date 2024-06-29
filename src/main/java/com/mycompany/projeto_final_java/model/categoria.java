package com.mycompany.projeto_final_java.model;

public class Categoria {
    private int categoriaId;
    private String nomeCategoria;

    // Construtor padrão
    public Categoria() {
    }

    // Construtor parametrizado
    public Categoria(int categoriaId, String nomeCategoria) {
        this.categoriaId = categoriaId;
        this.nomeCategoria = nomeCategoria;
    }

    // Getters
    public int getCategoriaId() {
        return categoriaId;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    // Setters
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

}
