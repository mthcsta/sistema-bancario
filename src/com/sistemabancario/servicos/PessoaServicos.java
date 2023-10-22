package com.sistemabancario.servicos;

import com.sistemabancario.entidades.Pessoa;

import java.util.ArrayList;

public class PessoaServicos {
    private ArrayList<Pessoa> pessoas;

    public PessoaServicos(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public boolean cpfJaCadastrado(String cpf) {
        return this.pessoas.stream().anyMatch(pessoa -> pessoa.getCPF() == cpf);
    }

    public Pessoa inserirNovo(String nome, String sobrenome, int idade, String cpf) {
        Pessoa pessoa = new Pessoa(nome, sobrenome, idade, cpf);
        this.pessoas.add(pessoa);
        return pessoa;
    }

    public ArrayList<Pessoa> pegarTudo() {
        return pessoas;
    }
    public Pessoa buscarPorCPF(String pessoaCPF) {
        return pessoas.stream().filter(p -> p.getCPF().equals(pessoaCPF)).findAny().orElse(null);
    }
}
