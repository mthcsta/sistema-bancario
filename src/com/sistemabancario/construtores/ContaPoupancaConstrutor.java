package com.sistemabancario.construtores;

public class ContaPoupancaConstrutor extends ContaBancariaConstrutor {
    protected double rendimento;

    public ContaPoupancaConstrutor() {}

    public ContaPoupancaConstrutor rendimento(double rendimento) {
        this.rendimento = rendimento;
        return this;
    }

    public double rendimento() {
        return this.rendimento;
    }
}
