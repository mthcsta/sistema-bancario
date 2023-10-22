package com.sistemabancario.construtores;

import com.sistemabancario.entidades.Banco;
import com.sistemabancario.entidades.Pessoa;

public class ContaBancariaConstrutor implements Construtor {
    protected Pessoa titular;
    protected Banco banco;
    protected int numeroConta;
    protected String senha;

    public ContaBancariaConstrutor() {}

    public ContaBancariaConstrutor titular(Pessoa titular) {
        this.titular = titular;
        return this;
    }
    public Pessoa titular() {
        return this.titular;
    }

    public ContaBancariaConstrutor banco(Banco banco) {
        this.banco = banco;
        return this;
    }
    public Banco banco() {
        return this.banco;
    }

    public ContaBancariaConstrutor numeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
        return this;
    }
    public int numeroConta() {
        return this.numeroConta;
    }

    public ContaBancariaConstrutor senha(String senha) {
        this.senha = senha;
        return this;
    }

    public String senha() {
        return this.senha;
    }

}
