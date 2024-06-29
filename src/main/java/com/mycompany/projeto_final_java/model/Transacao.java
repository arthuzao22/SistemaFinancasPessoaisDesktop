package com.mycompany.projeto_final_java.model;

import java.time.LocalDate;

public class Transacao {
    private int transacaoId;
    private int usuarioId;
    private int categoriaId;
    private double valor;
    private String dataDia;
    private String dataMesAno;
    private String descricao;

    public Transacao() {}

    public Transacao(int usuarioId, int categoriaId, double valor, String dataDia, String dataMesAno, String descricao) {
        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
        this.valor = valor;
        this.dataDia = dataDia;
        this.dataMesAno = dataMesAno;
        this.descricao = descricao;
    }

    // Getters
    public int getTransacaoId() {
        return transacaoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public double getValor() {
        return valor;
    }

    public String getDataDia() {
        return dataDia;
    }

    public String getDataMesAno() {
        return dataMesAno;
    }

    public String getDescricao() {
        return descricao;
    }

    // Setters
    public void setTransacaoId(int transacaoId) {
        this.transacaoId = transacaoId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDataDia(String dataDia) {
        this.dataDia = dataDia;
    }

    public void setDataMesAno(String dataMesAno) {
        this.dataMesAno = dataMesAno;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
