package com.sistemabancario.construtores;

public class ContaCorrenteConstrutor extends ContaBancariaConstrutor {
    protected double taxasMensais;

    public ContaCorrenteConstrutor() {}

    public ContaCorrenteConstrutor taxasMensais(double valor) {
        this.taxasMensais = valor;
        return this;
    }

    public double taxasMensais() {
        return this.taxasMensais;
    }
}
