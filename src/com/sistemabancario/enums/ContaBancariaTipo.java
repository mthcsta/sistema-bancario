package com.sistemabancario.enums;

import com.sistemabancario.forms.ContaBancariaForm;
import com.sistemabancario.forms.ContaCorrenteForm;
import com.sistemabancario.forms.ContaPoupancaForm;

public enum ContaBancariaTipo {
    CORRENTE(1, "Conta Corrente", new ContaCorrenteForm()),
    POUPANCA(2, "Conta Poupança", new ContaPoupancaForm());

    private int identificador;
    private String titulo;
    private ContaBancariaForm form;

    ContaBancariaTipo(int identificador, String titulo, ContaBancariaForm form) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.form = form;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public ContaBancariaForm getForm() {
        return form;
    }

    public static ContaBancariaTipo getContaBancariaTipo(int identificador) throws IllegalAccessException {
        for (ContaBancariaTipo contaTipo : ContaBancariaTipo.values()) {
            if (contaTipo.getIdentificador() == identificador) {
                return contaTipo;
            }
        }
        throw new IllegalAccessException("Conta inexistente.");
    }
}
