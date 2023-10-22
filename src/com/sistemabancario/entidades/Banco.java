package com.sistemabancario.entidades;

import java.util.ArrayList;

public class Banco {
    private String nome;
    private String cnpj;
    private int numeroBanco;
    private ArrayList<ContaBancaria> contasBancarias;
    private int numeroConta = 1000;

    public Banco(String nome, String cnpj, int numeroBanco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.numeroBanco = numeroBanco;
        this.contasBancarias = new ArrayList<ContaBancaria>();
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numeroBanco;
    }

    public String getNumeroENome() {
        return String.format("(%d) %s", numeroBanco, nome);
    }

    public ArrayList<ContaBancaria> getContasBancarias() {
        return contasBancarias;
    }

    public int novoNumeroConta() {
        return numeroConta++;
    }

    public void InfoBanco() {
        System.out.println(toString());
    }

    public void InfoContas() {
        System.out.printf("Listando contas do banco %s\n", this.getNumeroENome());
        for (ContaBancaria contaBancaria : contasBancarias) {
            contaBancaria.info();
        }
    }

    public void CriarConta(ContaBancaria contaBancaria) {
        this.contasBancarias.add(contaBancaria);
    }

    public void FecharConta(ContaBancaria contaBancaria) {
        this.contasBancarias.remove(contaBancaria);
    }

    public String toString() {
        return String.format(
                "Banco: %s\n" +
                "CNPJ: %s",
                this.getNumeroENome(),
                this.cnpj
        );
    }
}
