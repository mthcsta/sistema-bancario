package com.sistemabancario.servicos;

import com.sistemabancario.entidades.Banco;

import java.util.ArrayList;

public class BancoServicos {
    private ArrayList<Banco> bancos;

    public BancoServicos(ArrayList<Banco> bancos) {
        this.bancos = bancos;
    }

    public boolean existeNumeroBanco(int numeroBanco) {
        return this.bancos.stream().anyMatch(banco -> banco.getNumero() == numeroBanco);
    }

    public Banco inserirNovo(String nome, String cnpj) {
        int numeroBanco = this.novoNumeroBanco();
        Banco novoBanco = new Banco(nome, cnpj, numeroBanco);
        this.bancos.add(novoBanco);
        return novoBanco;
    }

    private int novoNumeroBanco() {
        return bancos.size() + 1;
    }

    public ArrayList<Banco> pegarTudo() {
        return bancos;
    }
    public Banco buscarPorNumero(int numeroBanco) {
        return bancos.stream().filter(b -> b.getNumero() == numeroBanco).findAny().orElse(null);
    }
}
