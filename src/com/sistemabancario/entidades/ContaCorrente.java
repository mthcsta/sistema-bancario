package com.sistemabancario.entidades;

import com.sistemabancario.construtores.ContaCorrenteConstrutor;

public class ContaCorrente extends ContaBancaria {
    private static final double TAXA_MENSAL = 15.99;
    private double taxasMensais;

    public ContaCorrente(ContaCorrenteConstrutor builder) {
        super(builder.titular(), builder.banco(), builder.numeroConta(), builder.senha());
        this.taxasMensais = builder.taxasMensais();
    }

    public ContaCorrente(Pessoa titular, Banco banco, int numeroConta, String senha, double saldo, double taxasMensais) {
        super(titular, banco, numeroConta, senha, saldo);
        this.taxasMensais = taxasMensais;
    }
    public ContaCorrente(Pessoa titular, Banco banco, int numeroConta, String senha, double taxasMensais) {
        super(titular, banco, numeroConta, senha);
        this.taxasMensais = taxasMensais;
    }

    public void info() {
        System.out.println("Conta Corrente");
        System.out.println(toString());
    }

    public void novoMes() {
        this.saque(taxasMensais);
    }

    public String toString() {
        return super.toString() + String.format(
                "Taxas Mensais: %.2f\n",
                taxasMensais
        );
    }
}
