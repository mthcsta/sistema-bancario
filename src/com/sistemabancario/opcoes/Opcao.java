package com.sistemabancario.opcoes;

public abstract class Opcao {
    private String titulo;
    public Opcao(String titulo) {
        this.titulo = titulo;
    }
    public void executar() {
        System.out.printf("Tela '%s' Não implementada.\n", this.titulo);
    }
    public String getTitulo() {
        return titulo;
    }
}
