package com.sistemabancario.excecoes;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException() {
        super("Saldo Insuficiente!");
    }
}
