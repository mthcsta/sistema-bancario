package com.sistemabancario.entidades;

import java.util.Scanner;

import com.sistemabancario.excecoes.SaldoInsuficienteException;

public abstract class ContaBancaria {
    protected Pessoa titular;
    protected Banco banco;
    protected int numeroConta;
    protected double saldo;
    protected String senha;

    public ContaBancaria(Pessoa titular, Banco banco, int numeroConta, String senha, double saldo) {
        this.titular = titular;
        this.banco = banco;
        this.numeroConta = numeroConta;
        this.senha = senha;
        this.saldo = saldo;

    }
    public ContaBancaria(Pessoa titular, Banco banco, int numeroConta, String senha) {
        this.titular = titular;
        this.banco = banco;
        this.numeroConta = numeroConta;
        this.senha = senha;
        this.saldo = 0.0;

    }
    public Pessoa getTitular() {
        return titular;
    }
    public int getNumero() {
        return numeroConta;
    }
    public double getSaldo() {
        return saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void saque(double valor) {
        this.saldo -= valor;
    }
    public boolean saque() {
        double valor;
        Scanner input = new Scanner(System.in);

        try {
            if (this.saldo == 0) {
                throw new SaldoInsuficienteException();
            }
            System.out.print("Digite um valor para sacar: ");
            valor = Math.abs(input.nextDouble());
            if (valor > this.saldo) {
                throw new SaldoInsuficienteException();
            }
            if (!this.verificaSenha()) {
                return false;
            }
            saque(valor);
            System.out.printf("Saque no valor de R$ %.2f realizado na conta. Saldo atual da conta: %.2f\n", valor, this.getSaldo());
            return true;
        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
            return true;
        } catch (Exception e) {
            System.out.println("Valor digitado não identificado");
            return true;
        }
    }

    public void deposito(double valor) {
        this.saldo += valor;
    }
    public void deposito() {
        double valor;
        Scanner input = new Scanner(System.in);

        System.out.print("Digite um valor para depositar: ");
        try {
            valor = Math.abs(input.nextDouble());
            deposito(valor);
            System.out.printf("Depósito no valor de R$ %.2f realizado na conta. Saldo atual da conta: %.2f\n", valor, this.getSaldo());
        } catch (Exception e) {
            System.out.println("Valor digitado não identificado");
        }
    }

    public boolean verificaSenha(String senha) {
        return this.senha.equals(senha);
    }
    public boolean verificaSenha() {
        String senha;
        Scanner input = new Scanner(System.in);

        System.out.print("Digite sua senha: ");
        senha = input.nextLine();

        if (!verificaSenha(senha)) {
            System.out.println("Senha incorreta.");
            return false;
        }

        return true;
    }

    public abstract void novoMes();
    public abstract void info();

    public String toString() {
        return String.format(
                "Titular: %s\n" +
                "Banco: %s\n" +
                "Numero da Conta: %d\n" +
                "Saldo: %.2f\n",
                this.titular.getNomeCompleto(),
                this.banco.getNumeroENome(),
                this.numeroConta,
                this.saldo
        );
    }
}
