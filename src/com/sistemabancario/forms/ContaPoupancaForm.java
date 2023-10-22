package com.sistemabancario.forms;

import com.sistemabancario.construtores.ContaPoupancaConstrutor;
import com.sistemabancario.entidades.ContaPoupanca;

public class ContaPoupancaForm extends ContaBancariaForm<ContaPoupancaConstrutor> {

    public ContaPoupancaForm() {
        super(new ContaPoupancaConstrutor());
    }

    @Override
    public void alternativo() {
        double rendimento;

        System.out.print("Rendimento Mensal (%): ");
        rendimento = getInput().nextDouble();

        getBuilder().rendimento(rendimento);
    }

    @Override
    public ContaPoupanca getInstancia() {
        return new ContaPoupanca(getBuilder());
    }
}
