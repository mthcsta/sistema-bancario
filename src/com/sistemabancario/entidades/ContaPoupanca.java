package com.sistemabancario.entidades;

import com.sistemabancario.construtores.ContaPoupancaConstrutor;

public class ContaPoupanca extends ContaBancaria {
    private static final double RENDIMENTO = 0.05;
    private static final int SAQUES_MENSAIS = 3;
    private double rendimento;
    private int saquesMensais;

    public ContaPoupanca(ContaPoupancaConstrutor builder) {
        super(builder.titular(), builder.banco(), builder.numeroConta(), builder.senha());
        this.rendimento = builder.rendimento();
    }

    public ContaPoupanca(Pessoa titular, Banco banco, int numeroConta, String senha, double saldo, double rendimento) {
        super(titular, banco, numeroConta, senha, saldo);
        this.rendimento = rendimento;
        this.saquesMensais = 0;
    }
    public ContaPoupanca(Pessoa titular, Banco banco, int numeroConta, String senha, double rendimento) {
        super(titular, banco, numeroConta, senha);
        this.rendimento = rendimento;
        this.saquesMensais = 0;
    }

    public void info() {
        System.out.println("Conta Poupança");
        System.out.println(toString());
    }
    private double calculaRendimento() {
        return this.getSaldo() * (rendimento / 100);
    }
    private boolean temSaqueMensal() {
        return this.getSaquesMensais() > 0;
    }
    public void novoMes() {
        this.deposito(this.calculaRendimento());
        this.saquesMensais = 0;
    }

    public boolean saque() {
        if (!this.temSaqueMensal()) {
            System.out.println("Seus saques mensais terminaram");
            return false;
        }
        if (super.saque()) {
            this.saquesMensais++;
            return true;
        }
        return false;
    }

    public int getSaquesMensais() {
        return SAQUES_MENSAIS - saquesMensais;
    }

    /* TODO: o que seria "variação da poupança"?
    public double getVariacaoPoupanca() {
        return 0;
    }
    */

    public String toString() {
        return super.toString() + String.format(
                "Rendimento Mensal: %.2f%%\n" +
                "Saques Mensais Restantes: %d\n" +
                // "Variação da Poupança: %.2f\n",
                this.rendimento,
                this.getSaquesMensais()
                // this.getVariacaoPoupanca()
        );
    }
}
