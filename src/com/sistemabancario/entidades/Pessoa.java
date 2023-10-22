package com.sistemabancario.entidades;

import java.util.ArrayList;

public class Pessoa {
    public String nome;
    public String sobrenome;
    public int idade;
    private String cpf;
    private ArrayList<ContaBancaria> contasBancarias;

    public Pessoa(String nome, String sobrenome, int idade, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.cpf = cpf;
        this.contasBancarias = new ArrayList<ContaBancaria>();
    }

    public String getNome() {
        return nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public String getNomeCompleto() {
        return String.format("%s %s", nome, sobrenome);
    }
    public int getIdade() {
        return idade;
    }
    public String getCPF() {
        return cpf;
    }
    public ArrayList<ContaBancaria> getContasBancairas() {
        return contasBancarias;
    }
    public void novaContaBancaria(ContaBancaria contaBancaria) {
        this.contasBancarias.add(contaBancaria);
    }
    public void removerContaBancaria(ContaBancaria contaBancaria) {
        this.contasBancarias.remove(contaBancaria);
    }
    public String toString() {
        return String.format(
                "Nome Completo: %s\n" +
                "Idade: %d\n" +
                "CPF: %s\n",
                getNomeCompleto(),
                getIdade(),
                getCPF()
        );
    }
}
