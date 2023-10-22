package com.sistemabancario.forms;

import com.sistemabancario.construtores.ContaCorrenteConstrutor;
import com.sistemabancario.entidades.ContaCorrente;

public class ContaCorrenteForm extends ContaBancariaForm<ContaCorrenteConstrutor> {

    public ContaCorrenteForm() {
        super(new ContaCorrenteConstrutor());
    }

    @Override
    public void alternativo() {
        double taxasMensais;

        System.out.print("Taxa Mensal (R$): ");
        taxasMensais = getInput().nextDouble();

        getBuilder().taxasMensais(taxasMensais);
    }

    @Override
    public ContaCorrente getInstancia() {
        return new ContaCorrente(getBuilder());
    }

}
